package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import custom_errors.ExpireException;
import custom_errors.PasswordMismatch;
import models.BookReservation;
import models.Books;
import models.RoomReservation;
import models.RoomSlot;
import models.Rooms;
import models.Reviews;
import models.Tags;
import models.User;
import security.Security;
import services.BookReservationService;
import services.BooksService;
import services.RoomReservationService;
import services.RoomSlotService;
import services.RoomsServices;
import services.ReviewsService;
import services.ServerService;
import services.TagsService;
import services.UserService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/book_detail", "/home", "/login_page", "/book_reserve", "/addbook", "/addbookpage",
		"/add_admins_page", "/add_admins", "/edit_book", "/search_room", "/get_room", "/room_reserve", "/new_user",
		"/search_book", "/delete_book", "/update_book", "/login", "/signup_page", "/logout", "/myaccount",
		"/change_pass", "/unlock_users_page", "/unlock_users", "/forget_password_page", "/secret_question",
		"/answer_question", "/temp_pass_change", "/addreview", "/commentreview", "/delete_reserve", "/review_detail",
		"/exportbooks", "/exportrooms", "/validate_email", "/validate_email_page" })

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(Controller.class);
	final static Logger booklogger = Logger.getLogger(BooksService.class);
	final static Logger reviewlogger = Logger.getLogger(ReviewsService.class);

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
		String pass = "";
		// TODO Auto-generated method stub
		request.setAttribute("loggedin", ServerService.CheckLoggedIn(request, response));
		User user = null;
		if ((int) request.getAttribute("loggedin") != -1) {

			user = UserService.getUserByID((int) request.getAttribute("loggedin"));
			request.setAttribute("firstname", user.getFirstName());
		}
		String servletPath = request.getServletPath(); // returns either /add,
														// /toggle, /main

		String user_info;

		if (user == null) {
			user_info = "[ANONYMOUS USER]";
		} else {
			user_info = "[" + user.getIdUser() + " | " + user.getAccesString() + "] " + user.getUserName();
		}

		if ("/temp_pass_change".equals(servletPath)) {
			request.getRequestDispatcher("MyAccount.jsp").forward(request, response);
			return;
		} else if (user != null && user.getStatus() == User.STATUS_TEMP && !"/change_pass".equals(servletPath)
				&& !"/logout".equals(servletPath)) {
			response.sendRedirect("temp_pass_change");
			return;
		}

		if ("/validate_email_page".equals(servletPath)) {
			request.getRequestDispatcher("ValidateEmail.jsp").forward(request, response);
			return;
		} else if (user != null && user.getEmailVal() != User.STATUS_VALID && !"/validate_email".equals(servletPath)
				&& !"/logout".equals(servletPath)) {
			response.sendRedirect("validate_email_page");
			return;
		}
		switch (servletPath) {
		case "/temp_pass_change":
			break;
		case "/exportbooks":

			if (user != null
					&& (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.ADMINISTRATOR)) {

				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=\"StatusBooks.csv\"");
				try {
					OutputStream outputStream = response.getOutputStream();
					ArrayList<Books> books = BooksService.getAllBooks();
					String outputResult = Books.COLUMN_IDBOOK + "," + Books.COLUMN_TITLE + "," + Books.COLUMN_AUTHOR
							+ "," + Books.COLUMN_STATUS + "\n";

					for (int i = 0; i < books.size(); i++) {
						Books book = books.get(i);
						outputResult += book.getIdBooks() + "," + book.getTitle() + "," + book.getAuthor() + ","
								+ book.getStatusString() + "\n";
					}

					outputStream.write(outputResult.getBytes());
					outputStream.flush();
					outputStream.close();
					logger.info(user_info + " downloaded books status.");
				} catch (Exception e) {
					logger.error(user_info + " failed to download books status.");
				}
			} else {
				logger.warn("UNAUTHORIZED USER : " + user_info + " attempted to download books status.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
			}

			break;
		case "/exportrooms":

			if (user != null
					&& (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.ADMINISTRATOR)) {

				response.setContentType("text/csv");
				response.setHeader("Content-Disposition", "attachment; filename=\"StatusRooms.csv\"");
				try {
					OutputStream outputStream = response.getOutputStream();
					ArrayList<RoomSlot> rooms = RoomSlotService.getAllRoomSlot();
					ArrayList<Rooms> roomData = RoomsServices.getAllRooms();

					String outputResult = RoomSlot.COLUMN_IDROOM + "," + Rooms.COLUMN_NAME + ","
							+ RoomSlot.COLUMN_STARTTIME + "," + RoomSlot.COLUMN_ENDTIME + "," + RoomSlot.COLUMN_STATUS
							+ "\n";

					for (int i = 0; i < rooms.size(); i++) {
						RoomSlot room = rooms.get(i);
						outputResult += room.getIdRoom() + "," + RoomsServices.getRoomById(room.getIdRoom()).getName()
								+ "," + room.getStart_time() + "," + room.getEnd_time() + "," + room.getStatusString()
								+ "\n";
					}

					outputStream.write(outputResult.getBytes());
					outputStream.flush();
					outputStream.close();
					logger.info(user_info + " downloaded rooms status.");
				} catch (Exception e) {
					logger.error(user_info + " failed to download rooms status.");
				}
			} else {
				logger.warn("UNAUTHORIZED USER : " + user_info + " attempted to download rooms status.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
			}

			break;
		case "/logout":
			if (user != null) {

				Cookie[] cookielist = request.getCookies();
				Cookie c = null;
				if (cookielist != null)
					for (int i = 0; i < cookielist.length; i++) {
						c = cookielist[i];
						if (c.getName().equals(Security.COOKIE_NAME)) {
							c.setMaxAge(0);
							response.addCookie(c);
						}
						;
					}

				logger.info(user_info + " logged out.");
				request.setAttribute("loggedin", -1);
				response.sendRedirect("LoggedOut.jsp");

			} else {
				response.sendRedirect("home");
			}
			break;

		case "/signup_page":

			if (user == null)
				request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			else
				response.sendRedirect("home");

			break;

		case "/myaccount":
			if (user != null) {
				request.getRequestDispatcher("MyAccount.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}
			break;

		case "/login_page":
			if (user == null)
				request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			else
				response.sendRedirect("home");

			break;
		case "/login":
			if (user == null) {

				String username = Security.sanitize(request.getParameter("username"));
				String password = request.getParameter("password");

				int id;
				try {
					id = UserService.logInUser(username, password);

					if (id != -1) {
						request.setAttribute("loggedin", id);
						User u = UserService.getUserByID(id);
						Cookie c = new Cookie(Security.COOKIE_NAME, u.getSalt() + ":" + id + "");
						c.setMaxAge(60 * 60 * 1);

						user_info = "[" + u.getIdUser() + " | " + u.getAccesString() + "] " + u.getUserName();

						response.addCookie(c);
						response.sendRedirect("home");

					} else {
						request.setAttribute("loggedin", -10);
						request.getRequestDispatcher("LogIn.jsp").forward(request, response);
					}

				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (custom_errors.LockoutException e) {
					// TODO Auto-generated catch block
					response.sendError(HttpServletResponse.SC_FORBIDDEN,
							"User has been locked out. Please contact the administrator.");
				} catch (ExpireException e) {
					// TODO Auto-generated catch block
					response.sendError(HttpServletResponse.SC_FORBIDDEN,
							"Temporary unlocking of user has expired. Please contact the administrator.");

				}

			} else {
				response.sendRedirect("home");
			}
			break;
		case "/book_detail":
			Books bookdetail = BooksService
					.getBookById(Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK))));
			request.setAttribute("book", bookdetail);
			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF))
				request.setAttribute("editable", true);
			request.getRequestDispatcher("ProductDetails.jsp").forward(request, response);
			break;
		case "/book_reserve":
			if (user != null) {
				Books bookreserve = BooksService
						.getBookById(Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK))));
				BookReservation bookreservation = new BookReservation();
				bookreservation.setIdBook(bookreserve.getIdBooks());
				bookreservation.setIdUser(user.getIdUser());
				long deadline = BookReservationService.addBookReservation(bookreservation);
				GregorianCalendar c = new GregorianCalendar();
				c.setTimeInMillis(deadline);
				bookreservation.setDeadline(c);
				bookreserve.setStatus(Books.RESERVED);
				try {
					BooksService.updateBook(bookreserve);
					request.setAttribute("reservation", bookreservation);
					request.setAttribute("book", bookreserve);
					logger.info(
							user_info + " reserved book [" + bookreserve.getIdBooks() + "] " + bookreserve.getTitle());
					request.getRequestDispatcher("ReserveBook.jsp").forward(request, response);

				} catch (SQLException e) {

					booklogger.error("DATABASE FAILURE: " + user_info + " attempted to reserve book ["
							+ bookreserve.getTitle() + "]");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					e.printStackTrace();
				}
			} else {
				logger.warn(user_info + " attempted book reservation.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not logged in.");
			}
			break;
		case "/review_detail":
			List<Reviews> reviewlist = new ArrayList<Reviews>();
			try {
				reviewlist = ReviewsService.getReviewsByBook(Integer.parseInt(request.getParameter("idBook")));
			} catch (SQLException e) {
				booklogger.error("DATABASE FAILURE: " + user_info + " attempt at retrieving reviews. BookID: ["
						+ Security.sanitize(request.getParameter("idBook")) + "]");
				e.printStackTrace();
			}
			request.setAttribute("reviewlist", reviewlist);
			request.getRequestDispatcher("ViewReviews.jsp").forward(request, response);
			break;
		case "/search_book":
			response.getWriter().append("Served at: ").append(request.getContextPath());
			List<Books> booklist = new ArrayList<Books>();

			try {
				if (Security.sanitize(request.getParameter("keyword")) == null
						|| "".equals(Security.sanitize(request.getParameter("keyword")))) {
					booklist = BooksService.getAllBooks();
				} else {
					booklist = BooksService.getBooksBySearch(request.getParameter("keyword"));

				}
			} catch (SQLException e) {
				booklogger.error("DATABASE FAILURE: " + user_info + " attempt at retrieving books. Keyword : ["
						+ Security.sanitize(request.getParameter("keyword")) + "]");
				e.printStackTrace();
			}
			request.setAttribute("booklist", booklist);
			request.getRequestDispatcher("BorrowBooks.jsp").forward(request, response);
			break;
		case "/addbookpage":

			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {
				request.getRequestDispatcher("AdminAddBook.jsp").forward(request, response);
			} else {
				logger.warn(user_info + " attempted to access add book page without proper access level.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
			}
			break;
		case "/addbook":

			pass = request.getParameter("reauth_pass");

			try {
				if (!Security.validatePassword(pass, user.getPassword())) {
					logger.warn(user_info + " failed reauth for adding book.");
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"Re entered password is invalid or incorrect.");
					return;
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			;

			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {
				Books b = new Books();

				b.setTitle(Security.sanitize(request.getParameter("book_title")));
				b.setAuthor(Security.sanitize(request.getParameter("author")));
				b.setPublisher(Security.sanitize(request.getParameter("publisher")));
				b.setYear(Integer.parseInt(Security.sanitize(request.getParameter("year"))));
				b.setLocation(Double.parseDouble(Security.sanitize(request.getParameter("location"))));
				b.setStatus(Books.AVAILABLE);
				b.setCreateTime(new GregorianCalendar());
				String typeString = Security.sanitize(request.getParameter("type"));
				int type1 = Books.UNKNOWN;

				if ("Book".equals(typeString)) {
					type1 = Books.BOOK;
				} else if ("Magazine".equals(typeString)) {
					type1 = Books.MAGAZINE;
				} else if ("Thesis".equals(typeString)) {
					type1 = Books.THESIS;
				}
				b.setType(type1);

				int bookid;
				try {
					bookid = BooksService.addBook(b);

					String[] taglist = Security.sanitize(request.getParameter("tags")).split(",");

					for (int i = 0; i < taglist.length; i++) {
						Tags t = new Tags();
						t.setBookid(bookid);
						t.setTag(taglist[i]);
						TagsService tagsservice = new TagsService();
						tagsservice.addTag(t);
					}

					request.setAttribute(Books.COLUMN_IDBOOK, bookid);
					booklogger.info("[" + bookid + "] " + b.getTitle() + " added by " + user_info);
					response.sendRedirect("search_book");
				} catch (SQLException e) {
					booklogger
							.error("DATABASE FAILURE: " + user_info + " attempted to add book [" + b.getTitle() + "]");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			} else {

				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
			}
			break;
		case "/edit_book":
			// pass = request.getParameter("reauth_pass");
			// try {
			// Security.validatePassword(pass, user.getPassword());
			// } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
			// // TODO Auto-generated catch block
			// e2.printStackTrace();
			// }

			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {

				int id = Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK)));
				Books b = BooksService.getBookById(id);
				request.setAttribute("show_book", b);

				ArrayList<Tags> t = (new TagsService()).getTagsPerBook(b.getIdBooks());
				request.setAttribute("show_book_tags", t);

				request.getRequestDispatcher("AdminEditBook.jsp").forward(request, response);
			} else {

				logger.warn(user_info + " attempted to edit book ["
						+ Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK)) + "]");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");

			}
			break;
		// here
		case "/update_book":

			// pass = request.getParameter("reauth_pass");
			// try {
			// Security.validatePassword(pass, user.getPassword());
			// } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
			// // TODO Auto-generated catch block
			// e2.printStackTrace();
			// }
			//
			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {

				Books b = BooksService
						.getBookById(Integer.parseInt(Security.sanitize(request.getParameter("idBooks"))));
				b.setTitle(Security.sanitize(request.getParameter("updated_title")));
				b.setAuthor(Security.sanitize(request.getParameter("updated_author")));
				b.setPublisher(Security.sanitize(request.getParameter("updated_publisher")));
				b.setYear(Integer.parseInt(Security.sanitize(request.getParameter("updated_year"))));
				b.setLocation(Double.parseDouble(Security.sanitize(request.getParameter("updated_location"))));
				b.setStatus(Books.AVAILABLE);

				String typeString = Security.sanitize(request.getParameter("updated_type"));
				int type1 = 0;
				if ("Book".equals(typeString)) {
					type1 = 0;
				} else if ("Magazine".equals(typeString)) {
					type1 = 1;
				} else if ("Thesis".equals(typeString)) {
					type1 = 2;
				}
				b.setType(type1);

				String statusString = Security.sanitize(request.getParameter("updated_status"));
				int status1 = 0;
				if ("Available".equals(statusString)) {
					status1 = 0;
				} else if ("Reserved".equals(statusString)) {
					status1 = 1;
				} else if ("Out".equals(statusString)) {
					status1 = 2;
				}
				b.setStatus(status1);

				String[] taglist = Security.sanitize(request.getParameter("updated_tags")).split(",");

				for (int i = 0; i < taglist.length; i++) {
					Tags t = new Tags();
					t.setTag(taglist[i]);
					TagsService tagsservice = new TagsService();
					tagsservice.addTag(t);
				}

				try {
					BooksService.updateBook(b);
					booklogger.info(user_info + " edited book [" + b.getIdBooks() + "] " + b.getTitle());
					response.sendRedirect("home");
				} catch (SQLException e) {

					booklogger
							.error("DATABASE FAILURE: " + user_info + " attempted to add book [" + b.getTitle() + "]");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}

			} else {
				logger.warn(user_info + " attempted to edit book ["
						+ Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK)) + "]");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
			}
			break;
		case "/delete_book":

			pass = request.getParameter("reauth_pass");

			try {
				if (!Security.validatePassword(pass, user.getPassword())) {
					logger.warn(user_info + " failed reauth for deleting book.");
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"Re entered password is invalid or incorrect.");
					return;
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			;

			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {

				int idBook = Integer.parseInt(Security.sanitize(request.getParameter(Books.COLUMN_IDBOOK)));
				BooksService.deleteBook(idBook);
				request.getRequestDispatcher("home").forward(request, response);

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");

			}
			break;

		case "/add_admins_page":
			if (user != null && (user.getAccessLevel() == User.ADMINISTRATOR)) {
				request.getRequestDispatcher("AdminAccountCreation.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");
			}
			break;
		case "/secret_question":
			int id = Integer.parseInt(Security.sanitize(request.getParameter("userid")));
			String username = Security.sanitize(request.getParameter("username"));
			User u = new User();
			u = UserService.getUserByIDandUsername(id, username);
			if (u != null) {
				request.setAttribute("forgottenuser", u);
				request.getRequestDispatcher("SecretQuestion.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("SecretQuestionFail.jsp").forward(request, response);
			}
			break;
		case "/answer_question":
			String ans = Security.sanitize(request.getParameter("secretanswer"));
			int idu = Integer.parseInt(request.getParameter("idUser"));
			String name = request.getParameter("userName");
			try {
				UserService.validateQuestionAndAnswer(idu, name, ans);
				request.getRequestDispatcher("SecretQuestionSuccess.jsp").forward(request, response);

			} catch (PasswordMismatch e1) {
				System.out.println("PASSWORD MISMATCH");
				request.getRequestDispatcher("SecretQuestionFail.jsp").forward(request, response);

				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AddressException e) {
				System.out.println("ADDRESS UNKNOWN");
				request.getRequestDispatcher("SecretQuestionFailedSend.jsp").forward(request, response);
				e.printStackTrace();
			} catch (MessagingException e) {
				System.out.println("SEND FAILED");
				request.getRequestDispatcher("SecretQuestionFailedSend.jsp").forward(request, response);
				e.printStackTrace();
			}
			break;
		case "/forget_password_page":
			request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
			break;

		case "/unlock_users_page":
			if (user != null && (user.getAccessLevel() == User.ADMINISTRATOR)) {

				request.setAttribute("lockedusers", UserService.getAllUsersByStatus(1));
				request.getRequestDispatcher("UnlockUsers.jsp").forward(request, response);

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");
			}
			break;
		case "/unlock_users":

			// pass = request.getParameter("reauth_pass");
			// try {
			// Security.validatePassword(pass, user.getPassword());
			// } catch (NoSuchAlgorithmException | InvalidKeySpecException e2) {
			// // TODO Auto-generated catch block
			// e2.printStackTrace();
			// }

			if (user != null && (user.getAccessLevel() == User.ADMINISTRATOR)) {
				try {
					User lockedu = UserService
							.getUserByID(Integer.parseInt(Security.sanitize(request.getParameter("idUser"))));
					UserService.unlockUser(lockedu.getIdUser());

					request.getRequestDispatcher("UnlockedUsersSuccess.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Unlocking Account Failed");
					;
				}

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");
			}

			break;
		case "/add_admins":
			pass = request.getParameter("reauth_pass");

			try {
				if (!Security.validatePassword(pass, user.getPassword())) {
					logger.warn(user_info + " failed reauth for adding admin.");
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"Re entered password is invalid or incorrect.");
					return;
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			;
			if (user != null && (user.getAccessLevel() == User.ADMINISTRATOR)) {

				try {

					User u2 = new User();

					u2.setIdUser(Integer.parseInt(Security.sanitize(request.getParameter("userid"))));
					u2.setEmail(request.getParameter("email"));
					u2.setFirstName(Security.sanitize(request.getParameter("firstname")));
					u2.setMiddleName(Security.sanitize(request.getParameter("middlename")));
					u2.setLastName(Security.sanitize(request.getParameter("lastname")));
					int access = User.STUDENT;
					String accessString = Security.sanitize(request.getParameter("access_level"));
					switch (accessString) {
					case "Library Manager":
						access = User.MANAGER;
						break;
					case "Library Staff":
						access = User.STAFF;
						break;

					case "Library Student Assistant":
						access = User.ASSISTANT;
						break;
					default:
						access = User.STUDENT;
					}

					u2.setAccessLevel(access);
					String birthdate = Security.sanitize(request.getParameter("birthdate"));
					String[] dates = birthdate.split("/");
					int month = Integer.parseInt(dates[0]);
					int day = Integer.parseInt(dates[1]);
					int year = Integer.parseInt(dates[2]);
					u2.setBirthdate(new GregorianCalendar(year, month, day));
					u2.setCreateTime(new GregorianCalendar());
					u2.setLastLogin(new GregorianCalendar());
					u2.setSecretQuestion(Security.sanitize(request.getParameter("secret_question")));
					String unAnswer = request.getParameter("secret_answer");
					u2.setSecretAnswer(Security.createHash(unAnswer));
					u2.setUserName(Security.sanitize(request.getParameter("username")));

					u2.setPassword(Security.createHash("" + new SecureRandom().nextInt(10000)));

					if (UserService.validateUser(u2)) {
						UserService.addUser(u2);
						UserService.unlockUser(u2.getIdUser());
						response.sendRedirect("home");
					} else {
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Credentials lacking.");
					}

				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();

				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("DATABASE FAILURE : Failed adding user to database.");
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Sign Up Failed");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					logger.error("SEND EMAIL FAILURE : Failed sending validation email.");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, "Email Sending Failed.");
				} catch (Exception e) {
					logger.warn("Sign up failed.");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Sign Up Failed");
					;
				}

			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");
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
			request.setAttribute("room",
					RoomsServices.getRoomById(Integer.parseInt(Security.sanitize(request.getParameter("idRooms")))));
			request.setAttribute("roomslots", RoomSlotService
					.getRoomSlotByRoom(Integer.parseInt(Security.sanitize(request.getParameter("idRooms")))));
			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF))
				request.setAttribute("override", true);

			request.getRequestDispatcher("RoomDetails.jsp").forward(request, response);
			break;
		case "/room_reserve":

			if (user != null) {
				RoomSlot roomreserve = RoomSlotService
						.getRoomSlotById(Integer.parseInt(Security.sanitize(request.getParameter("idRoomSlot"))));
				RoomReservation roomreservation = new RoomReservation();
				roomreservation.setIdRoom(roomreserve.getIdRoom());
				roomreservation.setIdUser(user.getIdUser());
				roomreservation.setStartTime(roomreserve.getStart_time());
				roomreservation.setEndTime(roomreserve.getEnd_time());
				request.getRequestDispatcher("ReservationSuccess.jsp").forward(request, response);
				int rrid = RoomReservationService.addRoomReservation(roomreservation);
				roomreservation.setIdRoomReservation(rrid);
				RoomSlotService.updateStatus(roomreserve.getIdRoomSlot(), RoomSlot.RESERVED);
			} else {
				response.sendRedirect("home");
			}

			break;

		case "/commentreview":
			if (user != null) {
				request.setAttribute(Books.COLUMN_IDBOOK, request.getParameter(Books.COLUMN_IDBOOK));
				request.getRequestDispatcher("AddReviews.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unathorized page access.");
			}
			break;

		case "/delete_reserve":

			if (user != null && (user.getAccessLevel() == User.MANAGER || user.getAccessLevel() == User.STAFF)) {
				RoomSlot roomreserve = RoomSlotService
						.getRoomSlotById(Integer.parseInt(Security.sanitize(request.getParameter("idRoomSlot"))));
				RoomSlotService.updateStatus(roomreserve.getIdRoomSlot(), RoomSlot.AVAILABLE);
				request.getRequestDispatcher("ReservationDeletion.jsp").forward(request, response);
			} else {
				logger.warn(user_info + " attempted to delete reservation.");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unathorized page access.");
			}

			break;

		case "/new_user":
			// TODO Auto-generated method stub

			try {

				User u1 = new User();

				u1.setIdUser(Integer.parseInt(Security.sanitize(request.getParameter("userid"))));
				u1.setEmail(request.getParameter("email"));
				u1.setFirstName(Security.sanitize(request.getParameter("firstname")));
				u1.setMiddleName(Security.sanitize(request.getParameter("middlename")));
				u1.setLastName(Security.sanitize(request.getParameter("lastname")));
				int access = 0;
				String accessString = Security.sanitize(request.getParameter("access_level"));
				if ("Faculty".equals(accessString)) {
					access = 1;
				}
				u1.setAccessLevel(access);
				String birthdate = Security.sanitize(Security.sanitize(request.getParameter("birthdate")));
				String[] dates = birthdate.split("/");
				int month = Integer.parseInt(dates[0]);
				int day = Integer.parseInt(dates[1]);
				int year = Integer.parseInt(dates[2]);
				u1.setBirthdate(new GregorianCalendar(year, month, day));
				u1.setCreateTime(new GregorianCalendar());
				u1.setLastLogin(new GregorianCalendar());
				u1.setSecretQuestion(Security.sanitize(request.getParameter("secret_question")));
				String unAnswer = request.getParameter("secret_answer");
				u1.setSecretAnswer(Security.createHash(unAnswer));
				u1.setUserName(Security.sanitize(request.getParameter("username")));

				String unhashed = request.getParameter("password");

				u1.setPassword(Security.createHash(unhashed));
				if (UserService.validateUser(u1)) {
					UserService.addUser(u1);
					response.sendRedirect("home");
				} else {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Credentials lacking.");
				}

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();

			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Sign Up Failed.");
				;
			}

			break;
		case "/change_pass":
			try {

				if (user != null) {
					String oldpass = request.getParameter("oldpassword");
					String newpass = request.getParameter("newpassword");
					int iduser = user.getIdUser();

					UserService.changePassword(oldpass, newpass, iduser);
					request.getRequestDispatcher("PasswordSuccess.jsp").forward(request, response);
					System.out.println("PASSWORD CHANGED");
				} else {
					System.out.println("PASSWORD CHANGED");
					response.sendRedirect("home");
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Changing Password Failed.");
				;
			}

			break;
		case "/validate_email":
			try {

				if (user != null) {
					String code = request.getParameter("emailcode");
					String passw = request.getParameter("password");
					int iduser = user.getIdUser();
					if (Security.validatePassword(code, user.getEmailCode())) {
						UserService.updateEmailVal(iduser);
						response.sendRedirect("home");
					} else {
						UserService.updateEmailCode(iduser);
						request.getRequestDispatcher("NewEmailCode.jsp").forward(request, response);
					}

				} else {
					response.sendRedirect("home");
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Credentials do not match.");

			}

			break;
		case "/addreview":
			if (user != null) {
				Reviews r = new Reviews();
				r.setReview(Security.sanitize(request.getParameter("review")));
				r.setIdBook(Integer.parseInt(Security.sanitize(request.getParameter("idBooks"))));
				r.setIdUser(user.getIdUser());
				r.setRating(4);
				r.setCreateTime(new GregorianCalendar());

				int reviewid;
				try {
					reviewid = ReviewsService.addReview(r);
					request.setAttribute(Reviews.COLUMN_REVIEWID, reviewid);
					reviewlogger.info("[" + reviewid + "] " + r.getReview() + " review added by " + user_info);
					request.getRequestDispatcher("book_detail").forward(request, response);

				} catch (SQLException e) {
					reviewlogger.error(
							"DATABASE FAILURE: " + user_info + " attempted to add review[" + r.getReview() + "]");
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			} else {

				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Page unavailable.");
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
