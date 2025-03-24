package conselho.api.controller;

import conselho.api.model.dto.mapper.SalaChatMapper;
import conselho.api.model.entity.SalaChat;
import conselho.api.model.dto.request.MensagemRequestDTO;
import conselho.api.model.dto.response.MensagemResponseDTO;
import conselho.api.model.dto.request.SalaChatRequestDTO;
import conselho.api.model.dto.response.SalaChatResponseDTO;
import conselho.api.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SalaChatMapper salaChatMapper;

    @PostMapping("/criar")
    public ResponseEntity<SalaChatResponseDTO> criarSalaChat(@RequestBody SalaChatRequestDTO salaChatRequestDTO) {
        SalaChat salaChat = chatService.criarSalaChat(salaChatRequestDTO.getUsuariosIds());
        return ResponseEntity.ok(salaChatMapper.toResponse(salaChat)
);
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensagem( @Valid @RequestBody MensagemRequestDTO mensagemDTO) {

            chatService.enviarMensagem(mensagemDTO);
            return ResponseEntity.ok("Mensagem enviada com sucesso");

    }

    @GetMapping("/historico")
    public ResponseEntity<SalaChatResponseDTO> obterSalaChat(
            @RequestParam String email1,
            @RequestParam String email2) {

        // Busca ou cria a sala de chat entre os dois usuários
        SalaChat salaChat = chatService.buscarOuCriarSala(email1, email2);

        return new ResponseEntity<>(salaChatMapper.toResponse(salaChat), HttpStatus.OK);
    }
}
