package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import java.time.LocalDate;
import java.util.List;


public record PerfilCompletoResponseDTO(
                        String nome, 
                        String email,
                        LocalDate dataNascmento,
                        String telefone,
                        String imgUrl, 
                        String usuarioId,
                        List<PerfilFuncaoDTO> funcoes) { 
                            // devo colocar uma lista das Funcoes ou devo deixar isso por parte de um "FuncoesDTO"?

}
