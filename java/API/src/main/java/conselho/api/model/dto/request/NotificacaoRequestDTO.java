package conselho.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoRequestDTO {

    @NotBlank String tipoNotificacao;
    @NotBlank String mensagem;
    @NotNull Time horario;
}