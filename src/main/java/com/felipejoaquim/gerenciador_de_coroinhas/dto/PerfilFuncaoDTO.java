package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Role;

public record PerfilFuncaoDTO(Integer perfilId, 
                              Role funcao, 
                              String comunidadeNome, 
                              Boolean ativo) {

}
