package com.DAWIDWYRWA.biblioteka.repository;

import java.util.List;
import java.util.Optional;

import com.DAWIDWYRWA.biblioteka.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.DAWIDWYRWA.biblioteka.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository <Book, Integer> {

	Optional<Book> findById(int bookId);

	List<Book> findByIdAndUser(int bookId, User user);

	List<Book> findByTitleContainingAndUser(String title, User user);

	List<Book> findByAuthorContainingAndUser(String author, User user);

	List<Book> findByBorrowerIsNotNull();



}
