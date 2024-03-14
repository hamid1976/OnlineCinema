//package com.onlinecinema.servlet;
//import java.io.*;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
//
//public class Purchase extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//        
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//        out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
//        // Add a Logout button
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form>");
//        
//        out.println("<a href=PurchaseTickets>Show Movies</a>");
//        HttpSession session = request.getSession(false);
//
//        if (session != null) {
//            User loggedInUser = (User) session.getAttribute("LoggedInUser");
//
//            if (loggedInUser != null) {
//                int userId = loggedInUser.getUserId();
//
//                try {
//                    String movieIdStr = request.getParameter("movieId");
//                    int movieId = Integer.parseInt(movieIdStr);
//                    Movies movie = movieDao.getMoviesById(movieId);
//
//                    // Create a PurchasedTickets object
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    Date purchaseDate = new Date(); // Use the current date as the purchase date
//
//                    // Display movie details
//                    out.println("<h1>Purchase Tickets for " + movie.getTitle() + "</h1>");
//                    out.println("<p>Description: " + movie.getDescription() + "</p>");
//                    out.println("<p>Ticket Price: $" + movie.getTicketPrice() + "</p>");
//                    out.println("<p>Available Tickets: " + movie.getAvailableTickets() + "</p>");
//                    
//                    out.println("<form action='Purchase' method='get'>");
//                    out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
//                    out.println("<input type='hidden' name='userId' value='" + userId + "'>");
//                    out.println("<input type='hidden' name='purchaseDate' value='" + dateFormat.format(purchaseDate) + "'>");
//                    out.println("Number of Tickets: <input type='text' name='number_of_tickets' required><br>");
//                    out.println("<input type='submit' name='action' value='purchase_tickets'>");
//                    out.println("</form>");
//
//                    String action = request.getParameter("action");
//
//                    if ("purchase_tickets".equals(action)) {
//                        String numberOfTicketsStr = request.getParameter("number_of_tickets");
//                        if (numberOfTicketsStr != null && !numberOfTicketsStr.isEmpty()) {
//                            int numberOfTickets = Integer.parseInt(numberOfTicketsStr);
//                            int at=movie.getAvailableTickets();
//                            int aTnT=at-numberOfTickets;
//                            Movies movie1 = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(),aTnT);
//                            movieDao.updateMovies(movie1, movieId);
//                            // Add the purchased tickets to the database
//                            PurchasedTickets tic = new PurchasedTickets(userId, movieId, purchaseDate, "Purchased", numberOfTickets);
//                            purchasedTicketDao.addTickets(tic);
//
//                            out.println("Tickets Successfully Purchased");
//                        } else {
//                            out.println("Invalid input for number of tickets.");
//                        }
//                    }
//                    
//                } catch (NumberFormatException e) {
//                  // out.println("Invalid input. Please provide valid numeric values.");
//                } catch (Exception e) {
//                  //  out.println("An error occurred while processing the request.");
//                    e.printStackTrace();
//                }
//            } else {
//                out.println("<p>You are not logged in. Please log in first.</p>");
//            }
//        } else {
//            out.println("<p>Session expired. Please log in again.</p>");
//        }
//        
//      //  out.println("<a href=ViewPurchasedTickets>View Purchased Tickets</a>");
//    }
//}
package com.onlinecinema.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.helper.ConnectionProvider;


public class Purchase extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());

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

        out.println("<a href=PurchaseTickets>Show Movies</a>");
        HttpSession session = request.getSession(false);

        if (session != null) {
            User loggedInUser = (User) session.getAttribute("LoggedInUser");

            if (loggedInUser != null) {
                int userId = loggedInUser.getUserId();

                try {
                    String movieIdStr = request.getParameter("movieId");
                    int movieId = Integer.parseInt(movieIdStr);
                    Movies movie = movieDao.getMoviesById(movieId);

                    // Create a PurchasedTickets object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date purchaseDate = new Date(); // Use the current date as the purchase date

                    // Display movie details
                    out.println("<h1>Purchase Tickets for " + movie.getTitle() + "</h1>");
                    out.println("<p>Description: " + movie.getDescription() + "</p>");
                    out.println("<p>Ticket Price: $" + movie.getTicketPrice() + "</p>");
                    out.println("<p>Available Tickets: " + movie.getAvailableTickets() + "</p>");

                    out.println("<form action='Purchase' method='get'>");
                    out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
                    out.println("<input type='hidden' name='userId' value='" + userId + "'>");
                    out.println("<input type='hidden' name='purchaseDate' value='" + dateFormat.format(purchaseDate) + "'>");
                    out.println("Number of Tickets: <input type='text' name='number_of_tickets' required><br>");
                    out.println("<input type='submit' name='action' value='purchase_tickets'>");
                    out.println("</form>");

                    String action = request.getParameter("action");

                    if ("purchase_tickets".equals(action)) {
                        String numberOfTicketsStr = request.getParameter("number_of_tickets");
                        if (numberOfTicketsStr != null && !numberOfTicketsStr.isEmpty()) {
                            int numberOfTickets = Integer.parseInt(numberOfTicketsStr);
                            int availableTickets = movie.getAvailableTickets();

                            if (numberOfTickets > availableTickets) {
                                out.println("HouseFull: Not enough tickets available.");
                            } else {
                                int aTnT = availableTickets - numberOfTickets;
                                Movies movie1 = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), aTnT);
                                movieDao.updateMovies(movie1, movieId);
                                // Add the purchased tickets to the database
                                PurchasedTickets tic = new PurchasedTickets(userId, movieId, purchaseDate, "Purchased", numberOfTickets);
                                purchasedTicketDao.addTickets(tic);
                                
                                

                                out.println("Tickets Successfully Purchased");
                                
                               
                            }
                            
                        } else {
                            out.println("Invalid input for number of tickets.");
                        }
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<p>You are not logged in. Please log in first.</p>");
            }
        } else {
            out.println("<p>Session expired. Please log in again.</p>");
        }
    }
}
