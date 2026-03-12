package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.PerfilFuncao;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;


public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByEmail(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("Usuário ["+ username +"] não encontrado!"));

        return new org.springframework.security.core.userdetails.
                        User(user.getEmail(), user.getSenha(), loadUserRoles(user));
    }

    public Set<GrantedAuthority> loadUserRoles(Usuario usuario){
        Set<GrantedAuthority> authorities = new HashSet<>();

        if (usuario.getPerfil() != null){
            List<PerfilFuncao> funcoes = perfilFuncaoRepository.findByPerfil(usuario.getPerfil());
            for (PerfilFuncao role : funcoes){
                authorities.add(new SimpleGrantedAuthority(role.getFuncao().name()));
            }
        }
        
        return authorities;
    }

}
