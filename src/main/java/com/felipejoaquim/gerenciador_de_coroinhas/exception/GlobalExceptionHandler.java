package com.felipejoaquim.gerenciador_de_coroinhas.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
        BadCredentialsException exception, 
        HttpServletRequest request)
        {
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            ErrorResponse error = new ErrorResponse(
                status.value(),
                LocalDateTime.now(), 
                exception.getMessage(), 
                status.getReasonPhrase(), 
                request.getRequestURI());

            return ResponseEntity.status(status).body(error);
    }

    // USUARIO EXCEPTIONS
    @ExceptionHandler(EmailJaCadastradoException.class) 
    public ResponseEntity<ErrorResponse> handleEmailJaCadastradoException(
        EmailJaCadastradoException exception, 
        HttpServletRequest request)
    {
            HttpStatus status = HttpStatus.CONFLICT;
            ErrorResponse error = new ErrorResponse(
                status.value(),
                LocalDateTime.now(), 
                exception.getMessage(), 
                status.getReasonPhrase(), 
                request.getRequestURI());

            return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEmailNaoEncontradoException(EmailNaoEncontradoException exception, 
        HttpServletRequest request)
        {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ErrorResponse error = new ErrorResponse(
                status.value(),
                LocalDateTime.now(), 
                exception.getMessage(), 
                status.getReasonPhrase(), 
                request.getRequestURI()
            );
            return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException exception, 
        HttpServletRequest request) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ErrorResponse error = new ErrorResponse(
                status.value(),
                LocalDateTime.now(), 
                exception.getMessage(), 
                status.getReasonPhrase(), 
                request.getRequestURI());
            return ResponseEntity.status(status).body(error);
        }

    
}
