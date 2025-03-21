package conselho.api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreConselhoForms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private String pontosFortes;
    @Column(nullable = false)
    private String oportunidadesMelhorias ;
    @Column(nullable = false)
    private String sugestoes ;
}
