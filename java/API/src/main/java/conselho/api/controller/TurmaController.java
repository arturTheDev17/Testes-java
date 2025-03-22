package conselho.api.controller;

import conselho.api.model.dto.request.TurmaRequestDTO;
import conselho.api.model.dto.response.TurmaResponseDTO;
import conselho.api.service.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/turmas")
@AllArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping("/criar")
    public ResponseEntity<TurmaResponseDTO> criarTurma(@Valid @RequestBody TurmaRequestDTO dto) {
        return new ResponseEntity<>(turmaService.adicionarTurma(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable UUID id) {
        turmaService.removerTurma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarTurma(@PathVariable UUID id) {
        return new ResponseEntity<>(turmaService.buscarTurmaPorId(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<TurmaResponseDTO> buscarTurmaPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(turmaService.buscarTurmaPorNome(nome), HttpStatus.OK);
    }

    @GetMapping("/buscar-todas")
    public ResponseEntity<Page<TurmaResponseDTO>> buscarTodasTurmas(@PageableDefault(size = 12) Pageable pageable) {
        return new ResponseEntity<>(turmaService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizarTurma(@PathVariable UUID id, @Valid @RequestBody TurmaRequestDTO dto) {
        return new ResponseEntity<>(turmaService.atualizarTurma(id, dto), HttpStatus.OK);
    }
}
