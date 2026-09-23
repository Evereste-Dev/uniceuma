package br.com.uniceuma.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniceuma.backend.dto.UsuarioRequestDTO;
import br.com.uniceuma.backend.dto.UsuarioResponseDTO;
import br.com.uniceuma.backend.service.UsuarioService;
import jakarta.validation.Valid;

// essa classe é a "porta de entrada" da API: recebe as requisições HTTP
// e devolve as respostas. Ela não faz nenhuma lógica sozinha, só chama o Service
@RestController
// todas as rotas aqui dentro começam com /usuarios
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // CREATE: POST /usuarios
    // @Valid faz o Spring checar as regras do DTO (@NotBlank, @Email, @Size)
    // antes de entrar no método. @RequestBody pega o JSON enviado e transforma no DTO
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioCriado = usuarioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    // READ: GET /usuarios -> lista todos
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // READ: GET /usuarios/{id} -> busca um só
    // @PathVariable pega o valor que vem na URL (ex: /usuarios/5 -> id = 5)
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // UPDATE: PUT /usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    // DELETE: DELETE /usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
