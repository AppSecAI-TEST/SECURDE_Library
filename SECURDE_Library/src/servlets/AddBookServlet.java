package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tags;
import services.TagsService;

import services.BooksService;
import models.Books;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
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
		Books b = new Books();
		
		b.setTitle			(request.getParameter("book_title"));
		b.setAuthor			(request.getParameter("author"));
		b.setPublisher		(request.getParameter("publisher"));
		b.setYear			(Integer.parseInt(request.getParameter("year")));
		b.setLocation		(Double.parseDouble(request.getParameter("location")));
		b.setStatus(0);
		b.setCreateTime(new GregorianCalendar());
		String typeString = request.getParameter("type");
		int type1 = 0;
		
		if("Book".equals(typeString)){
			type1 = 0;
		}
		else if("Magazine".equals(typeString)){
			type1 = 1;
		}
		else if("Thesis".equals(typeString)){
			type1 = 2;
		}
		b.setType(type1);
		
		BooksService bookservice = new BooksService();
		
		int bookid = bookservice.addBook(b);
		String[] taglist = request.getParameter("tags").split(",");
		
		for(int i=0; i<taglist.length; i++){
			Tags t = new Tags();
			t.setBookid(bookid);
			t.setTag(taglist[i]);
			TagsService tagsservice = new TagsService();
			tagsservice.addTag(t);
		}
		// --------- CHECK LOGIN ---------

		// ------------ END CHECK LOGIN --------
		
		
		// EDIT
		response.sendRedirect("ReserveBook.jsp");

	}

}
