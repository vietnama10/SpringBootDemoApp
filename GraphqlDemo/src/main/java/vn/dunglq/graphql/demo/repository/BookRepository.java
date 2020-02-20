package vn.dunglq.graphql.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dunglq.graphql.demo.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
