package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/*
 * This servlet will take you to the homepage for the Book Publishing module (level 100)
 */
@WebServlet("/BookPublishing")
public class BookPublishingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Grab the list of Books from the Database
		BookDAO bookDao = DAOUtilities.getBookDAO();
		TagDAO tagDao = DAOUtilities.getTagDAO();
		request.getSession().setAttribute("tags", tagDao.getAllTags());
		
		//on click set session to selected then load books on session if none load all else load 
		if ((request.getParameter("selectedTag")) != null) {
			request.getSession().setAttribute("selectedTag", (request.getParameter("selectedTag")));

			if ("none".equals(request.getParameter("selectedTag"))) {
				request.getSession().setAttribute("books", bookDao.getAllBooks());
			} else {				
				request.getSession().setAttribute("books",
						tagDao.getAllBooksWithTagName(request.getParameter("selectedTag")));
			}
		} else if ((request.getParameter("selectedTag")) == null) {
			request.getSession().setAttribute("selectedTag", "none");
			request.getSession().setAttribute("books", bookDao.getAllBooks());
		}

		// Populate the list into a variable that will be stored in the session

		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);

	}

}
