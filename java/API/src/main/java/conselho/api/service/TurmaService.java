package conselho.api.service;

import conselho.api.model.dto.mapper.TurmaMapper;
import conselho.api.model.dto.request.TurmaRequestDTO;
import conselho.api.model.dto.response.TurmaResponseDTO;
import conselho.api.model.entity.Turma;
import conselho.api.repository.TurmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper mapper;

    public TurmaResponseDTO adicionarTurma(TurmaRequestDTO dto) {
        Turma turma = mapper.toTurma(dto);
        turma = turmaRepository.save(turma);
        return mapper.toResponse(turma);
    }

    public Turma buscarTurma(UUID uuid) {
        return turmaRepository.findById(uuid).orElseThrow(() -> new NoSuchElementException("Turma não encontrada"));
    }

    public TurmaResponseDTO atualizarTurma(UUID uuid, TurmaRequestDTO dto) {
        Turma turmaExistente = buscarTurma(uuid);
        Turma turmaAtualizada = mapper.toTurma(dto);
        turmaAtualizada.setUuid(turmaExistente.getUuid());
        turmaAtualizada = turmaRepository.save(turmaAtualizada);
        return mapper.toResponse(turmaAtualizada);
    }

    public TurmaResponseDTO buscarTurmaPorNome(String nome) {
        Turma turma = turmaRepository.findByNomeCurso(nome).orElseThrow(() -> new NoSuchElementException("Turma não encontrada"));
        return mapper.toResponse(turma);
    }

    public TurmaResponseDTO buscarTurmaPorId(UUID uuid) {
        Turma turma = buscarTurma(uuid);
        return mapper.toResponse(turma);
    }

    public Page<TurmaResponseDTO> findAll(Pageable pageable) {
        return mapper.toResponse(turmaRepository.findAll(pageable));
    }

    public void removerTurma(UUID uuid) {
        if (!turmaRepository.existsById(uuid)) {
            throw new NoSuchElementException("Turma não encontrada para deletar");
        }
        turmaRepository.deleteById(uuid);
    }
}
