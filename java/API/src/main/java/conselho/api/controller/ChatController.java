package conselho.api.controller;

import conselho.api.service.ChatService;
import conselho.api.model.dto.request.MensagemRequestDTO;
import conselho.api.model.dto.response.MensagemResponseDTO;
import conselho.api.model.dto.request.SalaChatRequestDTO;
import conselho.api.model.dto.response.SalaChatResponseDTO;
import conselho.api.model.entity.SalaChat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Endpoint para criar uma nova sala de chat
    @PostMapping("/criar")
    public SalaChatResponseDTO criarSalaChat(@RequestBody @Valid SalaChatRequestDTO salaChatRequestDTO) {
        // Criação da sala de chat com os usuários fornecidos
        SalaChat salaChat = chatService.criarSalaChat(salaChatRequestDTO.getUsuariosIds());

        // Retorna a resposta com o ID da sala e os IDs dos usuários que fazem parte dela
        return new SalaChatResponseDTO(
                salaChat.getId(),
                salaChat.getUsuarios().stream().map(usuario -> usuario.getId()).toList(),
                salaChat.getNome()
        );
    }

    // Endpoint para enviar uma mensagem para a sala de chat
    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensagem(@RequestBody @Valid MensagemRequestDTO mensagemDTO) {
        try {
            // Envia a mensagem através do serviço
            chatService.enviarMensagem(mensagemDTO);
            return ResponseEntity.ok("Mensagem enviada com sucesso");
        } catch (Exception e) {
            // Em caso de erro, retorna o status 500 com a mensagem de erro
            return ResponseEntity.status(500).body("Erro ao enviar a mensagem: " + e.getMessage());
        }
    }

    // Endpoint para obter o histórico de mensagens de uma sala de chat
    @GetMapping("/historico")
    public List<MensagemResponseDTO> obterHistoricoChat(@RequestParam Long salaChatId) {
        // Retorna o histórico de mensagens da sala de chat
        return chatService.obterHistoricoChat(salaChatId);
    }
}
