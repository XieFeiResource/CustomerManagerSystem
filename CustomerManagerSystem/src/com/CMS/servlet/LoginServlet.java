package com.CMS.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CMS.dao.UserDAO;
import com.CMS.dao.impl.UserDAOJdbcimpl;
import com.CMS.domain.User;

/**
 * Servlet implementation class CheckCodeServlet
 */
public class LoginServlet extends HttpServlet {
	UserDAO loginuser=new UserDAOJdbcimpl();
	int count=0;
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取 ServletPath: /edit.do 或 /addCustomer.do
				String servletPath = request.getServletPath();

				//2. 去除 / 和 .do, 得到类似于 edit 或 addCustomer 这样的字符串
				String methodName = servletPath.substring(1);
				methodName = methodName.substring(0, methodName.length() - 3);
				
				try {
					//3. 利用反射获取 methodName 对应的方法
					Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, 
							HttpServletResponse.class);
					//4. 利用反射调用对应的方法
					method.invoke(this, request, response);
				} catch (Exception e) {
					e.printStackTrace();
					//可以有一些响应.
					response.sendRedirect("error.jsp");
				}
		
	}
	
	private void Login(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		//1. 获取请求参数: CHECK_CODE_PARAM_NAME
		String paramCode = request.getParameter("CHECK_CODE_PARAM_NAME");
		
		//2. 获取 session 中的 CHECK_CODE_KEY 属性值
		String sessionCode = (String)request.getSession().getAttribute("CHECK_CODE_KEY");
		
		System.out.println(paramCode);
		System.out.println(sessionCode); 
		System.out.println(name); 
		System.out.println(password); 
		
		//3. 比对. 看是否一致, 若一致说明验证码正确, 若不一致, 说明验证码错误
		if(!(paramCode != null && paramCode.equals(sessionCode))){
			request.getSession().setAttribute("message", "验证码不一致!");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		User user=new User();
		user.setName(name);
		user.setPassword(password);
		boolean flag=loginuser.Checkuser(user);
		if(flag) {
			response.sendRedirect("index.jsp");
		}else {
			count++;//登录三次不成功，系统将退出。
			System.out.println(count);
			if(count<3) {
				request.getSession().setAttribute("count", count);
				response.sendRedirect(request.getContextPath() +"/login.jsp");
			}else {
				System.exit(0);
			}
		}
	}
	
	private void Register(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		User user=new User();
		user.setName(name);
		user.setPassword(password);
		boolean result=loginuser.Registeruser(user);
		if(result) {
		request.setAttribute("mess", "注册成功，去登录！");
		System.out.println(result);
		request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			request.setAttribute("mess", "该用户已存在！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	
}
