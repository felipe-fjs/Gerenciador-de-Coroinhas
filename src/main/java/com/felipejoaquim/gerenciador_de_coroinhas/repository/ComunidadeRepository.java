package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Comunidade;
import java.util.List;
import java.util.Optional;


public interface ComunidadeRepository extends JpaRepository<Comunidade, Integer>{
    Optional<Comunidade> findByNome(String nome);

    boolean existsByNome(String nome);

    List<Comunidade> findByAtivoTrue();
}
