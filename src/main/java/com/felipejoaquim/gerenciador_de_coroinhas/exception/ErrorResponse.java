package com.felipejoaquim.gerenciador_de_coroinhas.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, LocalDateTime timestamp, String message, String error, String path){ }
