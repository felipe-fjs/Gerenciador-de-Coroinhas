package com.felipejoaquim.gerenciador_de_coroinhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipejoaquim.gerenciador_de_coroinhas.service.UsuarioService;

@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    
}
