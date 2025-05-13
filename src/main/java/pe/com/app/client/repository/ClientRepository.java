package pe.com.app.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.model.persistence.ClientEntity;
import reactor.core.publisher.Flux;

/**
 * <b>Interface</b>: ClientRepository <br/>
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
public interface ClientRepository extends ReactiveMongoRepository<ClientEntity, String> {

    Flux<ClientEntity> findByDocumentTypeAndDocumentNumber(DocumentType documentType, String documentNumber);

}
