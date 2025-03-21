package conselho.api.model.dto.response;

import conselho.api.model.entity.Turma;
import conselho.api.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConselhoResponseDTO implements ResponseDTO {

     Long id;
     Date dataInicio;
     Date dataFim;
     List<Usuario> professores;
     Turma turma;

}

