package conselho.api.model.dto.mapper;

import conselho.api.model.dto.request.MensagemRequestDTO;
import conselho.api.model.dto.response.MensagemResponseDTO;
import conselho.api.model.entity.Mensagem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MensagemMapper {

    private final ModelMapper modelMapper;

    public MensagemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Mensagem toEntity(MensagemRequestDTO dto) {
        return modelMapper.map(dto, Mensagem.class);
    }

    public MensagemResponseDTO toResponse(Mensagem mensagem) {
        return modelMapper.map(mensagem, MensagemResponseDTO.class);
    }
}
