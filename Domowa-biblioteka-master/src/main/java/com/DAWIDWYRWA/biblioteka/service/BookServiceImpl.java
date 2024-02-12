package com.DAWIDWYRWA.biblioteka.service;

import java.util.Optional;

import com.DAWIDWYRWA.biblioteka.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAWIDWYRWA.biblioteka.model.Book;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired 
	private BookRepository bookRepository;

	@Override
	public Optional<Book> findById(int bookId) {
        return bookRepository.findById(bookId);
	}

	


}
