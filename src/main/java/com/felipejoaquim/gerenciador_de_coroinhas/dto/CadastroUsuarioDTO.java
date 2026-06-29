package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Role;

public record CadastroUsuarioDTO(String email, String senha, Role funcao) {
}
