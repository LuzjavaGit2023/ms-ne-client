package pe.com.app.client.util;

import pe.com.app.client.common.config.ClientType;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.model.dto.ClientDto;
import pe.com.app.client.model.persistence.ClientEntity;

import java.util.List;

public class UtilBuild {
    public static ClientDto buildRequestCreate() {
        return ClientDto.builder()
                .documentType(DocumentType.DNI)
                .documentNumber("44745520")
                .name("Jorge Lucas")
                .lastName("Cardenas Neto")
                .clientType(ClientType.NATURAL)
                .build();
    }

    public static ClientDto buildRequestUpdate() {
        return ClientDto.builder()
                .documentType(DocumentType.DNI)
                .documentNumber("44745520")
                .name("Jorge Mendez")
                .lastName("Cardenas Perez")
                .clientType(ClientType.NATURAL)
                .build();
    }

    public static List buildList() {
        return List.of(buildRequestCreate());
    }

    public static ClientEntity buildEntity(ClientDto dto) {
        return ClientEntity.builder()
                .id(dto.getId() == null ? "9999" : dto.getId())
                .clientType(dto.getClientType())
                .documentNumber(dto.getDocumentNumber())
                .documentType(DocumentType.DNI)
                .name(dto.getName())
                .lastName(dto.getLastName())
                .build();
    }

    public static ClientEntity buildEntity() {
        return ClientEntity.builder()
                .id("1111")
                .clientType(ClientType.NATURAL)
                .documentNumber("44852852")
                .documentType(DocumentType.DNI)
                .name("Lupe")
                .lastName("Teran")
                .build();
    }
}
