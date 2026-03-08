package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import java.util.List;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Funcoes;



public interface PerfilFuncaoRepository extends JpaRepository<PerfilFuncao, Integer>{
    List<PerfilFuncao> findByPerfil(Perfil profile);

    List<PerfilFuncao> findByComunidade(Comunidade comunidade);
    List<PerfilFuncao> findByComunidadeAndFuncao(Comunidade comunidade, Funcoes role);


    List<PerfilFuncao> findByPerfilAndComunidade(Perfil profile, Comunidade comunidade);
    boolean existsByPerfilAndComunidadeAndFuncao(Perfil profile, Comunidade comunidade, Funcoes role);

    List<PerfilFuncao> findByPerfilAndActiveTrue(Perfil profile);
    List<PerfilFuncao> findByComunidadeAndActiveTrue(Comunidade comunidade);
    List<PerfilFuncao> findByComunidadeAndFuncaoAndActiveTrue(Comunidade comunidade, Funcoes role);

    long countByComunidade(Comunidade comunidade);
    long countByFuncao(Funcoes role);
    long countByComunidadeAndFuncao(Comunidade comunidade, Funcoes role);

}
