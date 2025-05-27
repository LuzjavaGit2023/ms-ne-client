package pe.com.app.client.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.client.common.config.ClientType;
import pe.com.app.client.common.config.DocumentType;

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
