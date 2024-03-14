package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.onlinecinema.beans.User;
import java.io.*
;/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	       public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
	             PrintWriter out=res.getWriter();

	             HttpSession session=req.getSession(true);
	             session.invalidate();
	             	   
	             res.sendRedirect("Login.jsp");  
	}

}
