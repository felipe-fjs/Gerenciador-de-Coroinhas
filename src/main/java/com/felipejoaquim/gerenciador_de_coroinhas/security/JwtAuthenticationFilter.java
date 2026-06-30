package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsServiceImpl;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsServiceImpl){
        this.jwtService = jwtService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

                String token = jwtService.getToken(request);
                if (token != null) {
                    
                    String email = jwtService.getEmail(token);
                    
                    UserDetails user = userDetailsServiceImpl.loadUserByUsername(email);
                    var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }                    
                }
                filterChain.doFilter(request, response);
    }
}
