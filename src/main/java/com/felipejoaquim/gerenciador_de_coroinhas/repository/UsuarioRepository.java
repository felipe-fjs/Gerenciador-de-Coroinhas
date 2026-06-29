package com.felipejoaquim.gerenciador_de_coroinhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    boolean existsByEmail(String email); // usado para verificações de input ou necessários

    Optional<Usuario> findByEmail(String email);

}
