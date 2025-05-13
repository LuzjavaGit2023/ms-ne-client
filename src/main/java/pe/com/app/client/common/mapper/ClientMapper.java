package pe.com.app.client.common.mapper;

import pe.com.app.client.model.dto.*;
import pe.com.app.client.model.persistence.ClientEntity;

public class ClientMapper {

    public static ClientDto buildDto(ClientEntity e) {
        return ClientDto.builder()
                .id(e.getId())
                .documentType(e.getDocumentType())
                .documentNumber(e.getDocumentNumber())
                .name(e.getName())
                .lastName(e.getLastName())
                .clientType(e.getClientType())
                .build();
    }

    public static ClientEntity buildEntity(ClientDto e) {
        return ClientEntity.builder()
                .documentType(e.getDocumentType())
                .documentNumber(e.getDocumentNumber())
                .name(e.getName())
                .lastName(e.getLastName())
                .clientType(e.getClientType())
                .build();
    }

    public static ClientEntity buildEntity(ClientDto e, String id) {
        return ClientEntity.builder()
                .id(id)
                .documentType(e.getDocumentType())
                .documentNumber(e.getDocumentNumber())
                .name(e.getName())
                .lastName(e.getLastName())
                .clientType(e.getClientType())
                .build();
    }
}
