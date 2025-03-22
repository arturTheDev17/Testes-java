package conselho.api.model.dto.mapper;

import conselho.api.model.dto.request.SalaChatRequestDTO;
import conselho.api.model.dto.response.SalaChatResponseDTO;
import conselho.api.model.entity.SalaChat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SalaChatMapper {

    private final ModelMapper modelMapper;

    public SalaChatMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SalaChat toEntity(SalaChatRequestDTO dto) {
        return modelMapper.map(dto, SalaChat.class);
    }

    public SalaChatResponseDTO toResponse(SalaChat salaChat) {
        return modelMapper.map(salaChat, SalaChatResponseDTO.class);
    }
}
