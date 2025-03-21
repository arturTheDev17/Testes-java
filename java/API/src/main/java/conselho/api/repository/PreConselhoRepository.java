package conselho.api.repository;

import conselho.api.model.entity.PreConselhoForms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreConselhoRepository extends JpaRepository <PreConselhoForms,Long> {
}
