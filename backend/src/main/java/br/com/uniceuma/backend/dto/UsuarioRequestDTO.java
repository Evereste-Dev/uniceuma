package br.com.uniceuma.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO = os dados que chegam do front-end quando alguém cria ou edita um usuário.
// Usamos "record" porque é só pra carregar dados, sem precisar escrever
// getters, construtor, equals, etc. (o Java já gera tudo isso sozinho)
public record UsuarioRequestDTO(

        // @NotBlank não deixa o campo vazio nem só com espaços
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        // @Email confere se o texto tem o formato de um email de verdade
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        // @Size exige um tamanho mínimo de caracteres
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha
) {
}
