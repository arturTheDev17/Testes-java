package conselho.api.model.dto.response;

import conselho.api.model.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoResponseDTO implements ResponseDTO {

     String tipoNotificacao;
     LocalTime horario;
     String mensagem;
}

