package br.com.uniceuma.backend.exception;

// erro que a gente lança quando alguém tenta cadastrar um email que já existe
public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String email) {
        super("Já existe um usuário cadastrado com o email: " + email);
    }
}
