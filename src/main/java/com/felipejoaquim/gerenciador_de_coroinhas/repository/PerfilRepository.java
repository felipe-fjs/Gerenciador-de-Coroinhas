package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import java.util.List;
import java.util.Optional;



public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
    Optional<Perfil> findByUsuario(Usuario user);

    List<Perfil> findByNomeContaining(String nome);

    List<Perfil> findByAtivoTrue();

    Optional<Perfil> findByTelefone(String telefone); 
}
