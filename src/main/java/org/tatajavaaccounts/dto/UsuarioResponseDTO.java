package org.tatajavaaccounts.dto;

public class UsuarioResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;

    public UsuarioResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }
}
