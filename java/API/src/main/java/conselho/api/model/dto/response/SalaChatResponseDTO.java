package conselho.api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaChatResponseDTO {

    private Long id;
    private List<Long> usuariosIds;
    private String nome;
}
