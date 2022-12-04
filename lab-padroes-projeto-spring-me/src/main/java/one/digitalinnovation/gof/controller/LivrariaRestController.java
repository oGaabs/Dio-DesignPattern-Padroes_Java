package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Livro;
import one.digitalinnovation.gof.model.LivroRepository;
import one.digitalinnovation.gof.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class LivrariaRestController {
    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Iterable<Livro>> buscarTodos() {
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Livro> buscarPorIsbn(@PathVariable Long isbn) {
        return ResponseEntity.ok(livroService.buscarPorIsbn(isbn));
    }

    @PostMapping
    public ResponseEntity<Livro> inserir(@RequestBody Livro livro) {
        livroService.inserir(livro);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long isbn, @RequestBody Livro livro) {
        livroService.atualizar(isbn, livro);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deletar(@PathVariable Long isbn) {
        livroService.deletar(isbn);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> quantidadeLivros() {
        return ResponseEntity.ok(livroService.quantidadeLivros());
    }

    @GetMapping("/reservar/{isbn}")
    public ResponseEntity<Void> reservar(Long isbn, Livro livro){
        livroService.reservar(isbn, livro);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/devolver/{isbn}")
    public ResponseEntity<Void> devolver(Long isbn){
        livroService.devolver(isbn);
        return ResponseEntity.ok().build();
    }
}
