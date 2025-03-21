package conselho.api.controller;

import conselho.api.model.dto.response.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(Exception exception) {
        return new ResponseEntity<>( new ExceptionResponseDTO(exception.getMessage()) , HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>( new ExceptionResponseDTO(exception.getMessage()) , HttpStatus.NOT_FOUND );
    }
}
