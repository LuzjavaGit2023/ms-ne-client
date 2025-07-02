package pe.com.app.client.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.common.util.Constant;
import pe.com.app.client.model.dto.ClientDto;
import pe.com.app.client.model.persistence.ClientEntity;
import pe.com.app.client.repository.ClientRepository;
import pe.com.app.client.util.UtilBuild;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;

    private ClientDto requestCreate;
    private ClientDto requestUpdate;

    private ClientEntity entity;

    @BeforeEach
    void setup() {
        requestCreate = UtilBuild.buildRequestCreate();
        requestUpdate = UtilBuild.buildRequestUpdate();
        entity = UtilBuild.buildEntity(requestCreate);
    }

    @Test
    void givenGetAllRequest_whenGetAll_thenReturnSuccess() {
        when(repository.findAll()).thenReturn(Flux.just(entity));

        var result = service.getAllElements();
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ClientDto);
                })
                .verifyComplete();
    }

    @Test
    void givenCreateRequest_whenCreate_thenReturnSuccess() {
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.empty());
        when(repository.save(any())).thenReturn(Mono.just(entity));

        var result = service.newElement(requestCreate);
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void givenCreateRequest_whenAlreadyExists_thenReturnError() {
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.just(entity));
        when(repository.save(any())).thenReturn(Mono.just(entity));

        var result = service.newElement(requestCreate);
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_EXIST_BY_DOCUMENT, error.getMessage());
                })
                .verify();
    }

    @Test
    void givenUpdateRequest_whenUpdate_thenReturnSuccess() {
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.empty());
        when(repository.save(any())).thenReturn(Mono.just(entity));

        var result = service.putElement("id", requestUpdate);
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void givenUpdateRequest_whenAlreadyExists_thenReturnError() {
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.just(UtilBuild.buildEntity()));

        var result = service.putElement("id", requestUpdate);
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_EXIST_BY_DOCUMENT, error.getMessage());
                })
                .verify();
    }

    @Test
    void givenGetByIdRequest_whenGetById_thenReturnSuccess() {
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));

        var result = service.getClient("ID");
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ClientDto);
                })
                .verifyComplete();
    }

    @Test
    void givenGetByIdRequest_whenNotFound_thenReturnError() {
        when(repository.findById(anyString())).thenReturn(Mono.empty());

        var result = service.getClient("ID");
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_NOT_FOUND, error.getMessage());
                })
                .verify();
    }

    @Test
    void givenGetByDocumentRequest_whenGetByDocument_thenReturnSuccess() {
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.just(UtilBuild.buildEntity()));

        var result = service.getClientByDocument(DocumentType.DNI, "44740401");
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof ClientDto);
                })
                .verifyComplete();
    }

    @Test
    void givenGetByDocumentRequest_whenNotFound_thenReturnError() {
        when(repository.findByDocumentTypeAndDocumentNumber(any(), any())).thenReturn(Flux.empty());

        var result = service.getClientByDocument(DocumentType.DNI, "44740401");
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_NOT_FOUND, error.getMessage());
                })
                .verify();
    }

    @Test
    void givenDeleteRequest_whenDelete_thenReturnSuccess() {
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.deleteById(anyString())).thenReturn(Mono.empty());

        var result = service.deleteClient("ID");
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void givenDeleteRequest_whenNotFound_thenReturnSuccess() {
        when(repository.findById(anyString())).thenReturn(Mono.empty());

        var result = service.deleteClient("ID");
        StepVerifier.create(result)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof IllegalStateException);
                    assertEquals(Constant.ELEMENT_NOT_FOUND, error.getMessage());
                })
                .verify();
    }
}
