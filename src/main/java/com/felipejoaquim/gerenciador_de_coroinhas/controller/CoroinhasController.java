package com.felipejoaquim.gerenciador_de_coroinhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipejoaquim.gerenciador_de_coroinhas.dto.ComunidadeDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.PerfilFuncaoDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.dto.coroinha.NovoCoroinhaUsuarioExistenteDTO;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilFuncaoRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.repository.PerfilRepository;
import com.felipejoaquim.gerenciador_de_coroinhas.service.ComunidadeService;
import com.felipejoaquim.gerenciador_de_coroinhas.service.PerfilFuncaoService;

@RestController
@RequestMapping("/coroinhas")
public class CoroinhasController {
    @Autowired
    private PerfilFuncaoRepository perfilFuncaoRepository;
    @Autowired
    private PerfilFuncaoService perfilFuncaoService;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private ComunidadeService comunidadeService;
     
    @PostMapping("/novo-com-usuario-cadastrado")
    public ResponseEntity<?> novoCoroinhaUsuarioExistente(@RequestBody NovoCoroinhaUsuarioExistenteDTO body) {
        if (perfilRepository.existsById(body.perfilId())) {
            if (!perfilFuncaoService.coroinhaExisteEmComunidade(body.perfilId(), body.comunidadeId())){
                try {
                    perfilFuncaoService.novoCoroinha(body.perfilId(), body.comunidadeId());

                    return ResponseEntity.ok().body("Novo coroinha cadastrado com sucesso!");
                } catch (RuntimeException e) {

                }
            }

            return ResponseEntity.badRequest().body("Usuário já cadastrado como COROINHA!");
        }

        return ResponseEntity.badRequest().body("Perfil de ID = [" + body.perfilId() + "] não encontrado!");
    }
    
    @PostMapping("/novo-com-usuario-nao-cadastrado")
    public ResponseEntity<?> novoCoroinhaUsuarioInexistente(@RequestBody NovoCoroinhaUsuarioExistenteDTO body) {
        if (perfilRepository.existsById(body.perfilId())) {
            if (!perfilFuncaoService.coroinhaExisteEmComunidade(body.perfilId(), body.comunidadeId())){
                try {
                    perfilFuncaoService.novoCoroinha(body.perfilId(), body.comunidadeId());

                    return ResponseEntity.ok().body("Novo coroinha cadastrado com sucesso!");
                } catch (RuntimeException e) {

                }
            }

            return ResponseEntity.badRequest().body("Usuário já cadastrado como COROINHA!");
        }

        return ResponseEntity.badRequest().body("Perfil de ID = [" + body.perfilId() + "] não encontrado!");
    }

    @GetMapping("/todos")
    // autorização para coordenador geral através de wrappers do spring-security
    public ResponseEntity<List<PerfilFuncaoDTO>> todos(){
        try {
            List<PerfilFuncaoDTO> coroinhasDTO = perfilFuncaoService.todosCoroinhas();
            if (coroinhasDTO == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(coroinhasDTO);

        } catch (RuntimeException e ){
            return ResponseEntity.internalServerError().build();
        }
    }

