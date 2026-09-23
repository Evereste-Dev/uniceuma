package br.com.uniceuma.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.uniceuma.backend.dto.UsuarioRequestDTO;
import br.com.uniceuma.backend.dto.UsuarioResponseDTO;
import br.com.uniceuma.backend.exception.EmailJaCadastradoException;
import br.com.uniceuma.backend.exception.UsuarioNaoEncontradoException;
import br.com.uniceuma.backend.model.Usuario;
import br.com.uniceuma.backend.repository.UsuarioRepository;

// aqui é onde fica a lógica/regras do CRUD. O Controller não faz nada sozinho,
// ele só chama esses métodos daqui
@Service
public class UsuarioService {

    // o Service usa o Repository para conversar com o banco
    private final UsuarioRepository usuarioRepository;

    // o Spring cria o UsuarioService e entrega o UsuarioRepository pronto
    // aqui automaticamente (isso se chama injeção de dependência)
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // CREATE: cria um novo usuário
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        // regra de negócio: não deixa cadastrar dois usuários com o mesmo email
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException(dto.email());
        }

        Usuario usuario = new Usuario(dto.nome(), dto.email(), dto.senha());
        usuario = usuarioRepository.save(usuario);

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    // READ: lista todos os usuários cadastrados
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    // READ: busca um único usuário pelo id
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        return UsuarioResponseDTO.fromEntity(usuario);
    }

    // UPDATE: atualiza os dados de um usuário existente
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        // primeiro confere se o usuário existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        // depois confere se o novo email já não está sendo usado por OUTRO usuário
        usuarioRepository.findByEmail(dto.email())
                .filter(outro -> !outro.getId().equals(id))
                .ifPresent(outro -> {
                    throw new EmailJaCadastradoException(dto.email());
                });

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        return UsuarioResponseDTO.fromEntity(usuarioRepository.save(usuario));
    }

    // DELETE: remove um usuário pelo id
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }
}
