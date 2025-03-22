package conselho.api.repository;

import conselho.api.model.entity.Mensagem;
import conselho.api.model.entity.SalaChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findBySalaChat(SalaChat salaChat);
}
