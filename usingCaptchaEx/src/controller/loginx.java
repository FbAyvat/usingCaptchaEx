package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

@WebServlet("/loginservlet")
public class loginx extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String captchaCode = req.getParameter("captchaCode");
		
		HttpSession session = req.getSession();
		Captcha captcha = (Captcha)session.getAttribute(Captcha.NAME); 
		boolean isCorrect=captcha.isCorrect(captchaCode);
		
		
		if("admin".equals(username) && "admin".equals(password) && isCorrect) {
			session.setAttribute("username", username);
			resp.sendRedirect("welcome.jsp");
		}
		
		else {
		RequestDispatcher disp =req.getRequestDispatcher("login.jsp");
		if(!isCorrect) {
			req.setAttribute("errormessage","captcha hatasý");
			
		}
		disp.forward(req, resp);
		}
		
		
	}

}
