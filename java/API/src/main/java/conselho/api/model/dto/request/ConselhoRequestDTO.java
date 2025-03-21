package conselho.api.model.dto.request;

import conselho.api.model.entity.Turma;
import conselho.api.model.entity.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConselhoRequestDTO implements RequestDTO {
    @NotNull private Turma turma;
    @NotNull private Date dataInicio;
    @NotNull private Date dataFim;
}
