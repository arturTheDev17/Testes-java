package conselho.api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String codigoTurma;

    @Column(nullable = false)
    private String nomeCurso;

    @ManyToMany (mappedBy = "turmas")
    private List<Usuario> usuarios;

    @OneToMany (mappedBy = "turma")
    private List<Conselho> conselhos;

}
