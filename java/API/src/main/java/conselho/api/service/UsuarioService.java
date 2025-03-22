package conselho.api.service;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.dto.mapper.UsuarioMapper;
import conselho.api.model.dto.request.UsuarioRequestDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.model.entity.Usuario;
import conselho.api.notification.Notification;
import conselho.api.notification.NotificationStatus;
import conselho.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import static conselho.api.notification.NotificationStatus.BOROWED;
import static conselho.api.notification.NotificationStatus.CREATED;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final NotificationService notificationService;

    // Salvar novo usuário com validação de email
    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioDTO) {
        if (existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = mapper.toUsuario(usuarioDTO);
        notificationService.sendNotification(usuario.getNome(), Notification.builder().status(CREATED).message(" Usuário cadastrado").build());
        return mapper.toResponse(repository.save(usuario));
    }

    // Buscar por ID (retorna DTO)
    public UsuarioResponseDTO getById(Long id) {
        return mapper.toResponse(findById(id));
    }

    // Buscar entidade por ID (uso interno)
    public Usuario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }

    // Buscar por email (retorna DTO)
    public UsuarioResponseDTO findByEmail(String email) {
        return mapper.toResponse(getByEmail(email));
    }

    // Buscar entidade por email (uso interno)
    public Usuario getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }

    // Atualizar usuário por ID
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario existente = findById(id);

        // Evitar duplicidade de email se for atualizado
        if (!existente.getEmail().equalsIgnoreCase(usuarioDTO.getEmail()) && existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario atualizado = mapper.toUsuario(usuarioDTO);
        atualizado.setId(id);
        return mapper.toResponse(repository.save(atualizado));
    }

    // Atualizar usuário pelo email (opcional, mantido caso necessário)
    public UsuarioResponseDTO updateByEmail(UsuarioRequestDTO usuarioDTO) {
        Usuario existente = getByEmail(usuarioDTO.getEmail());
        Usuario atualizado = mapper.toUsuario(usuarioDTO);
        atualizado.setId(existente.getId());
        return mapper.toResponse(repository.save(atualizado));
    }

    // Atualizar somente o email
    public UsuarioResponseDTO updateEmail(String emailAtual, String novoEmail) {
        Usuario usuario = getByEmail(emailAtual);

        if (existsByEmail(novoEmail)) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        usuario.setEmail(novoEmail);
        return mapper.toResponse(repository.save(usuario));
    }

    // Listar todos os usuários paginados
    public Page<UsuarioResponseDTO> getAll(Pageable pageable) {
        return mapper.toResponse(repository.findAll(pageable));
    }

    // Listar por role paginado
    public Page<UsuarioResponseDTO> findAllByRole(UsuarioRoles role, Pageable pageable) {
        return mapper.toResponse(repository.findAllByRole(role, pageable));
    }

    // Verificar existência por email
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    // Deletar por email
    public void deleteByEmail(String email) {
        Usuario usuario = getByEmail(email);
        repository.delete(usuario);
    }

    // Deletar por ID
    public void delete(Long id) {
        Usuario usuario = findById(id);
        repository.delete(usuario);
    }

    // Buscar entidade por role + ID (uso interno)
    public Usuario findByRole(UsuarioRoles role, Long usuarioId) {
        return repository.findByRoleAndId(role, usuarioId);
    }
}
