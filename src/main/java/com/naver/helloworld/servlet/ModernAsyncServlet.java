package com.naver.helloworld.servlet;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncDispatchServlet", urlPatterns = { "/asyncDispatch" }, asyncSupported = true)
public class ModernAsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 222L;

	@Override
	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AsyncContext asyncContext = request.startAsync();
		asyncContext.start(() -> {
			// 오래 걸리는 작업
			asyncContext.dispatch("/threadNames.jsp");
		});
	}
}