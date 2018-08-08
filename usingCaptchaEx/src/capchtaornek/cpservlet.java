package capchtaornek;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.StickyCaptchaServlet;

@WebServlet("/cpc.jpg")
public class cpservlet extends StickyCaptchaServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		GradiatedBackgroundProducer bg = new GradiatedBackgroundProducer();
		bg.setFromColor(Color.gray);
		bg.setToColor(Color.white);
		Captcha captcha = new Captcha.Builder(200, 40).addText().addNoise().addBackground(bg).build();
		session.setAttribute(Captcha.NAME, captcha);
		CaptchaServletUtil.writeImage(resp,captcha.getImage());
		
	}
}
