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
    public Perfil novoPerfil(String usuarioId, Perfil novoPerfil){
        if (usuarioRepository.existsById(usuarioId)) {
            Usuario usuario = usuarioRepository.findById(usuarioId).get();
            Perfil perfil = new Perfil(
                null, 
                novoPerfil.getNome(), 
                novoPerfil.getDataNascimento(), 
                novoPerfil.getTelefone(), 
                novoPerfil.getImgUrl(), 
                usuario);
                
                Perfil perfilRegistrado = perfilRepository.save(perfil);
                usuario.setPerfil(perfilRegistrado);
                return perfilRegistrado;
                // } catch (RuntimeException e) {
                //     throw new RuntimeException("Erro ao salvar novo perfil - PerfilService");
                // }
        }
        throw new RuntimeException("Usuario não encontrado - PerfilService");
    }

    public Perfil novoPerfil(Usuario usuario, Perfil novoPerfil){
        
        Perfil perfil = new Perfil(
            null, 
            novoPerfil.getNome(), 
            novoPerfil.getDataNascimento(), 
            novoPerfil.getTelefone(), 
            novoPerfil.getImgUrl(), 
            usuario);
            
            Perfil perfilRegistrado = perfilRepository.save(perfil);
            usuario.setPerfil(perfilRegistrado);
            return perfilRegistrado;
            // } catch (RuntimeException e) {
            //     throw new RuntimeException("Erro ao salvar novo perfil - PerfilService");
            // }
    }

        // alterar para receber DTO
    public void editarPerfil(String email, Perfil perfilAtualizado) { // ou buscar por uuid do usuário
        // verificar o que estiver preenchido do perfil_atualiado e atualizar no perfil registrado
        // implementar depois... preguiça
    }
    
}
