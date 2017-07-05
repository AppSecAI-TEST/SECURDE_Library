package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Books;
import models.Tags;
import services.BooksService;
import services.TagsService;

/**
 * Servlet implementation class EditBookServlet
 */
@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Books b = new Books();
		b.setTitle			(request.getParameter("updated_title"));
		b.setAuthor			(request.getParameter("updated_author"));
		b.setPublisher		(request.getParameter("updated_publisher"));
		b.setYear			(Integer.parseInt(request.getParameter("updated_year")));
		b.setLocation		(Double.parseDouble(request.getParameter("updated_location")));
		b.setStatus(0);
		
		String typeString = request.getParameter("updated_type");
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
		
		// --------- CHECK LOGIN ---------
		
		// ------------ END CHECK LOGIN --------
		
	
		BooksService bservice = new BooksService();
		
		
		String[] taglist = request.getParameter("updated_tags").split(",");
		
		for(int i=0; i<taglist.length; i++){
			Tags t = new Tags();
			t.setTag(taglist[i]);
			TagsService tagsservice = new TagsService();
			tagsservice.addTag(t);
		}
		
		bservice.updateBook(b);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
