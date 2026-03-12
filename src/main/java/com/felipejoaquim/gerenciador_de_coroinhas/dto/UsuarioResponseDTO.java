package com.felipejoaquim.gerenciador_de_coroinhas.dto;

public record UsuarioResponseDTO(
                    String email,  
                    Boolean ativo, 
                    Boolean verificado) {}
