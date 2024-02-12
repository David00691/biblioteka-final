package com.DAWIDWYRWA.biblioteka.service;

import java.util.Optional;

import com.DAWIDWYRWA.biblioteka.model.Book;

public interface BookService {
	
	Optional<Book> findById(int bookId);

}
