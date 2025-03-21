package conselho.api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String codigoTurma; //ex: mi, mm, me

    @Column(nullable = false)
    private String nomeCurso;

    @ManyToMany (mappedBy = "turmas")
    private List<Usuario> usuarios;

    @OneToMany (mappedBy = "turma")
    private List<Conselho> conselhos;

}
