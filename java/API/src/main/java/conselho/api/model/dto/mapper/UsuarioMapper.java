package conselho.api.model.dto.mapper;

import conselho.api.model.dto.request.UsuarioRequestDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.model.entity.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UsuarioMapper {

    private ModelMapper mapper;

    public UsuarioResponseDTO toResponse( Usuario usuario ) {
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    public Usuario toUsuario(UsuarioRequestDTO usuario) {
        return mapper.map( usuario, Usuario.class );
    }

    public Page<UsuarioResponseDTO> toResponse( Page<Usuario> usuarios ) {

        List<UsuarioResponseDTO> usuariosDtoList = usuarios.getContent().stream()
                .map(this::toResponse).toList();

        return new PageImpl<>(usuariosDtoList, usuarios.getPageable(), usuarios.getTotalPages());

    }

    public List<UsuarioResponseDTO> toResponse( List<Usuario> usuarios ) {
        return usuarios.stream().map( this::toResponse ).toList();
    }
}
