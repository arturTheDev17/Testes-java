package conselho.api.service;

import conselho.api.model.UsuarioRoles;
import conselho.api.model.dto.mapper.TurmaMapper;
import conselho.api.model.dto.request.TurmaRequestDTO;
import conselho.api.model.dto.response.TurmaResponseDTO;
import conselho.api.model.dto.response.UsuarioResponseDTO;
import conselho.api.model.entity.Turma;
import conselho.api.repository.TurmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TurmaService {

    private TurmaRepository repository;
    private TurmaMapper mapper;

    public TurmaResponseDTO postTurma(TurmaRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toTurma(dto)));
    }

    public Turma buscarTurma(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new
                        NoSuchElementException("Turma não encontrada"));
    }

    public TurmaResponseDTO findById(Long id) {
        return mapper.toResponse(buscarTurma(id));
    }

    public Page<TurmaResponseDTO> findAll(Pageable pageable) {
        return mapper.toResponse(repository.findAll(pageable));
    }

    public void removerTurma(Long id) {
        repository.deleteById(id);
    }

    public TurmaResponseDTO updateTurma(Long id, TurmaRequestDTO dto) {
        return mapper.toResponse(repository.save(mapper.toTurma(dto)));
    }

}
