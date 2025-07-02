package pe.com.app.client.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.model.dto.ClientDto;
import pe.com.app.client.service.ClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ClientController.class)
public class ClientControllerTest {

    @Autowired private WebTestClient webTestClient;
    @MockBean private ClientService service;

    private List<ClientDto> list;
    private ClientDto request;

    @BeforeEach
    void setup() {
        list = List.of(ClientDto.builder()
                .id("ID")
                .name("Martha")
                .lastName("Torres")
                .documentType(DocumentType.DNI)
                .documentNumber("44564102")
                .build());
        request = list.get(0);
    }

    @Test
    void givenGetAllRequest_whenGetAll_thenReturnSuccess() {
        when(service.getAllElements()).thenReturn(Flux.fromIterable(list));
        webTestClient.get()
                .uri("/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientDto.class)
                .value(response -> {
                    Assertions.assertTrue(!response.isEmpty());
                });
    }

    @Test
    void givenCreateRequest_whenCreate_thenReturnSuccess() {
        when(service.newElement(any())).thenReturn(Mono.empty());
        webTestClient.post()
                .uri("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void givenUpdateRequest_whenUpdate_thenReturnSuccess() {
        when(service.putElement(any(), any())).thenReturn(Mono.empty());
        webTestClient.put()
                .uri("/clients/{id}", "id01row")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void givenGetByDocumentRequest_whenGetByDocument_thenReturnSuccess() {
        when(service.getClientByDocument(any(), any())).thenReturn(Mono.just(request));
        webTestClient.get()
                .uri("/clients/{documentType}/{documentNumber}", "DNI", "44710101")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenGetByIdRequest_whenGetById_thenReturnSuccess() {
        when(service.getClient(any())).thenReturn(Mono.just(request));
        webTestClient.get()
                .uri("/clients/{id}", "id01row")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenDeleteRequest_whenDelete_thenReturnSuccess() {
        when(service.deleteClient(any())).thenReturn(Mono.empty());
        webTestClient.delete()
                .uri("/clients/{id}", "id01row")
                .exchange()
                .expectStatus().isNoContent();
    }
}
