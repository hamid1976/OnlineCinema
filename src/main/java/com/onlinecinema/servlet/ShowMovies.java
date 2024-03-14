//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.Date;
//import java.util.List;
//
//import com.onlinecinema.helper.ConnectionProvider;
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.dao.MoviesDao;
//
//public class ShowMovies extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//   
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out=response.getWriter();
//		response.setContentType("text/html");
//		
//		MoviesDao pDao=new MoviesDao(ConnectionProvider.getConnection());
//        
//		out.println("<h1>Welcome to Movie Theater (Admin)</h1>");
//		out.println(" <h2>Admin Options:</h2>");
//		
//		out.println("<a href=AddMovie>Add Movies</a>");
//		out.println("<a href=PurchaseTickets>Purchase Tickets</a>");
//		
//		try {
//			List<Movies> movies = pDao.getAllMovies();
//			
//			
//			   
//			
//			
//			out.println("<table border=1>");
//		    out.println("<tr>");
//			out.println("<th>Movie ID</th>");
//			out.println("<th>Title</th>");
//			out.println("<th>Description</th>");
//			out.println("<th>Release Date</th>");
//			out.println("<th>Ticket Price</th>");
//			out.println("<th>AvailableTicket</th>");
//			out.println("<th>Update</th>");
//			out.println("<th>Delete</th>");
//		    out.println("</tr>");
//		    
//		    
//		    for (Movies movie : movies) {
//                int movieId = movie.getMovieId();
//                String title = movie.getTitle();
//                String description = movie.getDescription();
//                Date releaseDate = movie.getReleaseDate();
//                Double ticketPrice = movie.getTicketPrice();
//                int availableTicket = movie.getAvailableTickets();
//             
//
//                out.println("<tr>");
//                out.println("<form action='UpdateMovie' method='get'>");
//                out.println("<td><input type='text' name='movieId' value='" + movieId + "' class='input-field' readonly></td>");
//                out.println("<td><input type='text' name='title' value='" + title + "' class='input-field'></td>");
//                out.println("<td><input type='text' name='description' value='" + description + "' class='input-field'></td>");
//                out.println("<td><input type='text' name='releaseDate' value='" + releaseDate + "' class='input-field'></td>");
//                out.println("<td><input type='text' name='ticketPrice' value='" + ticketPrice + "' class='input-field'></td>");
//                out.println("<td><input type='text' name='availableTickets' value='" + availableTicket + "' class='input-field'></td>");
//                out.println("<td>");
//                out.println("<button type='submit' name='action' value='update' class='button update-button'>Update</button>");
//                out.println("</td>");
//                out.println("</form>");
//                
//                out.println("<form action='RemoveMovie' method='get'>");
//                out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
//                out.println("<td><button type='submit' name='action' value='delete' class='button delete-button'>Delete</button></td>");
//                out.println("</form>");
//                out.println("</tr>");
//            }
//
//            out.println("</table>");
//            out.println("</body>");
//            out.println("</html>");
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}














package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

import com.onlinecinema.helper.ConnectionProvider;
import com.onlinecinema.beans.Movies;
import com.onlinecinema.dao.MoviesDao;

//@WebServlet("/ShowMovies")
public class ShowMovies extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        MoviesDao pDao = new MoviesDao(ConnectionProvider.getConnection());

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
        
        out.println("<h1>Welcome to Movie Theater (Admin)</h1>");
        out.println("<h2>Admin Options:</h2>");
        out.println("<a href=AddMovie>Add Movies</a>");
        out.println("<a href=PurchaseTickets>Purchase Tickets</a>");

       

        try {
            List<Movies> movies = pDao.getAllMovies();

            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>Movie ID</th>");
            out.println("<th>Title</th>");
            out.println("<th>Description</th>");
            out.println("<th>Release Date</th>");
            out.println("<th>Ticket Price</th>");
            out.println("<th>AvailableTicket</th>");
            out.println("<th>Update</th>");
            out.println("<th>Delete</th>");
            out.println("</tr>");

            for (Movies movie : movies) {
                int movieId = movie.getMovieId();
                String title = movie.getTitle();
                String description = movie.getDescription();
                Date releaseDate = movie.getReleaseDate();
                Double ticketPrice = movie.getTicketPrice();
                int availableTicket = movie.getAvailableTickets();

                out.println("<tr>");
                out.println("<form action='UpdateMovie' method='get'>");
                out.println("<td><input type='text' name='movieId' value='" + movieId + "' class='input-field' readonly></td>");
                out.println("<td><input type='text' name='title' value='" + title + "' class='input-field'></td>");
                out.println("<td><input type='text' name='description' value='" + description + "' class='input-field'></td>");
                out.println("<td><input type='text' name='releaseDate' value='" + releaseDate + "' class='input-field'></td>");
                out.println("<td><input type='text' name='ticketPrice' value='" + ticketPrice + "' class='input-field'></td>");
                out.println("<td><input type='text' name='availableTickets' value='" + availableTicket + "' class='input-field'></td>");
                out.println("<td>");
                out.println("<button type='submit' name='action' value='update' class='button update-button'>Update</button>");
                out.println("</td>");
                out.println("</form>");

                out.println("<form action='RemoveMovie' method='get'>");
                out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
                out.println("<td><button type='submit' name='action' value='delete' class='button delete-button'>Delete</button></td>");
                out.println("</form>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
        }
    }
}

