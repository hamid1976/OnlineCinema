//package com.onlinecinema.servlet;
//import java.io.*;
//import java.util.Date;
//import java.util.List;
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
//import jakarta.servlet.ServletException;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
///**
// * Servlet implementation class PurchaseTickets
// */
//public class PurchaseTickets extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out=response.getWriter();
//		response.setContentType("text/html");
//		
//		PurchasedTicketDao dao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//		MoviesDao movie=new MoviesDao(ConnectionProvider.getConnection());
//		
//		try {
//		List<PurchasedTickets>  pt=dao.getAllPurchasedTickets();
//		
//		java.sql.Date pD= ((PurchasedTickets) pt).getPurchaseDate();
//		
//		
//		List<Movies> movies = movie.getMoviesPurchasedTickets();
//		
//		out.println("<table border=1>");
//		out.println("<tr>");
//		out.println("<th>Movie Id</th>");
//		out.println("<th>Movie Name</th>");
//		out.println("<th>Description</th>");
//		out.println("<th>Show Date</th>");
//		out.println("<th>Purchase Date</th>");
//		out.println("<th>Ticket Price</th>");
//		out.println("<th>Number Of Tickets</th>");
//		out.println("<th>Purchase</th>");
//		out.println("<th>Delete</th>");
//		out.println("</tr>");
//		
//			
//		 for (Movies movie1 : movies) {
//             int movieId = movie1.getMovieId();
//             String title = movie1.getTitle();
//             String description = movie1.getDescription();
//             Date releaseDate = movie1.getReleaseDate();
//             Double ticketPrice = movie1.getTicketPrice();
//            
//            
//
//             out.println("<tr>");
//             out.println("<form action='UpdateTickets' method='get'>");
//             out.println("<td>< value='" + movieId + "' class='input-field' readonly></td>");
//             out.println("<td>< value='" + title + "' class='input-field'></td>");
//             out.println("<td>< value='" + description + "' class='input-field'></td>");
//             out.println("<td>< value='" + releaseDate + "' class='input-field'></td>");
//             out.println("<td>< value='" + pD + "' class='input-field'></td>");
//             out.println("<td>< value='" + ticketPrice + "' class='input-field'></td>");
//             out.println("<td><input type='text' name='numberOfTickets'></td>");
//          
//             out.println("<td>");
//             out.println("<button type='submit' name='action' value='update' class='button update-button'>Update</button>");
//             out.println("</td>");
//             out.println("</form>");
//             
//             out.println("<form action='RemoveTickets' method='get'>");
//             out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
//             out.println("<td><button type='submit' name='action' value='delete' class='button delete-button'>Delete</button></td>");
//             out.println("</form>");
//             out.println("</tr>");
//         }
//
//         out.println("</table>");
//         out.println("</body>");
//         out.println("</html>");
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//		
//		
//	}
//
//}


package com.onlinecinema.servlet;

import java.io.*;
import java.util.Date;
import java.util.List;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.dao.UserDao;
import com.onlinecinema.helper.ConnectionProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PurchaseTickets
 */
public class PurchaseTickets extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        PurchasedTicketDao dao = new PurchasedTicketDao(ConnectionProvider.getConnection());
        MoviesDao movie = new MoviesDao(ConnectionProvider.getConnection());
        UserDao user = new UserDao(ConnectionProvider.getConnection());
     //   out.println("<href a=httpViewPurchasedTickets'>View Purchased Tickets</a>");
        
        out.println("<style>");
        out.println(".logout-button {");
        out.println("  position: absolute;");
        out.println("  top: 10px;");
        out.println("  right: 10px;");
        out.println("}");
        out.println("</style>");

        // Add a Logout button
        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
        out.println("<input type='submit' value='Logout'>");
        out.println("</form>");
        
        out.println("<a href=ViewPurchasedTickets>View Purchased Tickets</a>");
        try {
            List<PurchasedTickets> pt = dao.getAllPurchasedTickets();

            //List<Movies> movies = movie.getMoviesPurchasedTickets();
            List<Movies> movies = movie.getAllMovies();
            
           
            
            out.println("<html>");
            out.println("<head><title>Purchase Tickets</title></head>");
            out.println("<body>");
            out.println("<h1>Purchase Ticket</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Movie Id</th>");
            out.println("<th>Movie Name</th>");
            out.println("<th>Description</th>");
            out.println("<th>Show Date</th>");
            out.println("<th>Purchase Date</th>");
            out.println("<th>Ticket Price</th>");
            out.println("<th>Available Tickets</th>");
            out.println("<th>Purchase</th>");
       
            out.println("</tr>");
            int movieId=0;
            for (Movies movie1 : movies) {
                 movieId = movie1.getMovieId();
                String title = movie1.getTitle();
                String description = movie1.getDescription();
                Date releaseDate = movie1.getReleaseDate();
                Double ticketPrice = movie1.getTicketPrice();
                int availableTicket = movie1.getAvailableTickets();
                

                out.println("<tr>");
                out.println("<form action='Purchase' method='get'>");
                out.println("<td>" + movieId + "</td>");
                out.println("<td>" + title + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + releaseDate + "</td>");
                out.println("<td>" + releaseDate + "</td>"); // Changed to releaseDate instead of pD
                out.println("<td>" + ticketPrice + "</td>");
                out.println("<td>" + availableTicket + "</td>");
             //   out.println("<td><input type='text' name='numberOfTickets'></td>");
                out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
                out.println("<td><button type='submit' name='action' value='purchase' class='button purchase-button'>Purchase Ticket</button></td>");
                out.println("</form>");
               
                out.println("</tr>");
            }
            
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            
        

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









//
//
//
//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/PurchaseTickets")
//public class PurchaseTickets extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        response.setContentType("text/html");
//
//        int movie_id = Integer.parseInt(request.getParameter("movie_id"));
//
//        // Fetch movie details from the database
//        Connection connection = ConnectionProvider.getConnection();
//        String selectQuery = "SELECT * FROM Movies WHERE movie_id = ?";
//        
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
//            preparedStatement.setInt(1, movie_id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                String title = resultSet.getString("title");
//                String description = resultSet.getString("description");
//                Double ticketPrice = resultSet.getDouble("ticketPrice");
//                int availableTickets = resultSet.getInt("availableTickets");
//
//                out.println("<h1>Purchase Tickets for " + title + "</h1>");
//                out.println("<p>Description: " + description + "</p>");
//                out.println("<p>Ticket Price: $" + ticketPrice + "</p>");
//                out.println("<p>Available Tickets: " + availableTickets + "</p>");
//
//                // Display the purchase form
//                out.println("<form action='PurchaseTickets' method='post'>");
//                out.println("<input type='hidden' name='movie_id' value='" + movie_id + "'>");
//                out.println("Number of Tickets: <input type='text' name='number_of_tickets' required><br>");
//                out.println("<input type='submit' value='Purchase Tickets'>");
//                out.println("</form>");
//            } else {
//                out.println("Movie not found.");
//            }
//
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            out.println("An error occurred while fetching movie details.");
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // The code for processing the purchase is the same as in the previous example
//        // ...
//    }
//}
//
