package com.DAWIDWYRWA.biblioteka.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	@NotEmpty(message = "Tytuł nie może być pusty")
	private String title;

	@Column(name = "author")
	@NotEmpty(message = "Autor nie może być pusty")
	private String author;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "loan_date")
	@Temporal(TemporalType.DATE)
	private Date loanDate;

	@Column(name = "return_date")
	@Temporal(TemporalType.DATE)
	private Date returnDate;

	@Column(name = "borrower")
	public String borrower;

	public String status;

	public String borrowerFullName;

	public Book() {
	}

	public Book(String title, String author, int releaseYear, String isRead, String genre,
				String coverLink, String user_id, int pages, User user, Date loanDate) {
		super();
		this.title = title;
		this.author = author;
		this.user = user;
		this.loanDate = loanDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
