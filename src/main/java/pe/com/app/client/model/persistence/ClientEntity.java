package pe.com.app.client.model.persistence;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.app.client.common.config.ClientType;
import pe.com.app.client.common.config.DocumentType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "clients")
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 7346145359381795413L;
    @Id
    private String id;
    private DocumentType documentType;
    private String documentNumber;
    private String name;
    private String lastName;
    private ClientType clientType;
}
