package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Tag;

import examples.pubhub.model.Book;

public interface TagDAO {
	
	//create
	public boolean addTagToBookWithTitle(String title, Tag tag);
	public boolean addTagToBookWithISBN(Tag tag);
	
	//read
	public List<Tag> getAllTagsForBookWithTitle(String title);
	public List<Tag> getAllTagsForBookWithISBN(String isbn_13);
	

	public List<Book> getAllBooksWithTagName(String tag_name);
	

	//update
	public boolean updateAllTagsNamed(Tag tag);

	public boolean updateTagForBookWithTitle(String title, Tag tag);
	public boolean updateTagForBookWithISBN(Tag tag);
	
	//delete
	public boolean deleteAllTags();
	public boolean deleteAllTagsNamed(String tag_name);
	
	public boolean deleteAllTagsForBookWithTitle(String title);
	public boolean deleteAllTagsForBookWithISBN(String isbn);

}
