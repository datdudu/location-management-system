package br.com.pavscan.api.infra.utils;

import br.com.pavscan.api.infra.controller.Dtos.Upload.UploadDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

public class Utils {

    public static void saveBytesFile(byte[] data, File outFile) throws IOException {
        //Files.write(fileNameAndPath.toPath(), file.getBytes());
        FileOutputStream outStream = new FileOutputStream(outFile);
        outStream.write(data);
        outStream.flush();
        outStream.close();
    }

    public static void saveTextFile(String filePath, String content) {
        try {
            FileOutputStream fOut = new FileOutputStream(filePath, false);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(content);
            myOutWriter.close();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readTextFile(String filePath) {
        StringBuffer sb = new StringBuffer();

        try {
            FileInputStream fIn = new FileInputStream(filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(fIn));
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static UploadDto deserializeMultipartFile(MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file.getInputStream(), UploadDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDateTimeString(String dateTime) {
        // Remove the last 3 zeros
        dateTime = dateTime.substring(0, 14);

        String day = dateTime.substring(0, 2);
        String month = dateTime.substring(2, 4);
        String year = dateTime.substring(4, 8);
        String hour = dateTime.substring(8, 10);
        String minute = dateTime.substring(10, 12);
        String second = dateTime.substring(12, 14);

        return day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
    }

    public static  BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        return Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, width, height);
    }

    public static double calcCOV_BRL(double averageSpeed, double iri) {
        double dolarAUD = 3.31;
        return calcCOV_AUD(averageSpeed, iri) * dolarAUD;
    }

    public static double calcCOV_AUD(double averageSpeed, double iri) {
        return 21.65553 * (0.682568 + (8.926626 / averageSpeed) + (1.86E-05 * (averageSpeed * averageSpeed)) + (0.029245 * iri) + (0.000812 * (iri * iri)) + (0.040681 * 1.04));
    }

    public static String calcClassificationHDM4ByIRI(double IRI) {
        if (0 < IRI && IRI <= 2) {
            return "Excelent";
        } else if (2 < IRI && IRI <= 4) {
            return "Good";
        } else if (4 < IRI && IRI <= 6) {
            return "Regular";
        } else if (IRI > 6) {
            return "Bad";
        }

        return "Error";
    }

    public static double getDistanceBetweenPoints(double latStart, double latEnd, double lngStart, double lngEnd, String units) {
        int metro = 1000;
        double R = units.equals("km") ? 6371 : 3958.8; // km ou milhas

        double dLat = Math.toRadians((latEnd - latStart));
        double dLon = Math.toRadians((lngEnd - lngStart));
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latStart)) * Math.cos(Math.toRadians(latEnd)) *
                        Math.sin(dLon / 2) *
                        Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d * metro;
    }
}