package com.felipejoaquim.gerenciador_de_coroinhas.exception;

import java.time.LocalDateTime;

public record ErrorResponse(int status, LocalDateTime timestamp, String message, String error, String path){ }
