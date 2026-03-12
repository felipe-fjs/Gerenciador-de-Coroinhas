package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Funcoes;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilRepository;

@Service
public class PerfilFuncaoService {
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    

    // a criação de um novo coroinha sem conta existente terá uma função específica, resolver mais pra frente a lógica
    public Integer novoCoroinha(Integer perfilId, Integer comunidadeId) {
        // cria um novo coroinha para uma comunidade
 
        
        return null;
    } 

    public Integer novoArticulador(Integer perfilId, Integer comunidadeId) {
        // cria um novo articulador para uma comunidade
        
        return null;
    } 

    public Integer novoResponsavel(Integer responsavelId, Integer coroinhaId) {
        // cria um novo reponsável por um coroinha para uma comunidade
        
        return null;
    } 

    public Integer novoCoordenador(Integer perfilId) {
        // cria um novo coordenador geral
        
        return null;
    } 

    public void desativarPerfilFuncao(Integer perfil_funcaoId) {

    }

    public void reativarPerfilFuncao(Integer perfil_funcaoId) {

    }

    public Boolean perfilTemFuncaoAtiva(String email, Funcoes funcao) {
        // verificar se registro existe e se está ativo
        return null;
    }

    public Boolean perfilAtuaNaComunidade(String email, String ComunidadeId) {
        return null;
    }
}
