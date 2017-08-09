package services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

public class ServerService {

	public static int CheckLoggedIn(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] c = request.getCookies();
		Cookie user=null;
		
		if(c!=null)
		for(int i=0; i <c.length; i++){
			if(c[i].getName().equals("user"+User.COLUMN_IDNUM)){
				System.out.println("UpdateSession");
				c[i].setMaxAge(0);
				response.addCookie(c[i]);
				user = c[i];
			}
		}
		if(user != null){
			System.out.println("LOGGED");
			return Integer.parseInt(user.getValue());
		}
		else{
			System.out.println("NO LOGIN");
			return -1;
		}
	}
	
}
