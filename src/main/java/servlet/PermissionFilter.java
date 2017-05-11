package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Data;
import user.User;

@WebFilter("/todo.jsp")
public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		Data data = Data.getInstance();
		List<User> users = data.getUsersFromDB();
		
		User currentUser = null;
		if(session != null){
			currentUser = (User) session.getAttribute("user");
			
			for (User user : users) {
				if(user.equals(currentUser)){
					chain.doFilter(request, response);
					return;
				}
			}
			res.sendRedirect("./login.jsp");
		}
	}

	@Override
	public void destroy() {
		
	}

}
