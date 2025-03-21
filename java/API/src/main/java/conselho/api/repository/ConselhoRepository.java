package conselho.api.repository;

import conselho.api.model.entity.Conselho;
import conselho.api.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConselhoRepository extends JpaRepository<Conselho, Long> {

}
