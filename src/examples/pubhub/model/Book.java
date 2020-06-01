package examples.pubhub.model;

import java.time.LocalDate;

public class Book {

	private String isbn_13;			// International Standard Book Number, unique
	private String title;
	private String author;
	private LocalDate publishDate;	// Date of publish to the website
	
	private double price;
	
	private byte[] content;

	// Constructor used when no date is specified
	public Book(String isbn_13, String title, String author, byte[] content) {
		this.isbn_13 = isbn_13;
		this.title = title;
		this.author = author;
		this.publishDate = LocalDate.now();
		this.content = content;
	}
	
	// Constructor used when a date is specified
	public Book(String isbn_13, String title, String author, byte[] content, LocalDate publishDate) {
		this.isbn_13 = isbn_13;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.content = content;
	}
	
	// Default constructor
	public Book() {
		this.isbn_13 = null;
		this.title = null;
		this.author = null;
		this.publishDate = LocalDate.now();
		this.content = null;
	}
	
	public String getisbn_13() {
		return isbn_13;
	}

	public void setisbn_13(String isbn_13) {
		this.isbn_13 = isbn_13;
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

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
