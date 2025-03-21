package conselho.api.model.entity;

import conselho.api.model.UsuarioRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Enumerated(EnumType.STRING) //ARTUR: não sei se é bom fazer isso
    @Column(nullable = false)
    private UsuarioRoles role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    private List<Turma> turmas;

    private String matricula;

//    @Column( nullable = false )
//    private String senha;


}
