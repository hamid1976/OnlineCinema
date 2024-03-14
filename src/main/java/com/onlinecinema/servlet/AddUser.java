package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.util.List;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.UserDao;
import com.onlinecinema.helper.ConnectionProvider;

public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	PrintWriter out=response.getWriter();
	UserDao userDao = new UserDao(ConnectionProvider.getConnection());
	
	  String userName = request.getParameter("username");
      String password = request.getParameter("password");
      String fullName = request.getParameter("fullname");
      String email = request.getParameter("email");
      String roll = request.getParameter("roll");
		try {
			
			
		      
		      
		      System.out.println(userName);
	        if (userName != null && password != null && fullName != null && email != null && roll != null) {
	            try {
	               

	     	      User ob=new User(userName,password,fullName,email,roll);
	     	      
	     	    userDao.addUser(ob);
	               out.println("Data added successfully.");
	           } catch (NumberFormatException e) {
	                out.println("Invalid input data.");
	           } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	        //out.println("<a href=http://localhost:8080/ECommerce/AddServlet>AddProduct</a>");
		    
	        response.sendRedirect("Login.jsp");
			
			
		}catch(Exception e){
			
		}
		
	}

}
