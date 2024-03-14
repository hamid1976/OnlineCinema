//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.helper.ConnectionProvider;
///**
// * Servlet implementation class UpdateMovie
// */
//public class UpdateMovie extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	response.setContentType("text/html");
//	PrintWriter out = response.getWriter();
//
//    MoviesDao dao=new MoviesDao(ConnectionProvider.getConnection());
//     
//    String movieIdStr = request.getParameter("movieId");
//    String title = request.getParameter("title");
//    String description = request.getParameter("description");
//    String release = request.getParameter("releaseDate");
//    String ticketPriceStr = request.getParameter("ticketPrice");
//   
//     try {
//     	
//	            int movieId = Integer.parseInt(movieIdStr);
//	            double ticketPrice = Double.parseDouble(ticketPriceStr);
//	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date releaseDate = dateFormat.parse(release);
//      
//
//	          
//	          
//             try {
//             	Movies ob=new Movies(title,description,releaseDate,ticketPrice);
//                 dao.updateMovies(ob,movieId);
//                 out.println("Product updated successfully.");
//             } catch (NumberFormatException e) {
//                 out.println("Invalid input. Please provide valid numeric values.");
//             } catch (Exception e) {
//             out.println("An error occurred while updating the product.");
//                 e.printStackTrace(); 
//             }
//      
//     } catch (NumberFormatException e) {
//         out.println("Invalid Movie ID. Please provide a valid numeric value.");
//     } catch (Exception e) {
//    
//         out.println("An error occurred while processing the request.");
//         e.printStackTrace(); 
//     }
//     try {
//         //response.sendRedirect("http://localhost:8080/WebProj/CRUD");
//         response.sendRedirect("ShowMovies");
//     } catch (IOException e) {
//         out.println("Error while redirecting.");
//     }
//
//}
//}


package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.helper.ConnectionProvider;

public class UpdateMovie extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        MoviesDao dao = new MoviesDao(ConnectionProvider.getConnection());

        try {
            String movieIdStr = request.getParameter("movieId");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String release = request.getParameter("releaseDate");
            String ticketPriceStr = request.getParameter("ticketPrice");
            String availableTicketStr = request.getParameter("availableTickets");

            int movieId = Integer.parseInt(movieIdStr);
            double ticketPrice = Double.parseDouble(ticketPriceStr);
            int availableTicket = Integer.parseInt(availableTicketStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = dateFormat.parse(release);

            Movies movie = new Movies(title, description, releaseDate, ticketPrice,availableTicket);
            dao.updateMovies(movie, movieId);
            out.println("Movie updated successfully.");
        } catch (NumberFormatException e) {
            out.println("Invalid input. Please provide valid numeric values.");
        } catch (ParseException e) {
            out.println("Invalid date format. Please use yyyy-MM-dd format.");
        } catch (Exception e) {
            out.println("An error occurred while updating the movie.");
            e.printStackTrace();
        }

        try {
            response.sendRedirect("ShowMovies");
        } catch (IOException e) {
            out.println("Error while redirecting.");
        }
    }
}
