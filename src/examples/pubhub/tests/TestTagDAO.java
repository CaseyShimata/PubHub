package examples.pubhub.tests;

import java.util.List;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;
import examples.pubhub.model.Book;

public class TestTagDAO {
	  
	public static void main(String[] args){


	    TagDAO dao = new TagDAOImpl();
	    List<Book> list = dao.getAllBooksWithTagName("bloop");
	
	    for (int i = 0; i < list.size(); i++){
	      Book t = list.get(i);
	      System.out.println("another book");
	      System.out.println(t.getAuthor());
	    }
	}
}
