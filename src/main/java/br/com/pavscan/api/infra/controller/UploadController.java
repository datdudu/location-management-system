package br.com.pavscan.api.infra.controller;


import br.com.pavscan.api.domain.entities.section.Section;
import br.com.pavscan.api.infra.controller.Dtos.Highway.HighwayDto;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;
import br.com.pavscan.api.infra.controller.Dtos.Upload.UploadDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserEmailDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserRegistrationDto;
import br.com.pavscan.api.infra.utils.CustomMultipartFile;
import br.com.pavscan.api.infra.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class UploadController {

    public static String UPLOAD_DIRECTORY = "/app/uploads";

    public final HighwayController highwayController;

    public final SectionController sectionController;

    public final SurveyController surveyController;
    public final UserController userController;
    public UploadController(HighwayController highwayController, SectionController sectionController, SurveyController surveyController, UserController userController) {
        this.highwayController = highwayController;
        this.sectionController = sectionController;
        this.surveyController = surveyController;
        this.userController = userController;
    }

    // Código de recebimento de arquivos em Spring Boot 3 (Java)

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("arquivo") MultipartFile file,
                                         @RequestParam("levantamento") String nomeLevantamento,
                                         @RequestParam("idx") String idx,
                                         @RequestParam("qtd") String qtd,
                                         @RequestParam("idUsuario") String id,
                                         @RequestParam("email") String email,
                                         @RequestParam("token") String token) throws IOException {

        try {
            System.out.println(nomeLevantamento);
            System.out.println(idx);
            System.out.println(qtd);
            System.out.println(id);

            System.out.println("*** FILE ORIGINAL: " + file.getOriginalFilename());
            File fileDir = null;

            if (nomeLevantamento != null && nomeLevantamento.length() > 0) {
                fileDir = new File(UPLOAD_DIRECTORY, nomeLevantamento + "/");
            } else {
                fileDir = new File(UPLOAD_DIRECTORY);
            }

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }


            File fileNameAndPath = new File(fileDir, file.getOriginalFilename());


            String absolutePath =  file.getOriginalFilename();
            if(absolutePath.split("_").length == 2 && absolutePath.split("_")[1].contains("jpg")){
                CustomMultipartFile image = processImage(file, fileDir);
                fileNameAndPath = new File(fileDir, image.getOriginalFilename());
                System.out.println("*** FILE: " + fileNameAndPath.getAbsolutePath());
                Utils.saveBytesFile(image.getBytes(), fileNameAndPath);
            }
            else{
                System.out.println("*** FILE: " + fileNameAndPath.getAbsolutePath());
                Utils.saveBytesFile(file.getBytes(), fileNameAndPath);
            }

            System.out.println(" ### SALVO [" + nomeLevantamento + "]");
            String highwayName = nomeLevantamento.split("_")[0]; //CE060


            if(absolutePath.split("_").length == 2 && absolutePath.split("_")[1].contains("txt")) {
                UploadDto uploadDto = Utils.deserializeMultipartFile(file);

                if(uploadDto != null){
                    var highway = highwayController.getHighwayByName(highwayName);
                    Long highwayId = 0L;

                    if (highway.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        HighwayDto highwayToBeCreated = UploadDto.toHighwayDto(uploadDto);
                        HighwayDto highwayCreated = highwayController.createHighway(highwayToBeCreated);
                        highwayId = highwayCreated.id();
                    } else {
                        highwayId = highway.getBody().id();
                    }


                    var user = userController.getUserByEmail(new UserEmailDto(email));
                    Long userId = 0L;

                    if (user.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        UserRegistrationDto userToBeCreated = UploadDto.toUserDto(email);
                        UserDto userCreated = userController.createUser(userToBeCreated);
                        userId = userCreated.id();
                    } else {
                        userId = user.getBody().id();
                    }


                    var survey = surveyController.getSurveyByName(fileNameAndPath.getAbsolutePath().split("/")[3]);
                    Long surveyId = 0L;

                    if (survey.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                        String datetime = Utils.formatDateTimeString(file.getOriginalFilename().split("_")[0]+file.getOriginalFilename().split("_")[1]);
                        SurveyDto surveyDto = surveyController.createSurvey(UploadDto.toSurveyDto(uploadDto, nomeLevantamento, highwayId, userId, datetime));
                    }else {
                        surveyId = survey.getBody().getId();
                    }

                    double distance = Utils.getDistanceBetweenPoints(uploadDto.latInicio(),uploadDto.latFinal(),uploadDto.lngInicio(),uploadDto.lngFinal(),"km");

                    sectionController.createSection(UploadDto.toSectionDto(uploadDto, surveyId, email, distance));
                }
            }
            Integer qtdAtt = Integer.parseInt(qtd) - 1;

            if(qtdAtt == Integer.parseInt(idx)) {
                System.out.println("PASSEI");
                var survey = surveyController.getSurveyByName(nomeLevantamento);
                Section lastSection = sectionController.getLastBySurveyId(survey.getBody().getId()).getBody();
                Section firstSection = sectionController.getFirstBySurveyId(survey.getBody().getId()).getBody();
                Double rms = sectionController.getAverageRMS(survey.getBody().getId()).getBody();
                Double rmsR1 = sectionController.getAverageRMSR1(survey.getBody().getId()).getBody();
                Double iri = sectionController.getAverageIRI(survey.getBody().getId()).getBody();
                Double iriR1 = sectionController.getAverageIRIR1(survey.getBody().getId()).getBody();
                Double averageSpeed = sectionController.getAverageAverageSpeed(survey.getBody().getId()).getBody();
                Double maxSpeed = sectionController.getAverageMaxSpeed(survey.getBody().getId()).getBody();
                Double averageDistance = sectionController.getAverageDistance(survey.getBody().getId()).getBody();
                System.out.println(averageDistance);
                String classification = Utils.calcClassificationHDM4ByIRI(iri);
                String classificationR1 = Utils.calcClassificationHDM4ByIRI(iriR1);
                Double covAUD = Utils.calcCOV_AUD(averageSpeed, iriR1);
                Double covBRL = Utils.calcCOV_BRL(averageSpeed, iriR1);

                SurveyDto surveyDto = new SurveyDto(0L,
                        survey.getBody().getName(),
                        survey.getBody().getDatetime(),
                        firstSection.getLatStart(),
                        lastSection.getLatEnd(),
                        firstSection.getLongStart(),
                        lastSection.getLongEnd(),
                        rms,
                        rmsR1,
                        iri,
                        iriR1,
                        classification,
                        classificationR1,
                        covAUD,
                        covBRL,
                        averageDistance,
                        firstSection.getVehicleType(),
                        averageSpeed,
                        maxSpeed,
                        survey.getBody().getUserId(),
                        survey.getBody().getHighwayId()
                );

                surveyController.updateSurvey(surveyDto, survey.getBody().getId());
            }

            System.out.println("============= Fim ==================");

            return ResponseEntity.status(HttpStatus.OK)
                    .body("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error");
        }
    }

    public CustomMultipartFile processImage(MultipartFile file, File dir) throws IOException {
        // Read the image
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        // Calculate new dimensions while maintaining aspect ratio
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newWidth = 640;
        int newHeight = (newWidth * originalHeight) / originalWidth;

        // Resize the image
        BufferedImage resizedImage = Utils.resizeImage(originalImage, newWidth, newHeight);


        // Convert the resized image to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        // Rename the file
        String originalFileName = file.getOriginalFilename();
        String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_orig" + originalFileName.substring(originalFileName.lastIndexOf("."));

        // Create a new MultipartFile
        return new CustomMultipartFile("file", newFileName, file.getContentType(), imageInByte);
    }
}
