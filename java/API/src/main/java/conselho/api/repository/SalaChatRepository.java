package conselho.api.repository;

import conselho.api.model.entity.SalaChat;
import conselho.api.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalaChatRepository extends JpaRepository<SalaChat, Long> {
    Optional<SalaChat> findByUsuariosIn(List<Usuario> usuarios);
}
