package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario get_por_email(String email) {
        if (this.email_cadastrado(email)){
            if (this.usuario_ativo(email)) {
                // econtrar usuário
            
            }
        }

        return null; // ou retornar exceção?
    }

    public String novo_usuario(Usuario usuario) {
        // verificar existencia de email no DB
        // criptografar senha
        // ... algo mais?
        // salvar
        // retornar o id do usuário criado
        return null;
    }

    public void editar_usuario(Integer usuario_id, Usuario usuario_atualizado) {

    }

    public void desativar_usuario(Integer usuario_id) {

    }

    public Boolean email_cadastrado(String email){ 
        return null;
    }

    public Boolean usuario_ativo(String email) {
        if (this.email_cadastrado(email)) {
            // fazer lógica
        }
        
        return false;
    }
} 
