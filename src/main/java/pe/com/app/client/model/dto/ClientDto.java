package pe.com.app.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.client.common.config.ClientType;
import pe.com.app.client.common.config.DocumentType;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDto implements Serializable {
    private String id;
    private DocumentType documentType;
    private String documentNumber;
    private String name;
    private String lastName;
    private ClientType clientType;
}
