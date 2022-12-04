package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Livro;

public interface LivroService {

    Iterable<Livro> buscarTodos();

    Livro buscarPorIsbn(Long isbn);

    Long quantidadeLivros();

    void inserir(Livro livro);

    void atualizar(Long isbn, Livro livro);

    void deletar(Long isbn);

    void reservar(Long isbn, Livro livro);

    void devolver(Long isbn);

}
