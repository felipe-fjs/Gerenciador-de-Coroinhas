package com.felipejoaquim.gerenciador_de_coroinhas.entity.enums;

public enum Roles {
    ROLE_CONTROL_MASTER("Admin Global"),
    ROLE_COORDENADOR("Coordenador"),
    ROLE_ARTICULADOR("Articulador"),
    ROLE_COROINHA("Coroinha"),
    ROLE_RESPONSAVEL("Responsável por Coroinha");

    private final String descricao;

    Roles(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
