package conselho.api.model.dto.request;

import conselho.api.model.UsuarioRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO implements RequestDTO {

  @NotNull
  private UsuarioRoles role;

  @NotBlank
  private String nome;

  @NotBlank
  @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
          flags = Pattern.Flag.CASE_INSENSITIVE,
          message = "email invalido")
  private String email;

  private String matricula;

}
