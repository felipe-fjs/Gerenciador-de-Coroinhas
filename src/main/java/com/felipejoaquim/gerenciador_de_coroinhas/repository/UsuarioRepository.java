package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    boolean existsByEmail(String email); // usado para verificações de input ou necessários

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailAndAtivoTrue(String email);
}
