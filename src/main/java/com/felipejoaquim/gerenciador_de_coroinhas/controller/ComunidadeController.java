package com.felipejoaquim.gerenciador_de_coroinhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.comunidade.ComunidadeDeactiveRequestDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.comunidade.ComunidadeUpdateRequestDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.comunidade.NovaComunidadeRequestDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.ComunidadeRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.service.ComunidadeService;


@RestController
@RequestMapping("/comunidades")
public class ComunidadeController {
    @Autowired
    private ComunidadeRepository comunidadeRepository;
    @Autowired
    private ComunidadeService comunidadeService;
    
    @PostMapping("/novo")
    public ResponseEntity<?> novaComunidade(@RequestBody NovaComunidadeRequestDTO body){
        Integer comunidadeId = comunidadeService.criarComunidade(body.nome());
        return ResponseEntity.ok().body("Nova comunidade registrada no ID = [" + comunidadeId + "]");
    }

    @GetMapping("/todos") 
    public ResponseEntity<?> todasComunidades() {

        return ResponseEntity.ok().body(new NovaComunidadeRequestDTO("Algo"));
        // return ResponseEntity.ok().body(comunidadeService.todasComunidadesDTO());
    }

    public ResponseEntity<?> alterarComunidade(@RequestBody ComunidadeUpdateRequestDTO body) {
        boolean alterado = comunidadeService.alterarComunidade(body.id(), body);
        if (alterado) {
            return ResponseEntity.ok().body("Comunidade alterada com sucesso!");
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> desativarComunidade(@RequestBody ComunidadeDeactiveRequestDTO body) {
        boolean desativado = comunidadeService.desativar(body.id());
        if (desativado) {
            return ResponseEntity.ok().body("Comunidade alterada com sucesso!");
        }
        return ResponseEntity.badRequest().build();
    }

}

/**
 *  - CRUD comunidade: 
 *      - CREATE
 *      - READ 
 *      - UPDATE 
 *      - DELETE (desativar)
 */
