package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.*;
import one.digitalinnovation.gof.service.LivroService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link LivroService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class LivroServiceImpl implements LivroService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private LivroRepository livroRepository;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Livro> buscarTodos() {
		// Buscar todos os Livros.
		return livroRepository.findAll();
	}

	@Override
	public Livro buscarPorIsbn(Long isbn) {
		// Buscar Livro por isbn.
		Optional<Livro> livro = livroRepository.findById(isbn);
		return livro.get();
	}

	@Override
	public Long quantidadeLivros() {
		return livroRepository.count();
	}

	@Override
	public void inserir(Livro livro) {
		livroRepository.save(livro);
	}

	@Override
	public void atualizar(Long isbn, Livro livro) {
		salvarLivro(isbn, livro, false);
	}

	@Override
	public void deletar(Long isbn) {
		// Deletar Livro por isbn.
		livroRepository.deleteById(isbn);
	}


	private void salvarLivro(Long isbn, Livro livro, Boolean reservar) {
		// verificar se o livro ja foi reservado
		if (reservar) {
			Optional<Livro> livroBd = livroRepository.findById(isbn);
			if (livroBd.isPresent() && livroBd.get().getReservado()) { // livro ja reservado, então nao pode ser alterado
				throw new RuntimeException("Livro ja reservado");
			}

			livro.setReservado(true);
		}

		livroRepository.save(livro);
	}

	@Override
	public void reservar(Long isbn, Livro livro) {
		salvarLivro(isbn, livro, true);
	}

	@Override
	public void devolver(Long isbn) {
		Optional<Livro> livroBd = livroRepository.findById(isbn);
		livroBd.ifPresent(l -> {l.setReservado(false); livroRepository.save(l);});
	}
}
