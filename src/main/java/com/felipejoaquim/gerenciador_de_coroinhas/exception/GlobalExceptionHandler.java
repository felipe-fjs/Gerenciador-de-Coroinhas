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
                status,
                LocalDateTime.now(), 
                "Email ou Senha Incorretos!", 
                status.name(), 
                request.getRequestURI());

            return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailJaCadastradoException.class) 
    public ResponseEntity<ErrorResponse> handleEmailJaCadastradoException(
        BadCredentialsException exception, 
        HttpServletRequest request)
    {
            HttpStatus status = HttpStatus.CONFLICT;
            ErrorResponse error = new ErrorResponse(
                status,
                LocalDateTime.now(), 
                "Email já registrado!", 
                status.name(), 
                request.getRequestURI());

            return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler()
    public ResponseEntity<?> algo(){
        return null;
    }
}
