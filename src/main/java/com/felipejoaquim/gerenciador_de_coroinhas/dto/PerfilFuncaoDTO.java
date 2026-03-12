package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Roles;

public record PerfilFuncaoDTO(Integer perfilId, 
                              Roles funcao, 
                              String comunidadeNome, 
                              Boolean ativo) {

}
