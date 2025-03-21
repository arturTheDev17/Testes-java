package conselho.api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaResponseDTO implements ResponseDTO {
    
    private String codigoTurma;
    private String nomeCurso;

    private List<UsuarioResponseDTO> usuarios;
    private List<ConselhoResponseDTO> conselhos;
}
