package conselho.api.repository;

import conselho.api.model.entity.Turma;
import conselho.api.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {


}
