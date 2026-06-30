package com.felipejoaquim.gerenciador_de_coroinhas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.service.UsuarioService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(), body.senha());
        Authentication auth = authManager.authenticate(usernamePassword);
        
        if (!auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("EMAIL/USUARIO OU SENHA INVALIDOS"));
        }

        String token = jwtService.generateToken(body.email());
        
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                                                .httpOnly(true)
                                                .maxAge(86400)
                                                .sameSite("Strict")
                                                .path("/")
                                                .build();


        return ResponseEntity.status(HttpStatus.ACCEPTED)
                                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                .body(new LoginResponseDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro (@RequestBody CadastroUsuarioDTO body) {
        if (!usuarioService.emailCadastrado(body.email())) {
            Usuario novo_usuario = new Usuario(body.email(), body.senha(), body.funcao());
            usuarioService.novoUsuario(novo_usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email já cadastrado!");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout () {
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                                                .httpOnly(true)
                                                .maxAge(0)
                                                .sameSite("Strict")
                                                .path("/")
                                                .build();

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                .body("Logout realizado com sucesso!");
    }

}
