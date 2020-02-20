package vn.dunglq.graphql.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.dunglq.graphql.demo.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
