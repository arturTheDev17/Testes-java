package conselho.api.model.dto.response;

import conselho.api.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaChatResponseDTO {

    private Long id;
    private List<Usuario> usuarios;
    private List<MensagemResponseDTO> mensagens;
}
