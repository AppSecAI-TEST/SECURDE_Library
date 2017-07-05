package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BookReservation;
import models.Books;
import models.User;
import services.BookReservationService;
import services.BooksService;
import services.ServerService;
import services.UserService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/book_detail", "/home", "/login_page", "/book_reserve" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("loggedin", ServerService.CheckLoggedIn(request));

		String servletPath = request.getServletPath(); // returns either /add,
														// /toggle, /main

		switch (servletPath) {
		case "/login_page":
			request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			break;
			
		case "/book_detail":
			Books bookdetail = BooksService.getBookById(Integer.parseInt(request.getParameter(Books.COLUMN_IDBOOK)));
			request.setAttribute("book", bookdetail);
			request.getRequestDispatcher("ProductDetails.jsp").forward(request, response);
			break;
			
		case "/book_reserve":
			Books bookreserve = BooksService.getBookById(Integer.parseInt(request.getParameter(Books.COLUMN_IDBOOK)));
			BookReservation bookreservation = new BookReservation();
			bookreservation.setIdBook(bookreserve.getIdBooks());
			bookreservation.setIdUser((int) request.getAttribute("loggedin"));
			long deadline = BookReservationService.addBookReservation(bookreservation);
			GregorianCalendar c = new GregorianCalendar();
			c.setTimeInMillis(deadline);
			bookreservation.setDeadline(c);
			bookreserve.setStatus(1);
			BooksService.updateBook(bookreserve);
			request.setAttribute("reservation", bookreservation);
			request.setAttribute("book", bookreserve);
			request.getRequestDispatcher("ReserveBook.jsp").forward(request, response);
			break;
		case "/home":
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
