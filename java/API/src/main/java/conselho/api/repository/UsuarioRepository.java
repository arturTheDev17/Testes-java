package conselho.api.repository;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Usuario findByRoleAndId(UsuarioRoles role , Long id);

//    @Query("SELECT u FROM Usuario u WHERE u.role = :role")
    Page<Usuario> findAllByRole(UsuarioRoles role , Pageable pageable);
}
