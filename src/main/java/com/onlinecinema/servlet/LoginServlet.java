//
//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.*;
//import java.util.List;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.UserDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
//
//public class LoginServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        PrintWriter out = res.getWriter();
//        res.setContentType("text/html");
//
//        UserDao userDao = new UserDao(ConnectionProvider.getConnection());
//
//        try {
//            String username = req.getParameter("username");
//            String password = req.getParameter("password");
//
//            
//            List<User> users = userDao.getAllUsers();
//            
//            String roll=((User) users).getRoll();
//
//           // User bean=new User();
//           // 
//         //   bean.getRoll();
//            
//          //  roll.equals(user.getRoll()
//            boolean isLoggedIn = false;
//
//            if (username != null && password != null) {
//                for (User user : users) {
//                    if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
//                       // isLoggedIn = true;
//                        //break;
//                        if(roll.equals("admin")) {
//                        	res.sendRedirect("Admin.jsp");
//                        }else {
//                        	res.sendRedirect("User.jsp");
//                        }
//                    }else {
//                        // Redirect to the login page in case of failure
//                        res.sendRedirect("Login.jsp");
//                    }
//                }
//            }
//
////            if (isLoggedIn) {
////                // Redirect to the successful login page
////                res.sendRedirect("Screens.html");
////            } else {
////                // Redirect to the login page in case of failure
////                res.sendRedirect("Login.jsp");
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setContentType("text/html");
//            out.println("Error in Exception. Please check the Exception.");
//        }
//    }
//}


package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.UserDao;
import com.onlinecinema.helper.ConnectionProvider;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDao userDao = new UserDao(ConnectionProvider.getConnection());

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            
            List<User> users = userDao.getAllUsers();
            
            boolean isLoggedIn = false;
            String userRole = null;
            User user1 = null;
            for (User user : users) {
                if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    isLoggedIn = true;
                    user1=user;
                    userRole = user.getRoll();
                    break;
                }
            }

            if (isLoggedIn) {
                if ("admin".equals(userRole)) {
                    // Redirect to the admin page
                	
//                	Cookie cook=new Cookie(username,password);
//            		res.addCookie(cook);
            		
            	//	int b = Integer.parseInt(quantity);

                    HttpSession session = req.getSession(true);
                    session.setAttribute("LoggedInUser",user1);
            		
                    res.sendRedirect("Admin.jsp");
                } else {
                    // Redirect to the user page
                	 HttpSession session = req.getSession(true);
                     session.setAttribute("LoggedInUser",user1);
                    res.sendRedirect("User.jsp");
                }
            } else {
                // Redirect to the login page in case of failure
                res.sendRedirect("Login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setContentType("text/html");
            res.getWriter().println("Error in Exception. Please check the Exception.");
        }
    }
}

