package vn.dunglq.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import vn.dunglq.graphql.demo.domain.Author;
import vn.dunglq.graphql.demo.domain.Book;
import vn.dunglq.graphql.demo.repository.AuthorRepository;
import vn.dunglq.graphql.demo.repository.BookRepository;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		Author auth = new Author();
		if(!authorRepository.findById((long)1).isPresent()) {
			auth = authorRepository.save(new Author("Dung", "Le"));
		}
		if(!authorRepository.findById((long)2).isPresent()) {
			authorRepository.save(new Author("Linh", "Vb"));
		} 
		if(!authorRepository.findById((long)3).isPresent()) {
			authorRepository.save(new Author("Nguyen", "Nc"));
		}
		
		if(!bookRepository.findById((long)1).isPresent()) {
			bookRepository.save(new Book("Con Đường Tội Ác", 300, auth));
		}
		if(!bookRepository.findById((long)2).isPresent()) {
			bookRepository.save(new Book("Tiểu Thuyết Nhập Hành Bán", 400, auth));
		}
		if(!bookRepository.findById((long)3).isPresent()) {
			bookRepository.save(new Book("Cô Em Sexy", 500, auth));
		}
	}
	
}
