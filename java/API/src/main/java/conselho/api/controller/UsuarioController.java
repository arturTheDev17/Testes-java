package conselho.api.controller;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.dto.request.UsuarioRequestDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    // Criar usuário
    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioDTO) {
        UsuarioResponseDTO response = service.save(usuarioDTO);
        return ResponseEntity.status(201).body(response); // Retorna 201 para sucesso na criação
    }

    // Buscar usuário por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO response = service.getById(id);
        return ResponseEntity.ok(response); // Retorna 200 com o usuário
    }

    // Buscar usuário por email
    @GetMapping("/buscar-por-email")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        UsuarioResponseDTO response = service.findByEmail(email);
        return ResponseEntity.ok(response); // Retorna 200 com o usuário
    }

    // Atualizar usuário por ID
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuarioDTO) {
        UsuarioResponseDTO response = service.update(id, usuarioDTO);
        return ResponseEntity.ok(response); // Retorna 200 com o usuário atualizado
    }

    // Atualizar email do usuário
    @PatchMapping("/atualizar-email")
    public ResponseEntity<UsuarioResponseDTO> atualizarEmail(@RequestParam String emailAtual, @RequestParam String novoEmail) {
        UsuarioResponseDTO response = service.updateEmail(emailAtual, novoEmail);
        return ResponseEntity.ok(response); // Retorna 200 com o email atualizado
    }

    // Listar todos os usuários paginados
    @GetMapping("/listar")
    public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuarios(Pageable pageable) {
        Page<UsuarioResponseDTO> usuarios = service.getAll(pageable);
        return ResponseEntity.ok(usuarios); // Retorna a lista de usuários paginados
    }

    // Listar usuários por role paginado
    @GetMapping("/listar-por-role")
    public ResponseEntity<Page<UsuarioResponseDTO>> listarUsuariosPorRole(@RequestParam UsuarioRoles role, Pageable pageable) {
        Page<UsuarioResponseDTO> usuarios = service.findAllByRole(role, pageable);
        return ResponseEntity.ok(usuarios); // Retorna a lista de usuários paginados filtrados por role
    }

    // Deletar usuário por email
    @DeleteMapping("/deletar-por-email")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email) {
        service.deleteByEmail(email);
        return ResponseEntity.noContent().build(); // Retorna 204 para sucesso na exclusão
    }

    // Deletar usuário por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 para sucesso na exclusão
    }
}
