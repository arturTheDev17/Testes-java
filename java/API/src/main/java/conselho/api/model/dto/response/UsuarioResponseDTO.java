package conselho.api.model.dto.response;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.entity.Turma;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO implements ResponseDTO {
    private Long id;
    private UsuarioRoles role;
    private String nome;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "email invalido")
    private String email;
    private String matricula;
    private List<Turma> turmas;
}
