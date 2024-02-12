package com.DAWIDWYRWA.biblioteka.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.DAWIDWYRWA.biblioteka.repository.BookRepository;
import com.DAWIDWYRWA.biblioteka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.DAWIDWYRWA.biblioteka.model.Book;
import com.DAWIDWYRWA.biblioteka.model.User;
import com.DAWIDWYRWA.biblioteka.dto.RentedBookDto;

@Controller
public class MainController {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;

	@Autowired
	public MainController(BookRepository bookRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}
	@GetMapping("/")
	public String index(Model model, Principal principal) {

		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> books = user.getBooks();

		model.addAttribute("books", books);
		return "index.html";
	}

	@GetMapping("/search")
	public String search(@RequestParam(value = "id", required = false) Long id,
						 @RequestParam(value = "title", required = false) String title,
						 @RequestParam(value = "author", required = false) String author,
						 Model model, Principal principal) {

		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> books;

		if (id != null) {
			// Wyszukiwanie po ID
			books = bookRepository.findByIdAndUser(Math.toIntExact(id), user);
		} else if (title != null && !title.isEmpty()) {
			// Wyszukiwanie po tytule
			books = bookRepository.findByTitleContainingAndUser(title, user);
		} else if (author != null && !author.isEmpty()) {
			// Wyszukiwanie po autorze
			books = bookRepository.findByAuthorContainingAndUser(author, user);
		} else {
			// Jeśli nie podano żadnych parametrów wyszukiwania, pobierz wszystkie książki użytkownika
			books = user.getBooks();
		}

		model.addAttribute("books", books);
		return "search.html";
	}
/*	@GetMapping("/rented")
	public String rentedBooks(Model model, Principal principal) {
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> rentedBooks = bookRepository.findByBorrower(user);

		// Tworzenie listy DTO dla wypożyczonych książek wraz z imieniem i nazwiskiem osoby wypożyczającej
		List<RentedBookDto> rentedBookDtos = new ArrayList<>();
		for (Book book : rentedBooks) {
			RentedBookDto rentedBookDto = new RentedBookDto();
			rentedBookDto.setTitle(book.getTitle());
			rentedBookDto.setBorrowerFullName(user.getUsername());
			rentedBookDto.setLoanDate(book.getLoanDate()); // Dodaj jeśli chcesz także datę wypożyczenia
			rentedBookDtos.add(rentedBookDto);
		}

		model.addAttribute("rentedBooks", rentedBookDtos);
		return "rented_books"; // Upewnij się, że masz szablon "rented_books.html" odpowiednio zdefiniowany
	}

*/


	/*
	@GetMapping("/search")
	public String rentedBooks(Model model, Principal principal) {

		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> books;
		if( book.get()!= null){
			books=user.getBooks();
		}

		model.addAttribute("books", books);
		return "rented_books.html";
	}
*/
	@GetMapping("/rented")
	public String rentedBooks(Model model, Principal principal) {
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		List<Book> booksWithBorrowedNotNull = bookRepository.findByBorrowerIsNotNull();
/*
		// Tworzenie listy DTO dla wypożyczonych książek wraz z imieniem i nazwiskiem osoby wypożyczającej
		List<RentedBookDto> rentedBookDtos = new ArrayList<>();
		for (Book book : booksWithBorrowedNotNull) {
			RentedBookDto rentedBookDto = new RentedBookDto();
			rentedBookDto.setTitle(book.getTitle());
			rentedBookDto.setAuthor(book.getTitle());
			rentedBookDto.setBorrowerFullName(user.getUsername());
			rentedBookDto.setLoanDate(book.getLoanDate()); // Dodaj jeśli chcesz także datę wypożyczenia
			rentedBookDtos.add(rentedBookDto);
		}*/
		for (Book book : booksWithBorrowedNotNull) {
			book.borrowerFullName = userRepository.findById(Integer.parseInt(book.borrower)).get().getUsername();
			book.status = "---";
			if(new Date().after(book.getReturnDate()) )
				book.status = "przekroczono";

		}
		model.addAttribute("rentedBooks", booksWithBorrowedNotNull);
		return "rented_books";
	}
}




