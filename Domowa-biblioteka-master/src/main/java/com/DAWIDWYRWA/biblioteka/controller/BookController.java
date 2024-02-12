package com.DAWIDWYRWA.biblioteka.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.DAWIDWYRWA.biblioteka.model.Book;
import com.DAWIDWYRWA.biblioteka.model.User;
import com.DAWIDWYRWA.biblioteka.repository.BookRepository;
import com.DAWIDWYRWA.biblioteka.repository.UserRepository;
import com.DAWIDWYRWA.biblioteka.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/book/add")
	public String addBookForm(Book book) {
		return "add-book.html";
	}
	
	@GetMapping("/book/update/{id}")
	public String showUpdateBookForm(@PathVariable("id") int bookId, Model model, Principal principal) {
		
		Book book = bookRepository.findById(bookId).orElse(new Book());
		
		String username = principal.getName();
		User currentUser = userRepository.findByUsername(username);
		User bookUser = book.getUser();
		
		if(currentUser.equals(bookUser)) {
			model.addAttribute("book", book);
			return "update-book.html";
		} else {
			List<Book> books = currentUser.getBooks();
			model.addAttribute("books", books);
			return "index.html";
		}
		

		
	}
	
	@Transactional
	@PostMapping("/book/update/{id}")
	public String updateBook(@PathVariable("id") int bookId, @Valid Book book,
			BindingResult result, Model model, Principal principal) {
		
		if(result.hasErrors()) {
			return "update-book.html";
		} else {
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			//book.setUser(user);
			bookRepository.save(book);
			List<Book> books = user.getBooks();
			model.addAttribute("books", books);
			return "index.html";
			
		}
			
	}
	
	@Transactional
	@PostMapping("/book/add")
	public String addBook(@Valid Book book, BindingResult result, Model model, Principal principal) {
	if(result.hasErrors()) {
		return "add-book.html";
	
	} else {
		
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> books = user.getBooks();
		book.setUser(user);
		books.add(book);
		model.addAttribute("books", books);
		return "index.html";
	}	
	
		
	}
	
	@Transactional
	@GetMapping("/book/delete/{id}")
	public String deleteBookById(@PathVariable(value = "id") int bookId, Model model, Principal principal)
	{
		
		Book book = bookRepository.findById(bookId).orElse(new Book());
		String username = principal.getName();
		User currentUser = userRepository.findByUsername(username);
		User bookUser = book.getUser();
		if(currentUser.equals(bookUser)) {
			bookRepository.deleteById(bookId);
		}
		List<Book> books = currentUser.getBooks();
		model.addAttribute("books", books);
		return "index.html";

	}

	// Metoda umożliwiająca bieżącemu użytkownikowi wypożyczenie książki na 14 dni do przodu
	@Transactional
	@PostMapping("/book/borrow/{id}")
	public String borrowBook(@PathVariable("id") int bookId, Model model, Principal principal) {
		// Pobierz bieżącego użytkownika
		String username = principal.getName();
		User user = userRepository.findByUsername(username);

		// Pobierz książkę z repozytorium
		Book book = bookRepository.findById(bookId).orElse(null);

		if (book != null && user != null) {
			// Ustaw datę wypożyczenia na dzisiejszą datę
			book.setLoanDate(new Date());

			// Ustaw datę zwrotu na 14 dni do przodu
			Calendar returnDateCalendar = Calendar.getInstance();
			returnDateCalendar.setTime(new Date());
			returnDateCalendar.add(Calendar.DATE, 14);
			book.setReturnDate(returnDateCalendar.getTime());

			// Ustaw użytkownika jako wypożyczającego książkę
			book.setUser(user);

			// Zapisz zmiany w bazie danych
			bookRepository.save(book);
		}

		// Pobierz listę książek użytkownika
		List<Book> books = user.getBooks();
		model.addAttribute("books", books);

		return "index.html";
	}

}
