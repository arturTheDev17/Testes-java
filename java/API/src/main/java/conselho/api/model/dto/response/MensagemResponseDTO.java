package conselho.api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResponseDTO {

    private Long id;
    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;
    private LocalDateTime dataEnvio;
}
