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

/**
 * Servlet implementation class CancelTicketUser
 */
public class CancelTicketUser extends HttpServlet {
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
	                    
	                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
	                    int movieId = 0;
	                    int at = 0;
	                    int nt = 0;

	                    if (!purchasedTickets.isEmpty()) {
	                        // Assuming that there is only one movie associated with the purchased tickets
	                    	   String movieIdStr = request.getParameter("movieId");
	                           movieId = Integer.parseInt(movieIdStr);
	                           
	                         // movieId = purchasedTickets.get(0).getMovieId();
	                     
	                        
	                        Movies movie = movieDao.getMoviesById(movieId);
	                        at = movie.getAvailableTickets();

	                        for (PurchasedTickets pt : purchasedTickets) {
	                            nt = pt.getNumberOfTickets();
	                        }

	                        int aTnT = at + nt;
	                        Movies updatedMovie = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), aTnT);
	                        movieDao.updateMovies(updatedMovie, movieId);
	                    }

	                    String deleteTicketIdStr = request.getParameter("ticketId");

	                    if (deleteTicketIdStr != null) {
	                        try {
	                            int deleteTicketId = Integer.parseInt(deleteTicketIdStr);
	                            purchasedTicketDao.deleteTickets(deleteTicketId);
	                            out.println("Ticket deleted successfully.");
	                        } catch (NumberFormatException e) {
	                            out.println("Invalid Ticket ID.");
	                        } catch (Exception e) {
	                            // Handle any other exceptions here
	                            e.printStackTrace();
	                        }

	                        try {
	                            // Redirect after successful deletion
	                            response.sendRedirect("ViewPurchasedTicketUser");
	                        } catch (IOException e) {
	                            out.println("Error while redirecting.");
	                        }
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
	}
}
