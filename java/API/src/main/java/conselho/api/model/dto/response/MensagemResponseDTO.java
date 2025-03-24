package conselho.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResponseDTO {

    private Long id;
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;

    private String dataEnvio;
}
