package conselho.api.controller;

import conselho.api.model.dto.request.ConselhoRequestDTO;
import conselho.api.model.dto.response.ConselhoResponseDTO;
import conselho.api.model.entity.Conselho;
import conselho.api.service.ConselhoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ConselhoController {

    private ConselhoService service;

    @PostMapping
    public ResponseEntity<ConselhoResponseDTO> addConselho(@RequestBody ConselhoRequestDTO conselhoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addConselho(conselhoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConselhoResponseDTO> updateConselho(@PathVariable Long id, @RequestBody ConselhoRequestDTO conselhoRequestDTO) {
        Conselho conselho = new Conselho();
        conselho.setId(id);
        return ResponseEntity.ok(service.update(id, conselho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConselho(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conselho> getConselhoById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ConselhoResponseDTO>> getAllConselhos(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping("/{conselhoId}/professores/{professorId}")
    public ResponseEntity<Boolean> addProfessor(@PathVariable Long conselhoId, @PathVariable Long professorId) {
        boolean result = service.addProfessorConselho(professorId, conselhoId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{conselhoId}/professores/{professorId}")
    public ResponseEntity<Boolean> removeProfessor(@PathVariable Long conselhoId, @PathVariable Long professorId) {
        boolean result = service.removeProfessorConselho(professorId, conselhoId);
        return ResponseEntity.ok(result);
    }

}