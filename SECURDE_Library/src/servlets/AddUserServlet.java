package servlets;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import services.UserService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/new_user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        //super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u = new User();
		
		u.setIdUser(Integer.parseInt(request.getParameter("userid")));
		u.setEmail(request.getParameter("email"));
		u.setFirstName(request.getParameter("firstname"));
		u.setMiddleName(request.getParameter("middlename"));
		u.setLastName(request.getParameter("lastname"));
		int access = 0;
		String accessString = request.getParameter("access_level");
		if("Faculty".equals(accessString)){
			access = 1;
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
		
		response.sendRedirect("Registered.html");;
	}

}
