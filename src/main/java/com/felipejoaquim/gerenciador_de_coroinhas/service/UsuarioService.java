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
        // verificar existencia de email no DB
        // criptografar senha
        // ... algo mais?
        // salvar
        // retornar o id do usuário criado
        return null;
    }

    public void editarUsuario(Integer usuarioId, Usuario usuarioAtualizado) {

    }

    public void desativarusuario(Integer usuarioId) {

    }

    public Boolean emailCadastrado(String email){ 
        return null;
    }

    public Boolean usuarioAtivo(String email) {
        if (this.emailCadastrado(email)) {
            // fazer lógica
        }
        
        return false;
    }
} 
