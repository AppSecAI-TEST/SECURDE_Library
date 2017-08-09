package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import security.Security;
import services.UserService;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = Security.sanitize(request.getParameter("username"));
		String password = request.getParameter("password");
		
		int id;
		try {
			id = UserService.logInUser(username, password);

			if(id != -1){
				request.setAttribute("loggedin", id);
				User u = UserService.getUserByID(id);
				System.out.println(request.getAttribute("loggedin"));
				Cookie c = new Cookie(Security.COOKIE_NAME, u.getSalt()+":"+id+"");
				c.setMaxAge(60*60*1);
				
				response.addCookie(c);
				response.sendRedirect("home");
				
			}
			else{
				request.setAttribute("loggedin", -10);
				request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			}
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (custom_errors.LockoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "User has exceeded allowable login attempts. Please contact the administrator.");
		} 
		
		
	}

}
