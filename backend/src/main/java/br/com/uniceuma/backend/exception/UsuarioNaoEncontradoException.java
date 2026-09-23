package br.com.uniceuma.backend.exception;

// erro que a gente lança quando procura um usuário por um id que não existe
public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com id: " + id);
    }
}
