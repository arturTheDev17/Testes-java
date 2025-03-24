package conselho.api.service;

import conselho.api.model.entity.Mensagem;
import conselho.api.model.entity.SalaChat;
import conselho.api.model.entity.Usuario;
import conselho.api.repository.MensagemRepository;
import conselho.api.repository.SalaChatRepository;
import conselho.api.model.dto.request.MensagemRequestDTO;
import conselho.api.model.dto.response.MensagemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final SalaChatRepository salaChatRepository;
    private final MensagemRepository mensagemRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public SalaChat criarSalaChat(List<Long> usuarioIds) {
        List<Usuario> usuarios = usuarioIds.stream().map(usuarioService::findById).toList();
        SalaChat salaChat = new SalaChat();
        salaChat.setUsuarios(usuarios);
        return salaChatRepository.save(salaChat);
    }

    @Transactional
    public void enviarMensagem(MensagemRequestDTO mensagemDTO) {
        SalaChat salaChat = salaChatRepository.findById(mensagemDTO.getSalaChatId())
                .orElseThrow(() -> new RuntimeException("Sala de chat não encontrada"));

        Usuario remetente = usuarioService.findById(mensagemDTO.getRemetenteId());

        Usuario destinatario = null;
        if (salaChat.getUsuarios().size() == 2) {
            destinatario = salaChat.getUsuarios().stream()
                    .filter(usuario -> !usuario.getId().equals(remetente.getId()))
                    .findFirst()
                    .orElse(null);
        }

        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(mensagemDTO.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
        mensagem.setSalaChat(salaChat);

        mensagemRepository.save(mensagem);

        MensagemResponseDTO responseDTO = new MensagemResponseDTO(
                mensagem.getId(), remetente.getId(), destinatario != null ? destinatario.getId() : null,
                mensagem.getConteudo(), mensagem.getDataEnvio().toString()
        );

        messagingTemplate.convertAndSend("/topic/sala/" + salaChat.getId(), responseDTO);
    }


    public SalaChat buscarOuCriarSala(String email1, String email2) {
        // Buscar os usuários no banco
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuarioService.getByEmail(email1));
        usuarios.add(usuarioService.getByEmail(email2));

        // Lógica para verificar se a sala já existe entre esses dois usuários
        Optional<SalaChat> salaChatOptional = salaChatRepository.findByUsuariosIn(usuarios);
        SalaChat salaChat = salaChatOptional.orElse(null);

        // Caso não encontre a sala, cria uma nova e define os usuários
        if (salaChat == null) {
            salaChat = new SalaChat();
            salaChat.setUsuarios(usuarios);  // Define os usuários na nova sala
            salaChat = salaChatRepository.save(salaChat);
        }

        // Retorna a sala encontrada ou criada
        return salaChat;
    }
}
