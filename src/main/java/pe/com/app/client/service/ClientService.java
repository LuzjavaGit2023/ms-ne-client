package pe.com.app.client.service;

import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.model.dto.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Interface</b>: ProductService <br/>
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
public interface ClientService {

    Flux<ClientDto> getAllElements();

    Mono<Void> newElement(ClientDto obj);

    Mono<Void> putElement(String id, ClientDto obj);

    Mono<ClientDto> getClient(String id);

    Mono<Void> deleteClient(String id);

    Mono<ClientDto> getClientByDocument(DocumentType documentType, String documentNumber);
}
