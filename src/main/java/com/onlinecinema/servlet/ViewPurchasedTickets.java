////package com.onlinecinema.servlet;
////
////import jakarta.servlet.ServletException;
////import jakarta.servlet.annotation.WebServlet;
////import jakarta.servlet.http.HttpServlet;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import jakarta.servlet.http.HttpSession;
////
////import java.io.IOException;
////import java.io.PrintWriter;
////import java.util.List;
////
////import com.onlinecinema.beans.Movies;
////import com.onlinecinema.beans.PurchasedTickets;
////import com.onlinecinema.beans.User;
////import com.onlinecinema.dao.MoviesDao;
////import com.onlinecinema.dao.PurchasedTicketDao;
////import com.onlinecinema.helper.ConnectionProvider;
////
////
////public class ViewPurchasedTickets extends HttpServlet {
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        PrintWriter out = response.getWriter();
////        out.println("<style>");
////        out.println(".logout-button {");
////        out.println("  position: absolute;");
////        out.println("  top: 10px;");
////        out.println("  right: 10px;");
////        out.println("}");
////        out.println("</style>");
////
////        // Add a Logout button
////        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
////        out.println("<input type='submit' value='Logout'>");
////        out.println("</form>");
////        
////        try {
////          //   Get the user's purchased tickets and display them with a cancel button
////        	out.println("<a href=ShowMovies>ShowMovies</a>");
////            HttpSession session = request.getSession(false);
////
////            if (session != null) {
////                User loggedInUser = (User) session.getAttribute("LoggedInUser");
////
////                if (loggedInUser != null) {
////                    int userId = loggedInUser.getUserId();
////
////                  //   Fetch purchased tickets for the user
////                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
////                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
////                    														//	 getPurchasedTicketsByUserId
////                    if (!purchasedTickets.isEmpty()) {
////                        out.println("<h1>Purchased Tickets</h1>");
////                        out.println("<table border =1>");
////                        out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets Buyed</th><th>Number of Tickets Cancel</th><th>Cancel</th></tr");
////                       // out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets Buyed</th></th></tr");
////                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
////
////                        for (PurchasedTickets ticket : purchasedTickets) {
////                            int movieId = ticket.getMovieId();
////                            Movies movie = movieDao.getMoviesById(movieId);
////
////                            out.println("<tr>");
////                            out.println("<td>" + movie.getTitle() + "</td>");
////                            out.println("<td>" + ticket.getPurchaseDate() + "</td>");
////                            out.println("<td>" + ticket.getNumberOfTickets() + "</td>");
////                            out.println("<td><input type=text name=numberofTickets></td>");
////                            String numberofTicketsStr=request.getParameter("numberofTickets");
////                            
////                            out.println("<td>");
////                            out.println("<form action='CancelTickets' method='get'>");
////                            out.println("<input type='hidden' name='movieId' value='" + ticket.getMovieId() + "'>");
////                            out.println("<input type='hidden' name='ticketId' value='" + ticket.getTicketId() + "'>");
////                            out.println("<td><input type='hidden' name=numOfTic value="+numberofTicketsStr+"></td>");
////                            out.println("<input type='submit' name='cancel' value='Cancel'>");
////                            out.println("</form>");
////                            out.println("</td>");
////                            out.println("</tr>");
////                        }
////
////
////                        out.println("</table>");
////                    } else {
////                        out.println("<p>No purchased tickets found.</p>");
////                    }
////                } else {
////                    out.println("<p>You are not logged in. Please log in first.</p>");
////                }
////            } else {
////                out.println("<p>Session expired. Please log in again.</p>");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////}
//
//
//
//
//
//
//
//
//
//
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
////
//////@WebServlet("/ViewPurchasedTickets")
//public class ViewPurchasedTickets extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<a href=ShowMovies>ShowMovies</a>");
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//      out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
//        // Add a Logout button
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form");
//    	
//        try {
//        	
//        	//out.println("<a href=PurchaseTickets>Purchase Tickets</a>");
//
//            HttpSession session = request.getSession(false);
//
//            if (session != null) {
//                User loggedInUser = (User) session.getAttribute("LoggedInUser");
//
//                if (loggedInUser != null) {
//                    int userId = loggedInUser.getUserId();
//
//                    // Fetch purchased tickets for the user
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//
//                    if (!purchasedTickets.isEmpty()) {
//                        out.println("<h1>Purchased Tickets</h1>");
//                        out.println("<table border='1'>");
//                      //  out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets Bought</th><th>Number of Tickets Cancel</th><th>Cancel</th></tr>");
//                        out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets Buyed</th></tr");
//                       MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//                            Movies movie = movieDao.getMoviesById(movieId);
//
//                            out.println("<tr>");
//                            out.println("<td>" + movie.getTitle() + "</td>");
//                            out.println("<td>" + ticket.getPurchaseDate() + "</td>");
//                            out.println("<td>" + ticket.getNumberOfTickets() + "</td>");
//                          //  out.println("<td><input type='text' name='numberofTickets'></td>");
//
//                            out.println("<td>");
//                            out.println("<form action='CancelTickets' method='get'>");
//                           out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
//                            out.println("<input type='hidden' name='ticketId' value='" + ticket.getTicketId() + "'>");
//                          // out.println("<input type='hidden' name='numberofTickets' value=''>");
//                            out.println("<input type='submit' name='cancel' value='Cancel'>");
//                           out.println("</form>");
//                            out.println("</td>");
//                            out.println("</tr>");
//                        }
//
//                        out.println("</table>");
//                   } else {
//                        out.println("<p>No purchased tickets found.</p>");
//                   }
//                } else {
//                    out.println("<p>You are not logged in. Please log in first.</p>");
//                }
//            } else {
//                out.println("<p>Session expired. Please log in again.</p>");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}










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
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/ViewPurchasedTickets")
//public class ViewPurchasedTickets extends HttpServlet {
//  
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<a href=ShowMovies>ShowMovies</a>");
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
//        out.println("</form");
//        
//        try {
//            HttpSession session = request.getSession(false);
//
//            if (session != null) {
//                User loggedInUser = (User) session.getAttribute("LoggedInUser");
//
//                if (loggedInUser != null) {
//                    int userId = loggedInUser.getUserId();
//
//                    // Fetch purchased tickets for the user
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//
//                    if (!purchasedTickets.isEmpty()) {
//                        out.println("<h1>Purchased Tickets</h1>");
//                        out.println("<table border='1'>");
//                        out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Number of Tickets Bought</th></th></tr");
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        // Create a set to store unique movie IDs
//                        Set<Integer> uniqueMovieIds = new HashSet<>();
//
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//
//                            // Check if this movie ID has already been displayed
//                            if (!uniqueMovieIds.contains(movieId)) {
//                                uniqueMovieIds.add(movieId);
//
//                                Movies movie = movieDao.getMoviesById(movieId);
//
//                                out.println("<tr>");
//                                out.println("<td>" + movie.getTitle() + "</td>");
//                                out.println("<td>" + ticket.getPurchaseDate() + "</td>");
//                                out.println("<td>" + ticket.getNumberOfTickets() + "</td>");
//
//                                out.println("<td>");
//                                out.println("<form action='CancelTickets' method='get'>");
//                                out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
//                                out.println("<input type='hidden' name='ticketId' value='" + ticket.getTicketId() + "'>");
//                                out.println("<input type='submit' name='cancel' value='Cancel'>");
//                                out.println("</form>");
//                                out.println("</td>");
//                                out.println("</tr>");
//                            }
//                        }
//
//                        out.println("</table>");
//                    } else {
//                        out.println("<p>No purchased tickets found.</p>");
//                    }
//                } else {
//                    out.println("<p>You are not logged in. Please log in first.</p>");
//                }
//            } else {
//                out.println("<p>Session expired. Please log in again.</p>");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.beans.User;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.dao.PurchasedTicketDao;
import com.onlinecinema.helper.ConnectionProvider;

//@WebServlet("/ViewPurchasedTickets")
public class ViewPurchasedTickets extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<a href=ShowMovies>ShowMovies</a>");
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
        out.println("</form");

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                User loggedInUser = (User) session.getAttribute("LoggedInUser");

                if (loggedInUser != null) {
                    int userId = loggedInUser.getUserId();

                    // Fetch purchased tickets for the user
                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);

                    if (!purchasedTickets.isEmpty()) {
                        out.println("<h1>Purchased Tickets</h1>");
                        out.println("<table border='1'>");
                        out.println("<tr><th>Movie Title</th><th>Purchase Date</th><th>Total Number of Tickets Bought</th><th>Cancel</th></tr>");
                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());

                        // Create a map to store total tickets for each movie
                        Map<Integer, Integer> totalTicketsByMovie = new HashMap<>();

                        for (PurchasedTickets ticket : purchasedTickets) {
                            int movieId = ticket.getMovieId();
                            int numberOfTickets = ticket.getNumberOfTickets();

                            // Update the total tickets for this movie
                            totalTicketsByMovie.put(movieId, totalTicketsByMovie.getOrDefault(movieId, 0) + numberOfTickets);
                        }

                        for (Map.Entry<Integer, Integer> entry : totalTicketsByMovie.entrySet()) {
                            int movieId = entry.getKey();
                            int totalTickets = entry.getValue();
                            Movies movie = movieDao.getMoviesById(movieId);

                            out.println("<tr>");
                            out.println("<td>" + movie.getTitle() + "</td>");
                            out.println("<td>" + purchasedTickets.get(0).getPurchaseDate() + "</td>");
                            out.println("<td>" + totalTickets + "</td>");
                            out.println("<td>");
                            out.println("<form action='CancelTickets' method='get'>");
                            out.println("<input type='hidden' name='movieId' value='" + movieId + "'>");
                            out.println("<input type='submit' name='cancel' value='Cancel'>");
                            out.println("</form>");
                            out.println("</td>");
                            out.println("</tr>");
                        }

                        out.println("</table>");
                    } else {
                        out.println("<p>No purchased tickets found.</p>");
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
