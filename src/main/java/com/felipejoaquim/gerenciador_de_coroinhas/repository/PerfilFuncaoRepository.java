package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import java.util.List;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Roles;



public interface PerfilFuncaoRepository extends JpaRepository<PerfilFuncao, Integer>{
    List<PerfilFuncao> findByPerfil(Perfil perfil);

    List<PerfilFuncao> findByComunidade(Comunidade comunidade);
    List<PerfilFuncao> findByComunidadeAndFuncao(Comunidade comunidade, Roles role);

    
    List<PerfilFuncao> findByPerfilAndComunidade(Perfil perfil, Comunidade comunidade);
    List<PerfilFuncao> findByFuncaoAndAtivoTrue(Roles funcao);
    boolean existsByPerfilAndComunidadeAndFuncao(Perfil perfil, Comunidade comunidade, Roles role);

    List<PerfilFuncao> findByPerfilAndAtivoTrue(Perfil perfil);
    List<PerfilFuncao> findByComunidadeAndAtivoTrue(Comunidade comunidade);
    List<PerfilFuncao> findByComunidadeAndFuncaoAndAtivoTrue(Comunidade comunidade, Roles role);

    long countByComunidade(Comunidade comunidade);
    long countByFuncao(Roles role);
    long countByComunidadeAndFuncao(Comunidade comunidade, Roles role);

}
