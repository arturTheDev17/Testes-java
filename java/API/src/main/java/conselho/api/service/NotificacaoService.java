package conselho.api.service;

import conselho.api.model.dto.mapper.NotificacaoMapper;
import conselho.api.model.dto.request.NotificacaoRequestDTO;
import conselho.api.model.dto.response.NotificacaoResponseDTO;
import conselho.api.model.entity.Notificacao;
import conselho.api.repository.NotificacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private NotificacaoRepository repository;
    private NotificacaoMapper mapper;

    public NotificacaoResponseDTO postNotificacao(NotificacaoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toNotificacao(dto)));
    }

    public Notificacao buscarNotificacao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new
                        NoSuchElementException("Notificação não encontrada"));
    }

    public void removerNotificacao(Long id) {
        repository.deleteById(id);
    }


    public NotificacaoResponseDTO findById(Long id) {
        return mapper.toResponse(buscarNotificacao(id));
    }

    public Page<NotificacaoResponseDTO> findAll(Pageable pageable) {
        return mapper.toResponse(repository.findAll(pageable));
    }

    public NotificacaoResponseDTO updateNotificacao(Long id, NotificacaoRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toNotificacao(dto)));
    }
}