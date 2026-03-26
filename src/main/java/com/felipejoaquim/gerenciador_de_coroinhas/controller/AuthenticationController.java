package com.felipejoaquim.gerenciador_de_coroinhas.controller;

import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.CadastroCompletoCreateDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.LoginDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Perfil;
import com.felipejoaquim.gerenciador_de_coroinhas.entity.Usuario;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.UsuarioRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.security.TokenService;
import com.felipejoaquim.gerenciador_de_coroinhas.service.PerfilService;
import com.felipejoaquim.gerenciador_de_coroinhas.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilService perfilService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    

    @PostMapping("/registro")
    public ResponseEntity<?> novoUsuario(@RequestBody CadastroCompletoCreateDTO body){ 
        if (usuarioService.emailCadastrado(body.email())){
            return ResponseEntity.badRequest().body("Email já registrado!");
        }

        try{
            Usuario novoUsuario = new Usuario(null, body.email(), body.senha(), null);
            String novoUsuarioId = usuarioService.novoUsuario(novoUsuario);

            Perfil novoPerfil = new Perfil(null, body.nome(), body.dataNascimento(), body.telefone(), body.imgUrl(), novoUsuario);
            Perfil perfil = perfilService.novoPerfil(novoUsuarioId, novoPerfil);

            novoUsuario.setPerfil(perfil);
            usuarioRepository.save(novoUsuario);
            System.out.println("Email registrado agora: " + body.email());

            return ResponseEntity.created(URI.create("novoUsuarioId")).body("Email ["+ body.email()+"] cadastrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        if (usuarioService.emailCadastrado(body.email())){
            Usuario usuario = usuarioService.buscarPorEmail(body.email());
            if (passwordEncoder.matches(body.senha(), usuario.getSenha())){
                String token = tokenService.generateToken(usuario);
                ResponseCookie cookie = ResponseCookie.from("jwt", token)
                                            .httpOnly(true)
                                            .path("/")
                                            .maxAge(Duration.ofMinutes(10))
                                            .sameSite("Strict") 
                                            .build();

                return ResponseEntity.ok()
                                     .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                     .body("Login com sucesso!");   
            }
        }
        return ResponseEntity.badRequest().body("Email não registrado!");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                                    .httpOnly(true)
                                    .path("/")
                                    .maxAge(0)
                                    .sameSite("Strict") 
                                    .build();

        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                                .body("logout relizado com sucesso!");   
    }
}
