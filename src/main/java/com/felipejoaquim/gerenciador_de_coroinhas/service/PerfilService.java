package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class PerfilService {
    @Autowired
    private UsuarioRepository usuarioRepository;
        // atualizar para receber DTO
    public Integer novo_perfil(String usuario_id, Perfil novoPerfil){
        // criar perfil
        return null;
    }

        // alterar para receber DTO
    public void editar_perfil(String email, Perfil perfil_atualizado) { // ou buscar por uuid do usuário
        // verificar o que estiver preenchido do perfil_atualiado e atualizar no perfil registrado
    }
    
}
