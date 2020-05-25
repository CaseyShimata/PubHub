package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Implementation for the TagDAO, responsible for querying the database for Tag objects.
 */
public class TagDAOImpl implements TagDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	@Override
	public boolean addTagToBookWithTitle(String title, Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO tags(isbn_13, tag_name, time_stamp) values((select isbn_13 from books where books.title = ?), ?, ?))"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, title);
			stmt.setString(2, tag.getTagName());
			stmt.setDate(3, Date.valueOf(tag.getPublishDate()));
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean addTagToBookWithISBN(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Tags VALUES (?, ?, ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getTagName());
			stmt.setDate(4, Date.valueOf(tag.getPublishDate()));
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Tag> getAllTagsForBookWithTitle(String title) {
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Tags WHERE isbn_13 = (SELECT Books.isbn_13 FROM Books WHERE Title = ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, title);;
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				tag.setPublishDate(rs.getDate("publish_date").toLocalDate());

				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;

	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Tag> getAllTagsForBookWithISBN(String isbn_13) {
		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn_13);;
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				tag.setPublishDate(rs.getDate("publish_date").toLocalDate());

				tags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Book> getAllBooksWithTagName(String tag_name) {
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE isbn_13 IN (SELECT Tags.isbn_13 FROM Tags WHERE tag_name = ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag_name);;
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();

				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));

				books.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	
	}
		
	/*------------------------------------------------------------------------------------------------*/

	//currently just updates tag name of all tags with specific name
	@Override
	public boolean updateAllTagsNamed(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			//use library or make model same names and escape.. sql queries to prone to syntax errors..
			String sql = "UPDATE Tags SET tag_name = ? WHERE Tags.tag_name = ?"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			stmt.setString(2, tag.getTagName());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean updateTagForBookWithTitle(String title, Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			//use library or make model same names and escape.. sql queries to prone to syntax errors..
			String sql = "UPDATE Tags SET tag_name=? WHERE isbn_13 = (SELECT isbn_13 FROM books WHERE title = ?)"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			stmt.setString(2, title);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean updateTagForBookWithISBN(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			//use library or make model same names and escape.. sql queries to prone to syntax errors..
			String sql = "UPDATE Tags SET tag_name=? WHERE isbn_13 = ?"; 
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getTagName());
			stmt.setString(2, tag.getIsbn13());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteAllTags() {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "TRUNCATE TABLE tags";
			stmt = connection.prepareStatement(sql);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteAllTagsNamed(String tag_name) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE Tags WHERE tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag_name);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteAllTagsForBookWithTitle(String title) {
		//get all books with title > join all tags > delete all tags 
		//
		//||
		//get all books with title > get isbn > delete all tags with isbn
		//DELETE FROM tags WHERE isbn_13 = (SELECT isbn_13 FROM books WHERE title = '?')

		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM tags WHERE isbn_13 = (SELECT isbn_13 FROM books WHERE title=?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, title);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
		
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteAllTagsForBookWithISBN(String isbn_13) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn_13);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


	
}
