package conselho.api.service;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.dto.mapper.UsuarioMapper;
import conselho.api.model.dto.request.UsuarioRequestDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.model.entity.Usuario;
import conselho.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository repository;
    private UsuarioMapper mapper;

    public Usuario save( Usuario usuario ) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        return repository.save(usuario);
    }

    public UsuarioResponseDTO save( UsuarioRequestDTO usuario ) {
        return mapper.toResponse(save( mapper.toUsuario( usuario )));
    }

    public UsuarioResponseDTO findByEmail(String email) {
        return mapper.toResponse(getByEmail(email));
    }

    private Usuario getByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Usuario não encontrado"));
    }

    public UsuarioResponseDTO findById(Long id) {
        return mapper.toResponse(getById(id));
    }

    public Usuario getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario não encontrado"));

    }

    public UsuarioResponseDTO update( UsuarioRequestDTO usuario ) {
        Long id = findByEmail(usuario.getEmail()).getId();
        Usuario entidade = mapper.toUsuario(usuario);
        entidade.setId(id);
        return mapper.toResponse(repository.save(entidade));
    }

    public UsuarioResponseDTO updateEmail( String antigoEmail , String novoEmail ) {
        Usuario entidade = getByEmail(antigoEmail);

        if (existsByEmail(novoEmail)) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }

        entidade.setEmail(novoEmail);
        return mapper.toResponse(save(entidade));
    }

    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        return mapper.toResponse(repository.findAll(pageable));
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void deleteByEmail(String email) {
        Usuario usuario = getByEmail(email);
        repository.delete(usuario);
    }

    public Page<UsuarioResponseDTO> findAllByRole(UsuarioRoles usuarioRoles, Pageable pageable) {
        return mapper.toResponse(repository.findAllByRole(usuarioRoles, pageable));
    }

    public Usuario findByRole( UsuarioRoles role , Long usuarioId ) {
        return repository.findByRoleAndId( role , usuarioId );
    }

    public void delete(Long id) {
        Usuario usuario = getById(id);
        repository.delete(usuario);
    }
}

