package conselho.api.repository;

import conselho.api.model.entity.SalaChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaChatRepository extends JpaRepository<SalaChat, Long> {
}
