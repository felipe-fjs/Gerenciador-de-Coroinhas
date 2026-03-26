package com.felipejoaquim.gerenciador_de_coroinhas.dto;

import java.time.LocalDate;


public record CadastroCompletoCreateDTO(
                        String nome, 
                        String email,
                        LocalDate dataNascimento,
                        String telefone,
                        String imgUrl, 
                        String senha) { 
                            // devo colocar uma lista das Funcoes ou devo deixar isso por parte de um "FuncoesDTO"?

}
