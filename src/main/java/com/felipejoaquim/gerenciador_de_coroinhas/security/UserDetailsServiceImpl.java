package com.felipejoaquim.gerenciador_de_coroinhas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.UserDetailsImpl;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.service.UsuarioService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioService.buscarPorEmail(username);

            return new UserDetailsImpl(usuario);

        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

}
