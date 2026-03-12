package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {
            String user_email = tokenService.validateToken(token);
            Usuario usuario = usuarioRepository.findByEmail(user_email)
                                      .orElseThrow(() -> new RuntimeException("Erro em doFilterInternal"));
                                      
            UserDetails userPrincipal = userDetailsService.loadUserByUsername(usuario.getEmail());
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request ){
        var authHeader = request.getHeader("Authentication");
        if (authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }

}