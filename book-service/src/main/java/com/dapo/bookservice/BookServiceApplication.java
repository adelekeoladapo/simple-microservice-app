package com.dapo.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/books")
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	private List<Book> bookList = Arrays.asList(
			new Book(1L, "Mel Gibson", "The gods are all dead"),
			new Book(2L, "John Doe", "Introduction to computer science")
	);

	@GetMapping("")
	public List<Book> findAllBooks() {
		return bookList;
	}

	@GetMapping("/{bookId}")
	public Book findBook(@PathVariable Long bookId) {
		return bookList.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
	}

}

class Book {
	private Long id;
	private String author;
	private String title;

	public Book(Long id, String author, String title) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return this.id;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getTitle() {
		return this.title;
	}

}
