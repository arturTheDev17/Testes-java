package conselho.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaRequestDTO implements RequestDTO {

    @NotBlank private String codigoTurma;
    @NotBlank private String nomeCurso;
//    @NotNull Date dataCriacao;
    private List<UsuarioRequestDTO> usuarios;
}
