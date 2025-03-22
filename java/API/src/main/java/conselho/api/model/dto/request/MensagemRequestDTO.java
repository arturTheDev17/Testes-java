package conselho.api.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequestDTO {

    private Long remetenteId;
    private Long destinatarioId;
    private String conteudo;
    private Long salaChatId;
}
