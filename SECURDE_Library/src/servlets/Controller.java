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
import models.Tags;
import models.User;
import services.BookReservationService;
import services.BooksService;
import services.ServerService;
import services.TagsService;
import services.UserService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/book_detail", "/home", "/login_page", "/book_reserve", "/addbook", "/addbookpage",
		"/add_admins_page", "/add_admins" })
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
		User user = null;
		if ((int) request.getAttribute("loggedin") != -1) {
			user = UserService.getUserByID((int) request.getAttribute("loggedin"));
		}
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
		case "/addbookpage":
			if (user != null && (user.getAccessLevel() == 3 || user.getAccessLevel() == 4)) {
				request.getRequestDispatcher("AdminAddBook.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
			break;
		case "/addbook":
			if (user != null && (user.getAccessLevel() == 3 || user.getAccessLevel() == 4)) {
				Books b = new Books();

				b.setTitle(request.getParameter("book_title"));
				b.setAuthor(request.getParameter("author"));
				b.setPublisher(request.getParameter("publisher"));
				b.setYear(Integer.parseInt(request.getParameter("year")));
				b.setLocation(Double.parseDouble(request.getParameter("location")));
				b.setStatus(0);
				b.setCreateTime(new GregorianCalendar());
				String typeString = request.getParameter("type");
				int type1 = 0;

				if ("Book".equals(typeString)) {
					type1 = 0;
				} else if ("Magazine".equals(typeString)) {
					type1 = 1;
				} else if ("Thesis".equals(typeString)) {
					type1 = 2;
				}
				b.setType(type1);

				int bookid = BooksService.addBook(b);
				String[] taglist = request.getParameter("tags").split(",");

				for (int i = 0; i < taglist.length; i++) {
					Tags t = new Tags();
					t.setBookid(bookid);
					t.setTag(taglist[i]);
					TagsService tagsservice = new TagsService();
					tagsservice.addTag(t);
				}

				request.setAttribute(Books.COLUMN_IDBOOK, bookid);
				request.getRequestDispatcher("book_detail").forward(request, response);
				;
			} else {
				response.sendRedirect("home");
			}
			break;
		case "/add_admins_page":
			if (user != null && (user.getAccessLevel() == 5)) {
				request.getRequestDispatcher("AdminAccountCreation.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
			break;
		case "/add_admins":
			if (user != null && (user.getAccessLevel() == 5)) {
				User u = new User();

				u.setIdUser(Integer.parseInt(request.getParameter("userid")));
				u.setEmail(request.getParameter("email"));
				u.setFirstName(request.getParameter("firstname"));
				u.setMiddleName(request.getParameter("middlename"));
				u.setLastName(request.getParameter("lastname"));
				int access = 0;
				String accessString = request.getParameter("access_level");
				switch (accessString) {
				case "Library Manager":
					access = 3;
					break;
				case "Library Staff":
					access = 4;
					break;
				case "Library Student Assistant":
					access = 0;
					break;
				default:
					access = 0;
				}

				u.setAccessLevel(access);
				String birthdate = request.getParameter("birthdate");
				String[] dates = birthdate.split("/");
				int month = Integer.parseInt(dates[0]);
				int day = Integer.parseInt(dates[1]);
				int year = Integer.parseInt(dates[2]);
				u.setBirthdate(new GregorianCalendar(year, month, day));
				u.setCreateTime(new GregorianCalendar());
				u.setLastLogin(new GregorianCalendar());
				u.setSecretQuestion(request.getParameter("secret_question"));
				u.setSecretAnswer(request.getParameter("secret_answer"));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));

				UserService.addUser(u);
				UserService.unlockUser(u.getIdUser());

				request.getRequestDispatcher("home").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
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
