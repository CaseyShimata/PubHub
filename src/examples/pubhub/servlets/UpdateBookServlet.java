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

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBook")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isSuccess = false;
		String isbn_13 = "";

		if (request.getParameter("isbn_13") != null) {
			request.getSession().setAttribute("currentIsbn13", request.getParameter("isbn_13"));
		}
		isbn_13 = request.getSession().getAttribute("currentIsbn13").toString();

		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn_13);
		TagDAO tagDao = DAOUtilities.getTagDAO();
		List<Tag> tags = tagDao.getAllTagsForBookWithISBN(isbn_13);

		if (book != null) {
			book.setTitle(request.getParameter("title"));
			book.setAuthor(request.getParameter("author"));
			book.setPrice(Double.parseDouble(request.getParameter("price")));
		}

		if (request.getParameter("deleteTagButton") != null) {
			String[] parts = request.getParameter("deleteTagButton").split("/");
			isSuccess = tagDao.deleteOneTagForBookWithISBN(parts[0], parts[1]);
		} else if (request.getParameter("editTagButton") != null) {
			String[] parts = request.getParameter("editTagButton").split("/");
			String new_tag_name = request.getParameter("tagTextBox");
			isSuccess = tagDao.updateTagForBookWithISBN(new_tag_name, parts[0], parts[1]);
		} else if (request.getParameter("newTagButton") != null) {
			isSuccess = tagDao.addTagToBookWithISBN(request.getParameter("newTagButton"), request.getParameter("addText"));
		}

		else if (request.getParameter("update") != null) {
			if (book != null) {
				// The only fields we want to be updatable are title, author and price. A new
				// ISBN has to be applied for
				// And a new edition of a book needs to be re-published.
				request.setAttribute("book", book);
				isSuccess = dao.updateBook(book);
			} else {
				// ASSERT: couldn't find book with isbn_13. Update failed.
				isSuccess = false;
			}
		}

		if (isSuccess) {
			request.getSession().setAttribute("message", "update successful");
			request.getSession().setAttribute("messageClass", "alert-success");
			tags = tagDao.getAllTagsForBookWithISBN(isbn_13);
			response.sendRedirect("ViewBookDetails?isbn_13=" + isbn_13);
		} else if (!isSuccess) {
			request.getSession().setAttribute("message", "There was a problem with the update");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}

}
