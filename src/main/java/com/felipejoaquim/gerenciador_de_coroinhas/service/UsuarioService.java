package com.felipejoaquim.gerenciador_de_coroinhas.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.CadastroUsuarioDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.UserDetailsImpl;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.enums.Role;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado! - UsuarioService"));
    }

    public UUID novoUsuario(CadastroUsuarioDTO usuario) {
        if (!this.emailCadastrado(usuario.email())) {
            try {
                String senhaCriptograda = passwordEncoder.encode(usuario.senha()); 
                Usuario novoUsuario = new Usuario(usuario.email(), senhaCriptograda, Role.COROINHA);
                novoUsuario.setPerfil(null);
                UUID id = usuarioRepository.save(novoUsuario).getId();
                return id;
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao registrar novo usuário - UsuarioService : \n " + e);
            }
        }
        throw new RuntimeException("Usuário já cadastrado - UsuarioService");
    }

    public void editarUsuario(String usuarioId, Usuario usuarioAtualizado) {
        // instanciar posteriormente (verificação talvez?)
    }

    public void desativarUsuario(UUID usuarioId) {
        if (usuarioRepository.existsById(usuarioId)) {
            Usuario usuario = usuarioRepository.findById(usuarioId).get();
            usuario.setAtivo(false);
            try {
                usuarioRepository.save(usuario);
            } catch (RuntimeException e ) {
                throw new RuntimeException("Erro ao desativar um usuário - UsuarioService");
            }
        }

        throw new RuntimeException("Erro ao verificar existencia de um usuário - UsuarioService");
    }

    public Boolean emailCadastrado(String email){ 
        return usuarioRepository.existsByEmail(email);
    }

    public Boolean usuarioAtivo(String email) {
        return usuarioRepository.existsByEmail(email);
    }
} 
