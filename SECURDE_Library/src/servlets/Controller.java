package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import models.BookReservation;
import models.Books;
import models.RoomReservation;
import models.RoomSlot;
import models.Tags;
import models.User;
import security.Security;
import services.BookReservationService;
import services.BooksService;
import services.RoomReservationService;
import services.RoomSlotService;
import services.RoomsServices;
import services.ServerService;
import services.TagsService;
import services.UserService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/book_detail", "/home", "/login_page", "/book_reserve", "/addbook", "/addbookpage",
		"/add_admins_page", "/add_admins", "/edit_book", "/search_room", "/get_room", "/room_reserve", "/new_user", "/search_book" })
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
		request.setAttribute("loggedin", ServerService.CheckLoggedIn(request, response));
		User user = null;
		if ((int) request.getAttribute("loggedin") != -1) {
			
			user = UserService.getUserByID((int) request.getAttribute("loggedin"));
			request.setAttribute("firstname", user.getFirstName());
		}
		String servletPath = request.getServletPath(); // returns either /add,
														// /toggle, /main

		switch (servletPath) {
		case "/login_page":
			request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			break;

		case "/book_detail":
			Books bookdetail = BooksService.getBookById(Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK))));
			request.setAttribute("book", bookdetail);
			if (user != null && (user.getAccessLevel() == 2 || user.getAccessLevel() == 3))
				request.setAttribute("editable", true);
			request.getRequestDispatcher("ProductDetails.jsp").forward(request, response);
			break;

		case "/book_reserve":
			Books bookreserve = BooksService.getBookById(Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK))));
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
			
		case "/search_book":
			response.getWriter().append("Served at: ").append(request.getContextPath());
			List<Books> booklist = new ArrayList<Books>();
			
			
			if(Security.sanitize(request.getParameter("keyword"))==null || "".equals(Security.sanitize(request.getParameter("keyword")))){
				booklist = BooksService.getAllBooks();
			}else{
				System.out.println(Security.sanitize(request.getParameter("keyword")));
				booklist = BooksService.getBooksBySearch(request.getParameter("keyword"));
			}
			
			request.setAttribute("booklist", booklist);
			request.getRequestDispatcher("BorrowBooks.jsp").forward(request, response);
			break;
		case "/addbookpage":
			if (user != null && (user.getAccessLevel() == 2 || user.getAccessLevel() == 3)) {
				request.getRequestDispatcher("AdminAddBook.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
			break;
		case "/addbook":
			if (user != null && (user.getAccessLevel() == 2 || user.getAccessLevel() == 3)) {
				Books b = new Books();

				b.setTitle(Security.sanitize(request.getParameter("book_title")));
				b.setAuthor(Security.sanitize(request.getParameter("author")));
				b.setPublisher(Security.sanitize(request.getParameter("publisher")));
				b.setYear(Integer.parseInt(Security.sanitize(request.getParameter("year"))));
				b.setLocation(Double.parseDouble(Security.sanitize(request.getParameter("location"))));
				b.setStatus(0);
				b.setCreateTime(new GregorianCalendar());
				String typeString = Security.sanitize(request.getParameter("type"));
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
				String[] taglist = Security.sanitize(request.getParameter("tags")).split(",");

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
		case "/edit_book":
			if (user != null && (user.getAccessLevel() == 2 || user.getAccessLevel() == 3)) {

				int id = Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK)));
				Books b = BooksService.getBookById(id);
				request.setAttribute("show_book", b);

				ArrayList<Tags> t = (new TagsService()).getTagsPerBook(b.getIdBooks());
				request.setAttribute("show_book_tags", t);

				request.getRequestDispatcher("AdminEditBook.jsp").forward(request, response);
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

				try {
				User u = new User();

				u.setIdUser(Integer.parseInt(Security.sanitize(request.getParameter("userid"))));
				u.setEmail(request.getParameter("email"));
				u.setFirstName(Security.sanitize(request.getParameter("firstname")));
				u.setMiddleName(Security.sanitize(request.getParameter("middlename")));
				u.setLastName(Security.sanitize(request.getParameter("lastname")));
				int access = 0;
				String accessString = Security.sanitize(request.getParameter("access_level"));
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
				String birthdate = Security.sanitize(request.getParameter("birthdate"));
				String[] dates = birthdate.split("/");
				int month = Integer.parseInt(dates[0]);
				int day = Integer.parseInt(dates[1]);
				int year = Integer.parseInt(dates[2]);
				u.setBirthdate(new GregorianCalendar(year, month, day));
				u.setCreateTime(new GregorianCalendar());
				u.setLastLogin(new GregorianCalendar());
				u.setSecretQuestion(Security.sanitize(request.getParameter("secret_question")));
				String unAnswer = request.getParameter("secret_answer");
				u.setSecretAnswer(Security.createHash(unAnswer));
				u.setUserName(Security.sanitize(request.getParameter("username")));

				String unhashed = request.getParameter("password");

				u.setPassword(Security.createHash(unhashed));

				if(UserService.validateUser(u)){
					UserService.addUser(u);
					UserService.unlockUser(u.getIdUser());
					response.sendRedirect("home");
					}
					else{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Credentials lacking.");
					}
				
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();

				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				} catch(Exception e){
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Sign Up Failed");;
				}


			} else {
				response.sendRedirect("home");
			}
			break;
		case "/search_room":

			if (Security.sanitize(request.getParameter("start")) != "")
				Integer.parseInt(Security.sanitize(request.getParameter("start")));
			if (Security.sanitize(request.getParameter("end")) != "")
				Integer.parseInt(Security.sanitize(request.getParameter("end")));

			request.setAttribute("rooms", RoomsServices.getAllRooms());
			request.getRequestDispatcher("RoomSearch.jsp").forward(request, response);
			break;
		case "/get_room":

			if (Security.sanitize(request.getParameter("start")) != "")
				Integer.parseInt(Security.sanitize(request.getParameter("start")));
			if (Security.sanitize(request.getParameter("end")) != "")
				Integer.parseInt(Security.sanitize(request.getParameter("end")));
			request.setAttribute("room", RoomsServices.getRoomById(Integer.parseInt(Security.sanitize(request.getParameter("idRooms")))));
			request.setAttribute("roomslots",
					RoomSlotService.getRoomSlotByRoom(Integer.parseInt(Security.sanitize(request.getParameter("idRooms")))));
			request.getRequestDispatcher("RoomDetails.jsp").forward(request, response);
			break;
		case "/room_reserve":
			RoomSlot roomreserve = RoomSlotService
					.getRoomSlotById(Integer.parseInt(Security.sanitize(request.getParameter("idRoomSlot"))));
			RoomReservation roomreservation = new RoomReservation();
			roomreservation.setIdRoom(roomreserve.getIdRoom());
			roomreservation.setIdUser((int) request.getAttribute("loggedin"));
			roomreservation.setStartTime(roomreserve.getStart_time());
			roomreservation.setEndTime(roomreserve.getEnd_time());
			request.getRequestDispatcher("ReservationSuccess.jsp").forward(request, response);
			int rrid = RoomReservationService.addRoomReservation(roomreservation);
			roomreservation.setIdRoomReservation(rrid);
			RoomSlotService.updateStatus(roomreserve.getIdRoomSlot(), 1);
			break;

		case "/new_user":
			// TODO Auto-generated method stub

			try {

				User u = new User();

				u.setIdUser(Integer.parseInt(Security.sanitize(request.getParameter("userid"))));
				u.setEmail(request.getParameter("email"));
				u.setFirstName(Security.sanitize(request.getParameter("firstname")));
				u.setMiddleName(Security.sanitize(request.getParameter("middlename")));
				u.setLastName(Security.sanitize(request.getParameter("lastname")));
				int access = 0;
				String accessString = Security.sanitize(request.getParameter("access_level"));
				if ("Faculty".equals(accessString)) {
					access = 1;
				}
				u.setAccessLevel(access);
				String birthdate = Security.sanitize(Security.sanitize(request.getParameter("birthdate")));
				String[] dates = birthdate.split("/");
				int month = Integer.parseInt(dates[0]);
				int day = Integer.parseInt(dates[1]);
				int year = Integer.parseInt(dates[2]);
				u.setBirthdate(new GregorianCalendar(year, month, day));
				u.setCreateTime(new GregorianCalendar());
				u.setLastLogin(new GregorianCalendar());
				u.setSecretQuestion(Security.sanitize(request.getParameter("secret_question")));
				String unAnswer = request.getParameter("secret_answer");
				u.setSecretAnswer(Security.createHash(unAnswer));
				u.setUserName(Security.sanitize(request.getParameter("username")));

				String unhashed = request.getParameter("password");

				u.setPassword(Security.createHash(unhashed));
				if(UserService.validateUser(u)){
				UserService.addUser(u);
				response.sendRedirect("home");}
				else{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Credentials lacking.");
				}
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Sign Up Failed.");;
			}

			break;

		case "/home":
		default:
			if (user != null) {
				request.setAttribute("access", user.getAccessLevel());
			}
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
