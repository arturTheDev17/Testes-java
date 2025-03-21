package conselho.api.controller;

import conselho.api.model.dto.request.NotificacaoRequestDTO;
import conselho.api.model.dto.response.NotificacaoResponseDTO;
import conselho.api.service.NotificacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao")
@Controller
public class NotificacaoController {

    private NotificacaoService service;

    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> criarNotificacao(@RequestBody NotificacaoRequestDTO dto) {
        return new ResponseEntity<>( service.postNotificacao( dto ) , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> buscarNotificacao( @PathVariable Long id ) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<NotificacaoResponseDTO>> buscarTodasNotificacao(@PageableDefault(
            page = 0,
            size = 16
    ) Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<NotificacaoResponseDTO> atualizarNotificacao(@PathVariable Long id, @RequestBody NotificacaoRequestDTO dto) {
        return new ResponseEntity<>(service.updateNotificacao(id, dto), HttpStatus.OK);
    }

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacaoController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Notificação para todos os usuários
    @MessageMapping("/sendMessage")
    @SendTo("/topico/global")
    public String sendGlobalMessage(String message) {
        System.out.println("Mensagem Global: " + message);
        return message;
    }

    // Notificação para uma turma específica
    @MessageMapping("/sendToClass/{turmaId}")
    public void sendClassMessage(String message, @org.springframework.messaging.handler.annotation.DestinationVariable String turmaId) {
        System.out.println("Mensagem para turma " + turmaId + ": " + message);
        messagingTemplate.convertAndSend("/topico/turma/" + turmaId, message);
    }

    // Notificação para professores e representantes de uma turma específica
    @MessageMapping("/sendToClassManagers/{turmaId}")
    public void sendClassManagersMessage(String message, @org.springframework.messaging.handler.annotation.DestinationVariable String turmaId) {
        System.out.println("Mensagem para gestores da turma " + turmaId + ": " + message);
        messagingTemplate.convertAndSend("/topico/turma/" + turmaId + "/preConselho", message);
    }
}