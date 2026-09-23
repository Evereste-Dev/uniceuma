package br.com.uniceuma.backend.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// essa classe fica "escutando" os erros lançados em qualquer Controller da
// aplicação e transforma cada um numa resposta HTTP organizada (com status
// certo e um corpo em JSON), em vez de devolver aquela tela feia de erro
@RestControllerAdvice
public class ApiExceptionHandler {

    // quando um UsuarioNaoEncontradoException é lançado em algum lugar,
    // esse método é chamado e devolve o status 404 (Not Found)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpoErro(ex.getMessage()));
    }

    // quando o email já está cadastrado, devolve o status 409 (Conflict)
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> handleEmailDuplicado(EmailJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpoErro(ex.getMessage()));
    }

    // quando os dados enviados não passam nas validações do DTO (@NotBlank,
    // @Email, @Size), o Spring lança esse erro sozinho e a gente monta uma
    // resposta 400 (Bad Request) listando o que está errado em cada campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(erro -> campos.put(erro.getField(), erro.getDefaultMessage()));

        Map<String, Object> corpo = corpoErro("Dados inválidos");
        corpo.put("campos", campos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpo);
    }

    // método de apoio para montar o corpo padrão da resposta de erro
    // (evita repetir esse código em cada método acima)
    private Map<String, Object> corpoErro(String mensagem) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("timestamp", Instant.now());
        corpo.put("mensagem", mensagem);
        return corpo;
    }
}
