package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Roles;

public record CadastroUsuarioDTO(String email, String senha, Roles funcao) {
}
