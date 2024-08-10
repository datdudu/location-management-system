package br.com.pavscan.api.infra.controller.Dtos.Upload;

import br.com.pavscan.api.infra.controller.Dtos.Highway.HighwayDto;
import br.com.pavscan.api.infra.controller.Dtos.Section.SectionDto;
import br.com.pavscan.api.infra.controller.Dtos.Survey.SurveyDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserDto;
import br.com.pavscan.api.infra.controller.Dtos.User.UserRegistrationDto;

public record UploadDto(
        String id,
        Long index,
        String levantamento,
        String tipoVeiculo,
        Double latInicio,
        Double lngInicio,
        Double latFinal,
        Double lngFinal,
        Double velMedia,
        Double velMax,
        Double dist,
        Integer altitude,
        Long dtIni,
        Long dtFim,
        Double acc,
        Integer tamanho,
        Double rms,
        Double rmsRl,
        Double iri,
        Double iriRl,
        String classificacao,
        String classificacaoRl,
        Double covAUD,
        Double covBRL,
        String email,
        Boolean enviado
) {
    public static HighwayDto toHighwayDto(UploadDto uploadDto){
        return new HighwayDto(
                1L,
                uploadDto.levantamento(),
                0,
                "",
                0.0,
                0.0,
                0.0,
                0.0
        );
    }

    public static UserRegistrationDto toUserDto(String email){
        return new UserRegistrationDto(
                "username generico",
                email,
                "nome generico",
                "sobrenome generico",
                "password"
        );
    }

    public static SurveyDto toSurveyDto(UploadDto uploadDto, String surveyName, Long highwayId, Long userId, String dateTime){
        return new SurveyDto(
                1L,
                surveyName,
                dateTime,
                uploadDto.latInicio(),
                uploadDto.latFinal(),
                uploadDto.lngInicio(),
                uploadDto.lngFinal(),
                uploadDto.rms(),
                uploadDto.rmsRl(),
                uploadDto.iri(),
                uploadDto.iriRl(),
                uploadDto.classificacao(),
                uploadDto.classificacaoRl(),
                uploadDto.covAUD(),
                uploadDto.covBRL(),
                uploadDto.dist(),
                uploadDto.tipoVeiculo(),
                uploadDto.velMedia(),
                uploadDto.velMax(),
                userId,
                highwayId
        );
    }

    public static SectionDto toSectionDto(UploadDto uploadDto, Long surveyId, String email, Double distance){
        return new SectionDto(
                uploadDto.id(),
                uploadDto.index(),
                uploadDto.levantamento(),
                uploadDto.tipoVeiculo(),
                uploadDto.latInicio(),
                uploadDto.latFinal(),
                uploadDto.lngInicio(),
                uploadDto.lngFinal(),
                uploadDto.velMedia(),
                uploadDto.velMax(),
                null,
                null,
                distance,
                uploadDto.altitude(),
                uploadDto.dtIni(),
                uploadDto.dtFim(),
                uploadDto.acc(),
                uploadDto.tamanho(),
                uploadDto.rms(),
                uploadDto.rmsRl(),
                uploadDto.iri(),
                uploadDto.iriRl(),
                uploadDto.classificacao(),
                uploadDto.classificacaoRl(),
                uploadDto.covAUD(),
                uploadDto.covBRL(),
                email,
                "Caminho da imagem",
                surveyId
        );
    }
}
