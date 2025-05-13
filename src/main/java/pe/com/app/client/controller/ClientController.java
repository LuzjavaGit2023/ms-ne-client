package pe.com.app.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.com.app.client.advice.ErrorResponse;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.model.dto.ClientDto;
import pe.com.app.client.service.ClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ClientController <br/>
 * <b>Copyright</b>: 2025 Tu Banco - Celula <br/>
 * .
 *
 * @author 2025 Tu Banco - Peru <br/>
 * <u>Service Provider</u>: Tu Banco <br/>
 * <u>Changes:</u><br/>
 * <ul>
 * <li>
 * May 10, 2025 Creación de Clase.
 * </li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Functional operations related to clients")
public class ClientController {

    private final ClientService service;

    /**
     * This method is used to list elements.
     *
     * @return ClientDto Flux.
     */
    @GetMapping
    @Operation(summary = "This method is used to list all elements.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Flux<ClientDto> getAllClients() {
        return service.getAllElements();
    }

    /**
     * This method is used to add element.
     *
     * @return Void Mono.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This method is used to add element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<Void> newClient(@RequestBody ClientDto obj) {
        return service.newElement(obj);
    }

    /**
     * This method is used to replace element.
     *
     * @return Void Mono.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This method is used to replace element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<Void> replaceClient(@PathVariable String id, @RequestBody ClientDto obj) {
        return service.putElement(id, obj);
    }

    /**
     * This method is used to get element by document.
     *
     * @return ClientDto Mono.
     */
    @GetMapping("/{documentType}/{documentNumber}")
    @Operation(summary = "This method is used to get element by document.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })

    public Mono<ClientDto> getClientByDocument(@PathVariable DocumentType documentType, @PathVariable String documentNumber) {
        return service.getClientByDocument(documentType, documentNumber);
    }

    /**
     * This method is used to get element.
     *
     * @return ClientDto Mono.
     */
    @GetMapping("/{id}")
    @Operation(summary = "This method is used to get element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<ClientDto> getClient(@PathVariable String id) {
        return service.getClient(id);
    }

    /**
     * This method is used to delete element.
     *
     * @return ClientDto Mono.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "This method is used to delete element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<Void> deleteClient(@PathVariable String id) {
        return service.deleteClient(id);
    }
}
