package conselho.api.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "sala_chat_usuarios",
            joinColumns = @JoinColumn(name = "sala_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "salaChat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensagem> mensagens;

    private String nome;

    @PrePersist
    public void configurarSala() {
        if (usuarios != null && usuarios.size() > 1) {
            this.nome = usuarios.get(0).getNome() + " e " + usuarios.get(1).getNome(); // Definir nome da sala
        }
    }
}
