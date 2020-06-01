package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;

import examples.pubhub.model.Book;

public interface TagDAO {
	
	//create
	public boolean addTagToBookWithTitle(String title, String new_tag_name);
	public boolean addTagToBookWithISBN(String isbn_13, String new_tag_name);
	
	//read
	public List<Tag> getAllTags();

	public List<Tag> getAllTagsForBookWithTitle(String title);
	public List<Tag> getAllTagsForBookWithISBN(String isbn_13);
	

	public List<Book> getAllBooksWithTagName(String tag_name);
	

	//update
	public boolean updateAllTagsNamed(String new_tag_name, String tag_name);

	public boolean updateTagForBookWithTitle(String new_tag_name, String tag_name, String title);
	public boolean updateTagForBookWithISBN(String new_tag_name, String tag_name, String isbn_13);
	
	//delete
	public boolean deleteAllTags();
	public boolean deleteAllTagsNamed(String tag_name);
	
	public boolean deleteOneTagForBookWithISBN(String isbn_13, String tag_name);

	
	public boolean deleteAllTagsForBookWithTitle(String title);
	public boolean deleteAllTagsForBookWithISBN(String isbn_13);

}
