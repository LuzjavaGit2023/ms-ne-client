package pe.com.app.client.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String error;
    private String message;
    private String timestamp;
}
