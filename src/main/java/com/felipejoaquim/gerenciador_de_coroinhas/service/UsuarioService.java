package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public String novoUsuario(Usuario usuario) {
        if (!this.emailCadastrado(usuario.getEmail())) {
            String senhaCriptograda = usuario.getSenha(); // criptografar
            usuario.setSenha(senhaCriptograda);
            try {
                String id = usuarioRepository.save(usuario).getId();
                return id;
            } catch (RuntimeException e) {
                throw new RuntimeException("Erro ao registrar novo usuário - UsuarioService");
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
