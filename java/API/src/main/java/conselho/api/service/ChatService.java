package conselho.api.service;

import conselho.api.model.entity.Mensagem;
import conselho.api.model.entity.SalaChat;
import conselho.api.model.entity.Usuario;
import conselho.api.repository.MensagemRepository;
import conselho.api.repository.SalaChatRepository;
import conselho.api.repository.UsuarioRepository;
import conselho.api.model.dto.request.MensagemRequestDTO;
import conselho.api.model.dto.response.MensagemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    private final SalaChatRepository salaChatRepository;
    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;

    public SalaChat criarSalaChat(List<Long> usuarioIds) {
        List<Usuario> usuarios = usuarioRepository.findAllById(usuarioIds);
        SalaChat salaChat = new SalaChat();
        salaChat.setUsuarios(usuarios);
        return salaChatRepository.save(salaChat);
    }

    public void enviarMensagem(MensagemRequestDTO mensagemDTO) {
        // Buscar a sala de chat com base no ID
        SalaChat salaChat = salaChatRepository.findById(mensagemDTO.getSalaChatId())
                .orElseThrow(() -> new RuntimeException("Sala de chat não encontrada"));

        // Buscar o remetente
        Usuario remetente = usuarioRepository.findById(mensagemDTO.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Criar a mensagem
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setConteudo(mensagemDTO.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());

        // Determinar o destinatário: se for um chat privado (2 usuários), pega o outro usuário
        if (salaChat.getUsuarios().size() == 2) {
            // Supondo que o destinatário seja o outro usuário na sala
            Usuario destinatario = salaChat.getUsuarios().stream()
                    .filter(usuario -> !usuario.getId().equals(remetente.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

            mensagem.setDestinatario(destinatario);
        } else {
            // Se for um chat em grupo, o destinatário é todos os usuários da sala
            mensagem.setDestinatario(remetente); // Define o destinatário como remetente, pois a mensagem será enviada para todos.
        }

        // Salvar a mensagem
        mensagemRepository.save(mensagem);

        // Enviar a mensagem para todos os usuários da sala
        salaChat.getUsuarios().forEach(usuario ->
                messagingTemplate.convertAndSendToUser(usuario.getId().toString(), "/queue/messages", mensagem)
        );
    }

    public List<MensagemResponseDTO> obterHistoricoChat(Long salaChatId) {
        // Buscar a sala de chat
        SalaChat salaChat = salaChatRepository.findById(salaChatId)
                .orElseThrow(() -> new RuntimeException("Sala de chat não encontrada"));

        // Obter o histórico de mensagens
        List<Mensagem> mensagens = mensagemRepository.findBySalaChat(salaChat);
        return mensagens.stream()
                .map(mensagem -> new MensagemResponseDTO(
                        mensagem.getId(),
                        mensagem.getRemetente().getId(),
                        mensagem.getDestinatario().getId(),
                        mensagem.getConteudo(),
                        mensagem.getDataEnvio()))
                .toList();
    }
}
