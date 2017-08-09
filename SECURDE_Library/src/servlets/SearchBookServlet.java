package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Books;
import security.Security;
import services.BooksService;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/search_book")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Books> booklist = new ArrayList<Books>();
		
//		Books testbook = new Books();
//		testbook.setAuthor("AUTH");
//		testbook.setTitle("TITLE 1");
//		testbook.setIdBooks(1);
//		testbook.setLocation(1237623);
//		testbook.setPublisher("PUBL");
//		testbook.setYear(1991);
//		testbook.setStatus(0);
//		testbook.setType(1);
//		booklist.add(testbook);
		
		if(Security.sanitize(request.getParameter("keyword"))==null || "".equals(Security.sanitize(request.getParameter("keyword")))){
			booklist = BooksService.getAllBooks();
		}else{
			System.out.println(Security.sanitize(request.getParameter("keyword")));
			booklist = BooksService.getBooksBySearch(request.getParameter("keyword"));
		}
		
		request.setAttribute("booklist", booklist);
		request.getRequestDispatcher("BorrowBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
