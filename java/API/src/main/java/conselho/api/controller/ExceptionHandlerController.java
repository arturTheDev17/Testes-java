package conselho.api.controller;

import conselho.api.model.dto.response.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class ExceptionHandlerController {

    // Tratamento de exceção quando um elemento não é encontrado (por exemplo, ao buscar no banco de dados)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(exception.getMessage(), exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Retorna um 404 (Not Found)
    }

    // Tratamento de exceções relacionadas a erros de validação de entrada (por exemplo, falta de parâmetros ou dados inválidos)
    @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        ErrorResponseDTO error = new ErrorResponseDTO(errorMessage, exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Retorna um 400 (Bad Request)
    }

    // Tratamento para exceções relacionadas a status específicos definidos por ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(exception.getReason(), exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, exception.getStatusCode()); // Retorna o código de status da exceção
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO(exception.getMessage(), exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Retorna um 400 (Bad Request)
    }

    // Tratamento genérico de exceções internas do servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Erro interno do servidor", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna um 500 (Internal Server Error)
    }

    // Tratamento de exceção para erros de integridade de dados, como violação de chave estrangeira no banco de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Violação de integridade de dados", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Retorna um 400 (Bad Request)
    }

    // Tratamento de exceção de timeout (por exemplo, quando uma requisição excede o tempo de espera)
    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponseDTO> handleTimeoutException(TimeoutException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Tempo de espera esgotado", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.REQUEST_TIMEOUT); // Retorna um 408 (Request Timeout)
    }

    // Tratamento de exceção quando a mensagem da requisição não pode ser lida (por exemplo, corpo malformado)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Mensagem de requisição inválida", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Retorna um 400 (Bad Request)
    }

    // Tratamento de exceção quando o tipo de argumento do método é incompatível (por exemplo, tipo errado na URL ou parâmetros)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Tipo de argumento inválido", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Retorna um 400 (Bad Request)
    }

    // Tratamento de exceção quando um handler não é encontrado para a requisição (por exemplo, URL inválida)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        ErrorResponseDTO error = new ErrorResponseDTO("Recurso não encontrado", exception.getClass(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Retorna um 404 (Not Found)
    }
}
