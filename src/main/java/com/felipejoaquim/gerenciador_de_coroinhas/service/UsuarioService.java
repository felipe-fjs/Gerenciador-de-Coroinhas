package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado! - UsuarioService"));
    }

    public String novoUsuario(Usuario usuario) {
        if (!this.emailCadastrado(usuario.getEmail())) {
            try {
                String senhaCriptograda = passwordEncoder.encode(usuario.getSenha()); // criptografar
                usuario.setSenha(senhaCriptograda);
                String id = usuarioRepository.save(usuario).getId();
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

    public void desativarUsuario(String usuarioId) {
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
        return usuarioRepository.existsByEmailAndAtivoTrue(email);
    }
} 
