package conselho.api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequestDTO {
    @NotNull
    private Long remetenteId;
    @NotNull
    private Long destinatarioId;
    @NotNull
    private String conteudo;
    @NotNull
    private Long salaChatId;
}
