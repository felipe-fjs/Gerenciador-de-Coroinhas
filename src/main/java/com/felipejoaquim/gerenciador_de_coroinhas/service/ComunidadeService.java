package com.felipejoaquim.gerenciador_de_coroinhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.ComunidadeRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;

@Service
public class ComunidadeService {
    @Autowired
    private ComunidadeRepository comunidadeRepository;
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;

    public Comunidade criarComunidade(String nome) {
        return null;
    }

    public Comunidade buscarPorId(Integer comunidadeId) {
        return null;
    }

    public List<PerfilFuncao> coroinhasDaComunidade(Integer comunidadeId) {
        // retorna os coroinhas de uma determinada comunidade
        return null;
    }

    public List<PerfilFuncao> articuladoresDaComunidade(Integer comunidadeId) {
        // retorna os articuladores de uma determinada comunidade
        return null;
    }

    public List<Comunidade> buscarPorNome(String nome) {
        // usar o containing do repository
        return null;
    }
}
