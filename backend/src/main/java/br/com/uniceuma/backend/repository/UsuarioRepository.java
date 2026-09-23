package br.com.uniceuma.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniceuma.backend.model.Usuario;

// ao estender JpaRepository, já ganhamos de graça métodos prontos como
// save (salvar), findById (buscar por id), findAll (listar todos) e
// deleteById (deletar). Não precisamos escrever nenhum SQL para isso.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // esses dois métodos abaixo a gente não implementa: só escreve a
    // assinatura e o Spring entende, pelo nome, o que fazer.
    // findByEmail = busca um usuário pelo email
    Optional<Usuario> findByEmail(String email);

    // existsByEmail = verifica se já existe um usuário com esse email
    boolean existsByEmail(String email);
}
