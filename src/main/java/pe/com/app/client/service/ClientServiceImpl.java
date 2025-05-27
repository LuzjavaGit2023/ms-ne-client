package pe.com.app.client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.app.client.common.config.DocumentType;
import pe.com.app.client.common.mapper.ClientMapper;
import pe.com.app.client.common.util.Constant;
import pe.com.app.client.model.dto.ClientDto;
import pe.com.app.client.repository.ClientRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: ProductServiceImpl <br/>
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
@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    /**
     * This method is used to list all elements.
     *
     * @return ClientDto Flux.
     */
    @Override
    public Flux<ClientDto> getAllElements() {
        log.info("getAllElements : execute");
        return repository.findAll().map(ClientMapper::buildDto).log();
    }

    @Override
    public Mono<Void> newElement(ClientDto obj) {
        log.info("addElement : execute, request {}", obj);
        return repository.findByDocumentTypeAndDocumentNumber(obj.getDocumentType(), obj.getDocumentNumber())
                .flatMap(existing -> Mono.error(new IllegalStateException(Constant.ELEMENT_EXIST_BY_DOCUMENT)))
                .switchIfEmpty(repository.save(ClientMapper.buildEntity(obj)))
                .then();
    }

    @Override
    public Mono<Void> putElement(String id, ClientDto obj) {
        log.info("putElement : execute, id {}", id);
        log.info("putElement : execute, request {}", obj);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)))
                .flatMap(existing ->
                        repository.findByDocumentTypeAndDocumentNumber(existing.getDocumentType(),
                                        obj.getDocumentNumber())
                                .filter(entity -> !entity.getId().equals(id))
                                .hasElements()
                                .flatMap(redundant -> {
                                    if (redundant) {
                                        return Mono.error(new IllegalStateException(
                                                Constant.ELEMENT_EXIST_BY_DOCUMENT));
                                    }
                                    else {
                                        return repository.save(ClientMapper.buildEntity(obj, id));
                                    }
                                })
                )
                .then();
    }

    @Override
    public Mono<ClientDto> getClient(String id) {
        log.info("getClient : execute, id {}", id);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)))
                .map(existing -> ClientMapper.buildDto(existing));
    }

    @Override
    public Mono<ClientDto> getClientByDocument(DocumentType documentType, String documentNumber) {
        return repository.findByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .map(existing -> ClientMapper.buildDto(existing))
                .next()
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)));
    }

    @Override
    public Mono<Void> deleteClient(String id) {
        log.info("deleteClient : execute, id {}", id);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalStateException(Constant.ELEMENT_NOT_FOUND)))
                .flatMap(existing -> repository.deleteById(id));
    }

}
