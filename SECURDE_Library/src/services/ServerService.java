package services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import security.Security;

public class ServerService {

	public static int CheckLoggedIn(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] c = request.getCookies();
		Cookie user = null;

		if (c != null)
			for (int i = 0; i < c.length; i++) {
				if (c[i].getName().equals(Security.COOKIE_NAME)) {
					System.out.println("UpdateSession");
					c[i].setMaxAge(60 * 60 * 1);
					response.addCookie(c[i]);
					user = c[i];

				}
			}

		User u = null;
		if (user != null) {
			u = UserService.getUserByID(Integer.parseInt(user.getValue().split(":")[1]));
			if(u != null){
				if (!u.getSalt().equals(user.getValue().split(":")[0])) {
					user = null;
				}
			}else{
				user=null;
			}
		}
		if (user != null) {
			System.out.println("LOGGED");
			return u.getIdUser();
		} else {
			System.out.println("NO LOGIN");
			return -1;
		}
	}

}
