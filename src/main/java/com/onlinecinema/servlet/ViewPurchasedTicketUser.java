package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.helper.ConnectionProvider;

public class ViewPurchasedTicketUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  PrintWriter out = response.getWriter();
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
	        out.println("<a href=ShowMoviesUser>Show All Movies....</a>");
	        try {
	            // Get the user's purchased tickets and display them with a cancel button
	     
	            HttpSession session = request.getSession(false);

	            if (session != null) {
	                User loggedInUser = (User) session.getAttribute("LoggedInUser");

	                if (loggedInUser != null) {
	                    int userId = loggedInUser.getUserId();

	                    // Fetch purchased tickets for the user
	                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
	                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
	                    															 //getPurchasedTicketsByUserId
	                    if (!purchasedTickets.isEmpty()) {
	                        out.println("<h1>Purchased Tickets</h1>");
	                        out.println("<table border =1>");
	                        out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets</th><th>Cancel</th></tr");

	                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());

	                        for (PurchasedTickets ticket : purchasedTickets) {
	                            int movieId = ticket.getMovieId();
	                            Movies movie = movieDao.getMoviesById(movieId);

	                            out.println("<tr>");
	                            out.println("<td>" + movie.getTitle() + "</td>");
	                            out.println("<td>" + ticket.getPurchaseDate() + "</td>");
	                            out.println("<td>" + ticket.getNumberOfTickets() + "</td>");
	                            out.println("<td>");
	                            out.println("<form action='CancelTicketUser' method='get'>");
	                            out.println("<input type='hidden' name='movieId' value='" + ticket.getMovieId() + "'>");
	                            out.println("<input type='hidden' name='ticketId' value='" + ticket.getTicketId() + "'>");
	                            out.println("<input type='submit' name='cancel' value='Cancel'>");
	                            out.println("</form>");
	                            out.println("</td>");
	                            out.println("</tr>");
	                        }


	                        out.println("</table>");
	                    } else {
	                        out.println("<p>No purchased tickets found.</p>");
	                     //   out.println("<a href=ShowMoviesUser>Show All Movies....</a>");
	                    }
	                } else {
	                    out.println("<p>You are not logged in. Please log in first.</p>");
	                }
	            } else {
	                out.println("<p>Session expired. Please log in again.</p>");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	      // 	out.println("<a href=ShowMoviesUser>Show All Movies</a>");
	}


}
