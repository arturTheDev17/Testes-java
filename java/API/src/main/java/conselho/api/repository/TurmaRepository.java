package conselho.api.repository;

import conselho.api.model.entity.Turma;
import conselho.api.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {

    Optional<Turma> findByNomeCurso(String nomeCurso);
}
