package br.com.uniceuma.backend.dto;

import br.com.uniceuma.backend.model.Usuario;

// DTO = os dados que a gente devolve pro front-end quando ele pede um usuário.
// Repare que não tem o campo "senha" aqui: nunca devolvemos a senha na resposta!
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email
) {

    // método de apoio para transformar um Usuario (do banco) neste DTO (da resposta)
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
