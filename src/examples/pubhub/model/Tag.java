package examples.pubhub.model;

import java.time.LocalDate;

//consider adding Books data type and as one 
//var of the tags obj and tags as list of tag objects in the books model

public class Tag {

	private String isbn_13;			// International Standard Tag Number, unique
	private String tag_name;
	private LocalDate time_stamp;	// Date of publish to the website
	
	// Constructor used when no date is specified
	public Tag(String isbn, String tag_name) {
		this.isbn_13 = isbn;
		this.tag_name = tag_name;
		this.time_stamp = LocalDate.now();
	}
	
	// Constructor used when a date is specified
	public Tag(String isbn, String tag_name, LocalDate publishDate) {
		this.isbn_13 = isbn;
		this.tag_name = tag_name;
		this.time_stamp = publishDate;
	}
	
	// Default constructor
	public Tag() {
		this.isbn_13 = null;
		this.tag_name = null;
		this.time_stamp = LocalDate.now();
	}
	
	public String getIsbn13() {
		return isbn_13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn_13 = isbn13;
	}

	public String getTagName() {
		return tag_name;
	}

	public void setTagName(String tag_name) {
		this.tag_name = tag_name;
	}


	public LocalDate getTimeStamp() {
		return time_stamp;
	}

	public void setTimeStamp(LocalDate publishDate) {
		this.time_stamp = publishDate;
	}	
}
