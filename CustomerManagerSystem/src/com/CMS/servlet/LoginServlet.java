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
		//1. ��ȡ ServletPath: /edit.do �� /addCustomer.do
				String servletPath = request.getServletPath();

				//2. ȥ�� / �� .do, �õ������� edit �� addCustomer �������ַ���
				String methodName = servletPath.substring(1);
				methodName = methodName.substring(0, methodName.length() - 3);
				
				try {
					//3. ���÷����ȡ methodName ��Ӧ�ķ���
					Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, 
							HttpServletResponse.class);
					//4. ���÷�����ö�Ӧ�ķ���
					method.invoke(this, request, response);
				} catch (Exception e) {
					e.printStackTrace();
					//������һЩ��Ӧ.
					response.sendRedirect("error.jsp");
				}
		
	}
	
	private void Login(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		//1. ��ȡ�������: CHECK_CODE_PARAM_NAME
		String paramCode = request.getParameter("CHECK_CODE_PARAM_NAME");
		
		//2. ��ȡ session �е� CHECK_CODE_KEY ����ֵ
		String sessionCode = (String)request.getSession().getAttribute("CHECK_CODE_KEY");
		
		System.out.println(paramCode);
		System.out.println(sessionCode); 
		System.out.println(name); 
		System.out.println(password); 
		
		//3. �ȶ�. ���Ƿ�һ��, ��һ��˵����֤����ȷ, ����һ��, ˵����֤�����
		if(!(paramCode != null && paramCode.equals(sessionCode))){
			request.getSession().setAttribute("message", "��֤�벻һ��!");
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
			count++;//��¼���β��ɹ���ϵͳ���˳���
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
		request.setAttribute("mess", "ע��ɹ���ȥ��¼��");
		System.out.println(result);
		request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			request.setAttribute("mess", "���û��Ѵ��ڣ�");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	
}
