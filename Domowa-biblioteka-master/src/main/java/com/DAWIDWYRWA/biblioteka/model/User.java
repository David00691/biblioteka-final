package com.DAWIDWYRWA.biblioteka.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	private List<Book> books;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Collection < Role> roles;

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Collection < Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Collection < Role > getRoles() {
		return roles;
	}

	public void setRoles(Collection < Role > roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		int spaceIndex = username.indexOf(" ");
		if (spaceIndex != -1) {
			return username.substring(0, spaceIndex);
		} else {
			return username;
		}
	}

	public String getLastName() {
		int spaceIndex = username.indexOf(" ");
		if (spaceIndex != -1) {
			return username.substring(spaceIndex + 1);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", books=" + books + ", roles="
				+ roles + "]";
	}

}
