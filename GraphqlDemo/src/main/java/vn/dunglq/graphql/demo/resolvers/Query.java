package vn.dunglq.graphql.demo.resolvers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import vn.dunglq.graphql.demo.domain.Author;
import vn.dunglq.graphql.demo.domain.Book;
import vn.dunglq.graphql.demo.repository.AuthorRepository;
import vn.dunglq.graphql.demo.repository.BookRepository;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Iterable<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	public Optional<Book> findBookById(Long id) {
		return bookRepository.findById(id);
	}
	
	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}
}
