package com.felipejoaquim.gerenciador_de_coroinhas.entity.enums;

public enum Role {
    ADMIN("Admin Global"),
    COORDENADOR("Coordenador"),
    ARTICULADOR("Articulador"),
    COROINHA("Coroinha"),
    RESPONSAVEL("Responsável por Coroinha");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
