package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.dao.UserDao;
import com.onlinecinema.helper.ConnectionProvider;

/**
 * Servlet implementation class ShowMoviesUser
 */
public class ShowMoviesUser extends HttpServlet {
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
	        
	        out.println("<a href=ViewPurchasedTicketUser>View Purchased Tickets</a>");
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
	                out.println("<form action='PurchaseUser' method='get'>");
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
