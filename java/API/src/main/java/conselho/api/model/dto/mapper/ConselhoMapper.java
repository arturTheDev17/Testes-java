package conselho.api.model.dto.mapper;

import conselho.api.model.dto.request.ConselhoRequestDTO;
import conselho.api.model.dto.response.ConselhoResponseDTO;
import conselho.api.model.entity.Conselho;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConselhoMapper {

    private ModelMapper mapper;

    public ConselhoResponseDTO toResponse(Conselho conselho ) {
        return mapper.map(conselho, ConselhoResponseDTO.class);
    }

    public Conselho toConselho(ConselhoRequestDTO conselho) {
        return mapper.map( conselho, Conselho.class );
    }

    public Page<ConselhoResponseDTO> toResponse(Page<Conselho> conselhos ) {

        List<ConselhoResponseDTO> conselhosDtoList = conselhos.getContent().stream()
                .map(this::toResponse).toList();

        return new PageImpl<>(conselhosDtoList, conselhos.getPageable(), conselhos.getTotalPages());

    }
}
