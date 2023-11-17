package br.com.bb.big.bbweekddquizbackend.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<HttpErrorResponse> handleStudentNotFoundException(BaseException exception) {
        log.error(ExceptionUtils.getRootCauseMessage(exception), exception);
        return ResponseEntity
                .status(exception.getStatus())
                .body(new HttpErrorResponse(exception.getStatus().value(), exception.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<HttpErrorResponse> handleRuntimeException(RuntimeException exception) {
        log.error(ExceptionUtils.getRootCauseMessage(exception), exception);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponse(status.value(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        return ResponseEntity
                .status(status)
                .body(new HttpErrorResponse(status.value(), mensagem));
    }

}
