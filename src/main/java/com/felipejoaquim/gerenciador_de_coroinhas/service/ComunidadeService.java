package com.felipejoaquim.gerenciador_de_coroinhas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.ComunidadeDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.PerfilFuncaoDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.comunidade.ComunidadeUpdateRequestDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Roles;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.ComunidadeRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;

@Service
public class ComunidadeService {
    @Autowired
    private ComunidadeRepository comunidadeRepository;
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;

    public Integer criarComunidade(String nome) {
        if (comunidadeRepository.existsByNome(nome)) {
            throw new RuntimeException("Nome de comunidade já registrado!");
        }
        Comunidade novaComunidade = new Comunidade(null, nome);
        try {
            Integer comunidadeId = comunidadeRepository.save(novaComunidade).getId();

            return comunidadeId;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao registrar nova comunidade ["+nome+"]!");
        }
    }

    public Comunidade buscarPorId(Integer comunidadeId) {
        return comunidadeRepository.findById(comunidadeId).orElseThrow(
            () -> new RuntimeException("Comunidade de ID = "+comunidadeId+" não encontrada!")
        );
    }

    public List<PerfilFuncao> coroinhasDaComunidade(Integer comunidadeId) {
        // retorna os coroinhas de uma determinada comunidade
        if (comunidadeRepository.existsById(comunidadeId)){
            Comunidade comunidade = comunidadeRepository.findById(comunidadeId).get();

            List<PerfilFuncao> coroinhas =  perfilFuncaoRepository.findByComunidadeAndFuncaoAndAtivoTrue(comunidade, Roles.ROLE_COROINHA);

            return coroinhas;
        }
        throw new RuntimeException("Comunidade de ID = "+comunidadeId+" não encontrada!");
    }

    public List<PerfilFuncaoDTO> coroinhasDaComunidadeDTO(String comunidade) {
        if (comunidadeRepository.existsByNome(comunidade)){
            Comunidade comunidad = comunidadeRepository.findByNome(comunidade).get();

            List<PerfilFuncao> coroinhas =  perfilFuncaoRepository.findByComunidadeAndFuncaoAndAtivoTrue(comunidad, Roles.ROLE_COROINHA);
            if (!coroinhas.isEmpty()) {
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
            
            return null;
        }
        throw new RuntimeException("Comunidade de ID = "+comunidade+" não encontrada!");
    }

    public List<PerfilFuncao> articuladoresDaComunidade(Integer comunidadeId) {
        // retorna os articuladores de uma determinada comunidade
        if (comunidadeRepository.existsById(comunidadeId)){
            Comunidade comunidade = comunidadeRepository.findById(comunidadeId).get();

            List<PerfilFuncao> articuladores =  perfilFuncaoRepository.findByComunidadeAndFuncaoAndAtivoTrue(comunidade, Roles.ROLE_ARTICULADOR);

            return articuladores;
        }
        throw new RuntimeException("Comunidade de ID = "+comunidadeId+" não encontrada!");
    }

    public List<ComunidadeDTO> todasComunidadesDTO(){
        List<ComunidadeDTO> comunidades = new ArrayList<>();
        for (Comunidade comunidade: comunidadeRepository.findByAtivoTrue()) {
            comunidades.add(new ComunidadeDTO(comunidade.getNome()));
        }
        return comunidades;
    }

    public boolean alterarComunidade(Integer id, ComunidadeUpdateRequestDTO comunidadeAlterada) {
        try {
            Comunidade comunidade = comunidadeRepository.findById(id).get();
            comunidade.setNome(comunidadeAlterada.nome());
            comunidadeRepository.save(comunidade);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean desativar(Integer id) {
        try {
            Comunidade comunidade = comunidadeRepository.findById(id).get();
            comunidade.setAtivo(false);
            comunidadeRepository.save(comunidade);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}
