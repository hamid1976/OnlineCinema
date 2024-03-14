package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.helper.ConnectionProvider;


public class RemoveMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	    
	        MoviesDao dao = new MoviesDao(ConnectionProvider.getConnection());
	        
	        String deleteMovieIdStr = request.getParameter("movieId");
	    

	        if (deleteMovieIdStr != null) {
	            try {
	                int deleteMovieId = Integer.parseInt(deleteMovieIdStr);
	                
	                dao.deleteMovies(deleteMovieId);

	                out.println("Product deleted successfully.");

	            } catch (NumberFormatException e) {
	                out.println("Invalid Product ID.");
	            } catch (Exception e) {
	                // Handle any other exceptions here
	                e.printStackTrace();
	            }

	            try {
	                // Redirect after successful deletion
	                response.sendRedirect("ShowMovies");
	            } catch (IOException e) {
	                out.println("Error while redirecting.");
	            }
	        }
	    }
	}

