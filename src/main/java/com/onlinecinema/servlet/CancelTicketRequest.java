package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.helper.ConnectionProvider;

/**
 * Servlet implementation class CancelTicketRequest
 */
//@WebServlet("/CancelTicketRequest")
public class CancelTicketRequest extends HttpServlet {
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

        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
        out.println("<input type='submit' value='Logout'>");
        out.println("</form");

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                User loggedInUser = (User) session.getAttribute("LoggedInUser");

                if (loggedInUser != null) {
                    int userId = loggedInUser.getUserId();
                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());

                    // Get ticketId from the request
                    String ticketIdStr = request.getParameter("ticketId");

                    if (ticketIdStr != null) {
                        int ticketId = Integer.parseInt(ticketIdStr);

                        // Fetch the purchased ticket details
                        PurchasedTickets purchasedTicket = purchasedTicketDao.getTicketsById(ticketId);

                        if (purchasedTicket != null) {
                            int numOfPt = purchasedTicket.getNumberOfTickets();
                            String cancelTicketsStr = request.getParameter("cancelticket");

                            if (cancelTicketsStr != null) {
                                int cancelTickets = Integer.parseInt(cancelTicketsStr);
                                int n = numOfPt - cancelTickets;

                                if (cancelTickets > 0 && cancelTickets <= numOfPt) {
                                    // Create a new purchased ticket with updated ticket count
                                    PurchasedTickets updatedPurchasedTicket = new PurchasedTickets(userId, purchasedTicket.getMovieId(), purchasedTicket.getPurchaseDate(), "PURCHASED", n);
                                    purchasedTicketDao.updateTickets(updatedPurchasedTicket, ticketId);
                                    out.println("Tickets Successfully Canceled");

                                    // Update the available tickets count of the movie
                                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
                                    Movies movie = movieDao.getMoviesById(purchasedTicket.getMovieId());
                                    int avt = movie.getAvailableTickets() + cancelTickets;
                                    Movies updatedMovie = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), avt);
                                    movieDao.updateMovies(updatedMovie, purchasedTicket.getMovieId());
                                    out.println("Movie tickets count updated successfully.");
                                } else {
                                    out.println("Invalid number of tickets to cancel.");
                                }
                            }
                        }
                    }else {
                        out.println("<p>Invalid request. Please try again.</p>");
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
        response.sendRedirect("ShowMovies");
    }
}







//
//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
///**
// * Servlet implementation class CancelTicketRequest
// */
////@WebServlet("/CancelTicketRequest")
//public class CancelTicketRequest extends HttpServlet {
////    private static final long serialVersionUID = 1L;
//
//
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    PrintWriter out = response.getWriter();
//    out.println("<style>");
//    out.println(".logout-button {");
//    out.println("  position: absolute;");
//    out.println("  top: 10px;");
//    out.println("  right: 10px;");
//    out.println("}");
//    out.println("</style>");
//
//    out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//    out.println("<input type='submit' value='Logout'>");
//    out.println("</form>");
//
//    try {
//        HttpSession session = request.getSession(false);
//
//        if (session != null) {
//            User loggedInUser = (User) session.getAttribute("LoggedInUser");
//
//            if (loggedInUser != null) {
//                int userId = loggedInUser.getUserId();
//                PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                String selectedMovieIdStr = request.getParameter("movieId");
//
//                if (selectedMovieIdStr != null) {
//                    int selectedMovieId = Integer.parseInt(selectedMovieIdStr);
//                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//                    Movies selectedMovie = movieDao.getMoviesById(selectedMovieId);
//
//                    out.println("<h1>Purchase Tickets for " + selectedMovie.getTitle() + "</h1>");
//
//                    List<PurchasedTickets> ticketsForMovie = purchasedTicketDao.getTicketsByMovieId(selectedMovieId);
//
//                    if (!ticketsForMovie.isEmpty()) {
//                        out.println("<table border='1'>");
//                        out.println("<tr><th>Number Of Tickets Purchased</th><th>Number Of Tickets Cancel</th><th>Cancel</th></tr>");
//
//                        for (PurchasedTickets ticket : ticketsForMovie) {
//                            out.println("<tr>");
//                            out.println("<td>" + ticket.getNumberOfTickets() + "</td>");
//                            out.println("<td><input type='text' name='cancelticket'></td>");
//                            out.println("<td>");
//                            out.println("<form action='CancelTicketRequest' method='get'>");
//                            out.println("<input type='hidden' name='ticketId' value='" + ticket.getTicketId() + "'>");
//                            out.println("<input type='submit' name='cancel' value='Cancel'>");
//                            out.println("</form>");
//                            out.println("</td>");
//                            out.println("</tr>");
//                        }
//
//                        out.println("</table>");
//                    } else {
//                        out.println("<p>No purchased tickets found for this movie.</p>");
//                    }
//                } else {
//                    out.println("<p>Invalid movie ID.</p>");
//                }
//            } else {
//                out.println("<p>You are not logged in. Please log in first.</p>");
//            }
//        } else {
//            out.println("<p>Session expired. Please log in again.</p>");
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    }
//}
//
