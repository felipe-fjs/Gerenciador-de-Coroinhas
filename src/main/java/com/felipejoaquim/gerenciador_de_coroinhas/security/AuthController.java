package com.felipejoaquim.gerenciador_de_coroinhas.security;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.CadastroUsuarioDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.auth.LoginRequestDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.auth.LoginResponseDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.UserDetailsImpl;
import com.felipejoaquim.gerenciador_de_coroinhas.service.UsuarioService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private UsuarioService usuarioService;
    private AuthenticationManager authManager;
    private JwtService jwtService;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authManager, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(), body.senha());
        Authentication auth = authManager.authenticate(usernamePassword);
        
        if (!auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new LoginResponseDTO("EMAIL/USUARIO OU SENHA INVALIDOS"));
        }
        UserDetailsImpl usuario = (UserDetailsImpl) auth.getPrincipal();
        String token = jwtService.generateToken(usuario.getUsuario());
        
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                                .body(new LoginResponseDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro (@RequestBody CadastroUsuarioDTO body) {
        if (!usuarioService.emailCadastrado(body.email())) {
            UUID usuarioID = usuarioService.novoUsuario(body);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email já cadastrado!");
    }

}
