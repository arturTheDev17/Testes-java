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

@RestController
@RequestMapping("/turma")
@AllArgsConstructor
public class TurmaController {

    private TurmaService service;

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> criarTurma(@RequestBody TurmaRequestDTO dto) {
        return new ResponseEntity<>( service.postTurma( dto ) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarTurma( @PathVariable Long id ) {
        return new ResponseEntity<>( service.findById( id ) , HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<Page<TurmaResponseDTO>> buscarTodasTurmas(@PageableDefault(
            size = 12
    )Pageable pageable) {
        return new ResponseEntity<>( service.findAll( pageable ) , HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTurma( @PathVariable Long id ) {
        service.removerTurma( id );
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @PutMapping("{id}")
    public ResponseEntity<TurmaResponseDTO> atualizarTurma(@PathVariable Long id, @RequestBody TurmaRequestDTO dto) {
        return new ResponseEntity<>(service.updateTurma(id, dto), HttpStatus.OK);
    }
}
