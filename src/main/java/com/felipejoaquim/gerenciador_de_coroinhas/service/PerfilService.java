package com.felipejoaquim.gerenciador_de_coroinhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

@Service
public class PerfilService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

        // atualizar para receber DTO
    public Integer novoPerfil(String usuarioId, Perfil novoPerfil){
        if (usuarioRepository.existsById(usuarioId)) {
            Usuario usuario = usuarioRepository.findById(usuarioId).get();
            Perfil perfil = new Perfil(
                null, 
                novoPerfil.getNome(), 
                novoPerfil.getDataNascimento(), 
                novoPerfil.getTelefone(), 
                novoPerfil.getImgUrl(), 
                usuario);
                try {
                    Integer id = perfilRepository.save(perfil).getId();
                    return id;
                } catch (RuntimeException e) {
                    throw new RuntimeException("Erro ao salvar novo perfil - PerfilService");
                }
        }
        throw new RuntimeException("Usuario não encontrado - PerfilService");
    }

        // alterar para receber DTO
    public void editarPerfil(String email, Perfil perfilAtualizado) { // ou buscar por uuid do usuário
        // verificar o que estiver preenchido do perfil_atualiado e atualizar no perfil registrado
        // implementar depois... preguiça
    }
    
}
