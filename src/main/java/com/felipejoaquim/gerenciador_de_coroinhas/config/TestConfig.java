package com.felipejoaquim.gerenciador_de_coroinhas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.felipejoaquim.gerenciador_de_coroinhas.entity.*;
import com.felipejoaquim.gerenciador_de_coroinhas.service.*;;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UsuarioService userService;

    @Override
	public void run(String... args) throws Exception {

        Usuario user = new Usuario(null, "felipe@gmail.com", "123456");
        
        String password = new BCryptPasswordEncoder().encode(user.getSenha());
        Usuario new_user = new Usuario(null, user.getEmail(), password);

        userService.novoUsuario(new_user);

    }

}
