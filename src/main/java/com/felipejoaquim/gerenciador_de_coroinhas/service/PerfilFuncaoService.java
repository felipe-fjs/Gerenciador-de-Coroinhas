package com.felipejoaquim.gerenciador_de_coroinhas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.PerfilFuncaoDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Roles;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.ComunidadeRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilRepository;

@Service
public class PerfilFuncaoService {
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private ComunidadeRepository comunidadeRepository;

    
    public Boolean coroinhaExisteEmComunidade(Integer perfilId, Integer comunidadeId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(() -> new RuntimeException());
        Comunidade comunidade = comunidadeRepository.findById(comunidadeId).orElseThrow(() -> new RuntimeException());

        return perfilFuncaoRepository.existsByPerfilAndComunidadeAndFuncao(perfil, comunidade, Roles.ROLE_COROINHA);
    }

    // a criação de um novo coroinha sem conta existente terá uma função específica, resolver mais pra frente a lógica
    public Integer novoCoroinha(Integer perfilId, Integer comunidadeId) {
        // cria um novo coroinha para uma comunidade
        try {
            Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(() -> new RuntimeException());
            Comunidade comunidade = comunidadeRepository.findById(comunidadeId).orElseThrow(() -> new RuntimeException());
            PerfilFuncao novoCoroinha = new PerfilFuncao(null, perfil, comunidade, Roles.ROLE_COROINHA);

            Integer coroinhaId = perfilFuncaoRepository.save(novoCoroinha).getId();
            return coroinhaId;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar novo COROINHA - PerfilFuncaoService");
        }
    } 

    public List<PerfilFuncaoDTO> todosCoroinhas(){
        List<PerfilFuncao> coroinhas =perfilFuncaoRepository
                                                .findByFuncaoAndAtivoTrue(Roles.ROLE_COROINHA); 
        if (coroinhas.isEmpty()) {
            return null;
        }

        Map<Comunidade, List<PerfilFuncao>> coroinhasPorComunidade = new HashMap<>();
        // fazer MAP para retornar ao front

        List<PerfilFuncaoDTO> coroinhasDTO = new ArrayList<>();
        for (PerfilFuncao perfilFuncao : coroinhas) {
                coroinhasDTO.add(new PerfilFuncaoDTO(
                                        perfilFuncao.getPerfil().getId(),
                                        perfilFuncao.getFuncao(),
                                        perfilFuncao.getComunidade().getNome(),
                                        perfilFuncao.getAtivo()
                                    ));
        }

        return coroinhasDTO;
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

    public Boolean perfilTemFuncaoAtiva(Integer perfilId, Roles funcao) {
        // verificar se registro existe e se está ativo
        return null;
    }

    public Boolean perfilAtuaNaComunidade(Integer perfilId, Integer ComunidadeId) {
        return null;
    }
}
