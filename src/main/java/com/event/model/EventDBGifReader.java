package com.event.model;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/event/EventDBGifReader")
public class EventDBGifReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer eventId = Integer.valueOf(req.getParameter("eventId"));
			EventService eventSvc = new EventService();
			out.write(eventSvc.getOneEvent(eventId).getEventPic());
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/tia102g1/images/NoData/null2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();

		}
	}
}
