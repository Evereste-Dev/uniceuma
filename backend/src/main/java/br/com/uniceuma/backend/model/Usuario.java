package br.com.uniceuma.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// diz que essa classe representa uma tabela do banco de dados
@Entity
// nome da tabela que vai ser criada: "usuarios"
@Table(name = "usuarios")
public class Usuario {

    // esse campo é o identificador único de cada usuário (a chave primária)
    @Id
    // o valor do id é gerado automaticamente pelo banco (1, 2, 3...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // essa coluna não pode ficar vazia e aceita no máximo 120 caracteres
    @Column(nullable = false, length = 120)
    private String nome;

    // "unique = true" garante que não existam dois usuários com o mesmo email
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    // construtor vazio: o Java/JPA precisa dele para criar o objeto por baixo
    // dos panos quando busca os dados no banco. Sempre deixe ele aqui.
    public Usuario() {
    }

    // construtor que a gente usa no código para criar um usuário novo
    // (repare que não tem "id" aqui, porque quem gera o id é o banco)
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // diz quando dois usuários são considerados "iguais": quando têm o mesmo id
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuario usuario)) {
            return false;
        }
        return id != null && id.equals(usuario.id);
    }

    // sempre que criamos o equals, o Java pede pra criar o hashCode junto
    // (são um par: toda classe que sobrescreve um, sobrescreve o outro)
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
