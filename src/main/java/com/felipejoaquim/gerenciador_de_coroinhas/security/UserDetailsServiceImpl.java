package com.felipejoaquim.gerenciador_de_coroinhas.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.UserDetailsImpl;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioRepository.findByEmail(username)
                                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            return new UserDetailsImpl(usuario);

        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

        public UserDetailsImpl loadUserDetailsImpl(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            return new UserDetailsImpl(usuarioRepository.findByEmail(email)
                                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
        }
        return null;
    }

}
