package conselho.api.controller;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.dto.request.UsuarioRequestDTO;
import conselho.api.model.dto.request.EmailUpdateDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.model.entity.Notificacao;
import conselho.api.service.NotificacaoService;
import conselho.api.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService service;
    private NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarAluno(@Valid @RequestBody UsuarioRequestDTO usuario) {
        return new ResponseEntity<>(service.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarAluno(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<UsuarioResponseDTO> buscarAluno(@Valid @RequestParam @Email String email) {
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarTodosAlunos(@PageableDefault(
            page = 0,
            size = 16
    ) Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/tipo")
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarTodosPorTipo(@RequestParam String role, @PageableDefault(
            page = 0,
            size = 16
    ) Pageable pageable) {
        UsuarioRoles usuarioRole = UsuarioRoles.valueOf(role.toUpperCase());
        return new ResponseEntity<>(service.findAllByRole(usuarioRole, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/notificacao/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable Long id) {
        notificacaoService.removerNotificacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/email")
    public ResponseEntity<Void> deletarAluno(@Valid @Email @RequestParam String email) {
        service.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDTO> atualizarAluno(@Valid @RequestBody UsuarioRequestDTO usuario) {
        return new ResponseEntity<>(service.update(usuario), HttpStatus.OK);
    }

    @PatchMapping("/email")
    public ResponseEntity<UsuarioResponseDTO> atualizarEmail(@Valid @RequestBody EmailUpdateDTO dto) {
        return new ResponseEntity<>(service.updateEmail
                (dto.getEmailAtual(), dto.getNovoEmail()), HttpStatus.OK);
    }


}
