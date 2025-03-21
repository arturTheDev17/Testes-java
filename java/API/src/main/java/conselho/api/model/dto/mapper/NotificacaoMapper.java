package conselho.api.model.dto.mapper;

import conselho.api.model.dto.request.NotificacaoRequestDTO;
import conselho.api.model.dto.response.NotificacaoResponseDTO;
import conselho.api.model.entity.Notificacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NotificacaoMapper {

    private ModelMapper mapper;

    public NotificacaoResponseDTO toResponse(Notificacao notificacao) {
        return mapper.map(notificacao, NotificacaoResponseDTO.class);
    }

    public Notificacao toNotificacao(NotificacaoRequestDTO notificacao) {
        return mapper.map(notificacao, Notificacao.class);
    }

    public Page<NotificacaoResponseDTO> toResponse(Page<Notificacao> notificoes) {

        List<NotificacaoResponseDTO> notificoesDtoList = notificoes.getContent().stream()
                .map(this::toResponse).toList();

        return new PageImpl<>(notificoesDtoList, notificoes.getPageable(), notificoes.getTotalPages());
    }
}