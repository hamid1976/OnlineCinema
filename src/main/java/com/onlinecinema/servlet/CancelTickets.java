//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
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
////@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
// 
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        try {
//            // Get the user's purchased tickets and display them with a cancel button
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
//                    															 //getPurchasedTicketsByUserId
//                  
//                    
//                    
//                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//                    
//                    //int movieId = Integer.parseInt(movieIdStr);
//                    Movies movie = movieDao.getMoviesById(userId);
//                    int movieId =movie.getMovieId();
//                    
//                    
//                    int at=movie.getAvailableTickets();
//                    List<PurchasedTickets> pt = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//                    int nt=((PurchasedTickets) pt).getNumberOfTickets();
//                    int aTnT=at-nt;
//                    Movies movie1 = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(),aTnT);
//                    movieDao.updateMovies(movie1, movieId);
//                    
//                    
//                  // dao.getMoviesById(userId);
//                  //  String at=dao.getAllTickets();
//        	        String deleteticketIdStr = request.getParameter("ticketId");
//        	    
//
//        	        if (deleteticketIdStr != null) {
//        	            try {
//        	                int deleteTicketId = Integer.parseInt(deleteticketIdStr);
//        	                
//        	                purchasedTicketDao.deleteTickets(deleteTicketId);
//
//        	                out.println("Ticket deleted successfully.");
//
//        	            } catch (NumberFormatException e) {
//        	                out.println("Invalid Product ID.");
//        	            } catch (Exception e) {
//        	                // Handle any other exceptions here
//        	                e.printStackTrace();
//        	            }
//
//        	            try {
//        	                // Redirect after successful deletion
//        	                response.sendRedirect("ViewPurchasedTickets");
//        	            } catch (IOException e) {
//        	                out.println("Error while redirecting.");
//        	            }
//        	        }
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
//
//public class CancelTickets extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
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
//        try {
//            // Get the user's purchased tickets and display them with a cancel button
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
//                    String cancelTicketsStr = request.getParameter("cancelTickets");
//                    int cancelTickets = Integer.parseInt(cancelTicketsStr);
//                    
//                    if (!purchasedTickets.isEmpty() && cancelTickets > 0) {
//                        PurchasedTickets ticketToCancel = purchasedTickets.get(0);  // Assuming there's only one ticket associated with the cancel action
//                        int numberOfTicketsToCancel = Math.min(cancelTickets, ticketToCancel.getNumberOfTickets());
//                        
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//                        int movieId = ticketToCancel.getMovieId();
//                        Movies movie = movieDao.getMoviesById(movieId);
//                        int availableTickets = movie.getAvailableTickets();
//                        int newAvailableTickets = availableTickets + numberOfTicketsToCancel;
//
//                        Movies updatedMovie = new Movies(
//                            movie.getTitle(),
//                            movie.getDescription(),
//                            movie.getReleaseDate(),
//                            movie.getTicketPrice(),
//                            newAvailableTickets
//                        );
//                        movieDao.updateMovies(updatedMovie, movieId);
//
//                        if (numberOfTicketsToCancel < ticketToCancel.getNumberOfTickets()) {
//                            // Update the remaining purchased tickets if not all were canceled
//                            int remainingTickets = ticketToCancel.getNumberOfTickets() - numberOfTicketsToCancel;
//                            PurchasedTickets pt=
//                            purchasedTicketDao.updateTickets(ticketToCancel.getTicketId(), remainingTickets);
//                        } else {
//                            // If all tickets were canceled, delete the ticket
//                            purchasedTicketDao.deleteTickets(ticketToCancel.getTicketId());
//                        }
//
//                        out.println(numberOfTicketsToCancel + " ticket(s) canceled successfully.");
//                    } else {
//                        out.println("Invalid number of tickets to cancel or no purchased tickets found.");
//                    }
//
//                    // Redirect after successful action
//                    response.sendRedirect("ViewPurchasedTickets");
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




//@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
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
//        try {
//            // Get the user's purchased tickets and display them with a cancel button
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
//                    
//                    String cancelTicketsStr = request.getParameter("cancelTickets");
//                    int cancelTickets=Integer.parseInt(cancelTicketsStr);
//                    
//                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//                    int movieId = 0;
//                    int at = 0;
//                    int nt = 0;
//                    int ct=0;
//                    if (!purchasedTickets.isEmpty()) {
//                        // Assuming that there is only one movie associated with the purchased tickets
//                    	   String movieIdStr = request.getParameter("movieId");
//                           movieId = Integer.parseInt(movieIdStr);
//                           
//                         // movieId = purchasedTickets.get(0).getMovieId();
//                     
//                        
//                        Movies movie = movieDao.getMoviesById(movieId);
//                        at = movie.getAvailableTickets();
//
//                        for (PurchasedTickets pt : purchasedTickets) {
//                            nt = pt.getNumberOfTickets();
//                            ct=nt-cancelTickets;
//                        }
//
//                      //  int aTnT = at + nt;
//                        int aTnT = at + ct;
//                        Movies updatedMovie = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), aTnT);
//                        movieDao.updateMovies(updatedMovie, movieId);
//                    }
//
//                    String deleteTicketIdStr = request.getParameter("ticketId");
//
//                    if (deleteTicketIdStr != null) {
//                        try {
//                            int deleteTicketId = Integer.parseInt(deleteTicketIdStr);
//                            purchasedTicketDao.deleteTickets(deleteTicketId);
//                            out.println("Ticket deleted successfully.");
//                        } catch (NumberFormatException e) {
//                            out.println("Invalid Ticket ID.");
//                        } catch (Exception e) {
//                            // Handle any other exceptions here
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            // Redirect after successful deletion
//                            response.sendRedirect("ViewPurchasedTickets");
//                        } catch (IOException e) {
//                            out.println("Error while redirecting.");
//                        }
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
//
// public class CancelTickets extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
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
//       out.println("</form>");
//
//        try {
//           // Get the user's purchased tickets and display them with a cancel button
//            HttpSession session = request.getSession(false);
//
//           if (session != null) {
//               User loggedInUser = (User) session.getAttribute("LoggedInUser");
////
//                if (loggedInUser != null) {
//                    int userId = loggedInUser.getUserId();
//
//                    // Fetch purchased tickets for the user
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//
//                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//                    int movieId = 0;
//                    int at = 0;
//                    int nt = 0;
//                    
//                  
//                    
//                    if (!purchasedTickets.isEmpty()) {
//                        // Assuming that there is only one movie associated with the purchased tickets
//                        String movieIdStr = request.getParameter("movieId");
//                        movieId = Integer.parseInt(movieIdStr);
//
//                        Movies movie = movieDao.getMoviesById(movieId);
//                        at = movie.getAvailableTickets();
//
//                        for (PurchasedTickets pt : purchasedTickets) {
//                            nt = pt.getNumberOfTickets();
//                        }
//                    }
//
//                    String deleteTicketIdStr = request.getParameter("ticketId");
//                    String numberOfTicketsStr = request.getParameter("number_of_tickets");
//                    
//                   
//                    
//                    if (deleteTicketIdStr != null) {
//                        try {
//                            int deleteTicketId = Integer.parseInt(deleteTicketIdStr);
//                           
//                            
//                            if (numberOfTicketsStr != null) {
//                                int numberOfTicketsToDelete = Integer.parseInt(numberOfTicketsStr);
//
//                                if (numberOfTicketsToDelete > 0 && numberOfTicketsToDelete <= nt) {
//                                    // Increase the available tickets count and update the movie
//                                    int aTnT = at + numberOfTicketsToDelete;
//                                    Movies movie = null;
//									Movies updatedMovie = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), aTnT);
//                                    movieDao.updateMovies(updatedMovie, movieId);
//                                    
//                                    
//                                    
//                                    // Decrease the purchased tickets count in the database
//                                    PurchasedTickets purchasedTicket = purchasedTicketDao.getTicketsById(deleteTicketId);
//                                    
//                                    PurchasedTickets purTic =purchasedTicketDao.getTicketsById(movieId);
//                                    int tickId = purTic.getTicketId();
//                                    int newPurchasedTicketsCount = purchasedTicket.getNumberOfTickets() - numberOfTicketsToDelete;
//                                    if (newPurchasedTicketsCount > 0) {
//                                    	 PurchasedTickets pt = new PurchasedTickets(userId, movieId, purTic.getPurchaseDate(),"Purchased",newPurchasedTicketsCount);
//                                        purchasedTicketDao.updateTickets(pt, newPurchasedTicketsCount);
//                                    } else {
//                                        // Delete the entire purchased ticket
//                                        purchasedTicketDao.deleteTickets(deleteTicketId);
//                                    }
//
//                                    out.println("Tickets deleted successfully.");
//                                } else {
//                                    out.println("Invalid number of tickets specified.");
//                                }
//                            }
//                        } catch (NumberFormatException e) {
//                            out.println("Invalid Ticket ID.");
//                        } catch (Exception e) {
//                            // Handle any other exceptions here
//                            e.printStackTrace();
//                        }
//
//                        try {
//                            // Redirect after successful deletion
//                            response.sendRedirect("ViewPurchasedTickets");
//                        } catch (IOException e) {
//                            out.println("Error while redirecting.");
//                        }
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

 
 
 
 
 
 
 
 
 
// important
// package com.onlinecinema.servlet;
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
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
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
//        out.println();
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
//                    MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                    // Get parameters from the request
//                    String movieIdStr = request.getParameter("movieId");
//                    String ticketIdStr = request.getParameter("ticketId");
////                    String numberOfTicketsStr = request.getParameter("numberofTickets");
////                    int not=Integer.parseInt(numberOfTicketsStr);
//
//                    if (movieIdStr != null && ticketIdStr != null) {
//                        try {
//                            int movieId = Integer.parseInt(movieIdStr);
//                            int ticketId = Integer.parseInt(ticketIdStr);
//
//                            Movies movie = movieDao.getMoviesById(movieId);
//                            PurchasedTickets purchasedTicket = purchasedTicketDao.getTicketsById(ticketId);
//
//                            if (movie != null && purchasedTicket != null) {
//                                int numberOfTicketsToCancel = purchasedTicket.getNumberOfTickets();
//
//                                if (numberOfTicketsToCancel > 0) {
//                                    // Increase the available tickets count and update the movie
//                                    int newAvailableTickets = movie.getAvailableTickets() + numberOfTicketsToCancel;
//                                    movie.setAvailableTickets(newAvailableTickets);
//                                    movieDao.updateMovies(movie, movieId);
//
//                                    // Decrease the purchased tickets count in the database
//                                    int newPurchasedTicketsCount = purchasedTicket.getNumberOfTickets() - numberOfTicketsToCancel;
//
//                                    if (newPurchasedTicketsCount > 0) {
//                                        purchasedTicket.setNumberOfTickets(newPurchasedTicketsCount);
//                                        purchasedTicketDao.updateTickets(purchasedTicket, newPurchasedTicketsCount);
//                                    } else {
//                                        // Delete the entire purchased ticket if no tickets left
//                                        purchasedTicketDao.deleteTickets(ticketId);
//                                    }
//
//                                    out.println("Tickets canceled successfully.");
//                                    response.sendRedirect("ViewPurchasedTickets");
//                                } else {
//                                    out.println("No tickets to cancel.");
//                                }
//                            } else {
//                                out.println("Invalid movie or purchased ticket.");
//                            }
//                        } catch (NumberFormatException e) {
//                            out.println("Invalid parameters.");
//                        }
//                    } else {
//                        out.println("Missing parameters.");
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









 
 
// important
// package com.onlinecinema.servlet;
//
//import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
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
//////@WebServlet("/CancelTickets")
//   public class CancelTickets extends HttpServlet {
// 
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//        out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
////        // Add a Logout button
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form>");
//        
//        
//        
//        
//        
//        
//        
// try {
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
//                    String movieIdStr = request.getParameter("movieId");
//                    String ticketIdStr = request.getParameter("ticketId");
//                    int ticketId=Integer.parseInt(ticketIdStr);
//                    
//                    if (!purchasedTickets.isEmpty()) {
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//                            Movies movie = movieDao.getMoviesById(movieId);
//                            
//                            out.println("<form action='CancelTickets' method='get'>");
//                            out.println("<h1>Purchase Tickets for " + movie.getTitle() + "</h1>");
//                            out.println("<p>Number Of Tickets Purchased: " + ticket.getNumberOfTickets() + "</p>");
//                            out.println("<p>How Many Tickets You Want To Cancel</p><input type=text name=cancelticket>");
//                            out.println("<input type='submit'  >");
//                            out.println("</form>");
//                            
//                            
//                            
//                            int numOfPt= ticket.getNumberOfTickets();
//                            String cancelTicketsStr=request.getParameter("cancelticket");
//                            int cancelTickets=Integer.parseInt(cancelTicketsStr);
//                            
//                            int n=numOfPt-cancelTickets;
//                            
//                            
//                            if(cancelTickets<numOfPt) {
//                            	 PurchasedTickets purTic=new PurchasedTickets(userId,movieId,ticket.getPurchaseDate(),"PURCHASED",n);
//                                 purchasedTicketDao.updateTickets(purTic, ticketId);
//                                 out.println("Tickets Successfully Canceled");
//                            }else {
//                            	out.println("That Many Tickets Are Not Available");
//                            }
//                            
//                            int avt=movie.getAvailableTickets();
//                            
//                            avt=avt +cancelTickets;
//                            Movies mov=new Movies(movie.getTitle(),movie.getDescription(),movie.getReleaseDate(),movie.getTicketPrice(),avt);
//                            movieDao.updateMovies(mov, movieId);
//                           // Movies movie1 = movieDao.getMoviesById(movieId);
//                           
//                            
//                           
//
//                          
//                         
//                          
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
//	}
//   }











//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//        out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form>");
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
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//
//                    if (!purchasedTickets.isEmpty()) {
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//                            Movies movie = movieDao.getMoviesById(movieId);
//
//                            out.println("<form action='CancelTickets' method='get'>");
//                            out.println("<h1>Purchase Tickets for " + movie.getTitle() + "</h1>");
//                            out.println("<p>Number Of Tickets Purchased: " + ticket.getNumberOfTickets() + "</p>");
//                            out.println("<p>How Many Tickets You Want To Cancel</p><input type='text' name='cancelticket'>");
//                            out.println("<input type='submit'  >");
//                            out.println("</form>");
//
//                            int numOfPt = ticket.getNumberOfTickets();
//                            String cancelTicketsStr = request.getParameter("cancelticket");
//
//                            if (cancelTicketsStr != null) {
//                                int cancelTickets = Integer.parseInt(cancelTicketsStr);
//                                int n = numOfPt - cancelTickets;
//
//                              //  if (cancelTickets > 0 && cancelTickets <= numOfPt) {
//                                if (cancelTickets<=numOfPt) {
//                                    PurchasedTickets purTic = new PurchasedTickets(userId, movieId, ticket.getPurchaseDate(), "PURCHASED", n);
//                                    purchasedTicketDao.updateTickets(purTic, ticket.getTicketId());
//                                    out.println("Tickets Successfully Canceled");
//
//                                    int avt = movie.getAvailableTickets();
//                                    avt = avt + cancelTickets;
//                                    Movies mov = new Movies(movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getTicketPrice(), avt);
//                                    movieDao.updateMovies(mov, movieId);
//                                } else {
//                                    out.println("Invalid number of tickets to cancel.");
//                                }
//                            }
//                        }
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











//package com.onlinecinema.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//        out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form>");
//        String ticketIdStr = request.getParameter("ticketId");
//        int ticketId=Integer.parseInt(ticketIdStr);
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
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//
//                    if (!purchasedTickets.isEmpty()) {
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        out.println("<form action='CancelTickets' method='get'>");
//                        out.println("<select name='movieId'>");
//                        
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//                            Movies movie = movieDao.getMoviesById(movieId);
//                           // out.println("<option value='" + movieId + "'>" + movie.getTitle() + "</option>");
//                        }
//                        
//                        out.println("</select>");
//                        out.println("<input type='submit' value='Show Movie'>");
//                        out.println("</form>");
//                        
//                        String selectedMovieIdStr = request.getParameter("movieId");
//
//                        if (selectedMovieIdStr != null) {
//                            int selectedMovieId = Integer.parseInt(selectedMovieIdStr);
//                            Movies selectedMovie = movieDao.getMoviesById(selectedMovieId);
//
//                            out.println("<h1>Purchase Tickets for " + selectedMovie.getTitle() + "</h1>");
//
//                            for (PurchasedTickets ticket : purchasedTickets) {
//                                int movieId = ticket.getMovieId();
//
//                                if (movieId == selectedMovieId) {
//                                	out.println("<form action=CancelTickets method=get>");
//                                    out.println("<p>Number Of Tickets Purchased: " + ticket.getNumberOfTickets() + "</p>");
//                                    out.println("<p>How Many Tickets You Want To Cancel</p><input type='text' name='cancelticket'>");
//                                    out.println("<input type='submit'  >");
//                                    out.println("</form>");
//
//                                    int numOfPt = ticket.getNumberOfTickets();
//                                    String cancelTicketsStr = request.getParameter("cancelticket");
//
//                                    if (cancelTicketsStr != null) {
//                                        int cancelTickets = Integer.parseInt(cancelTicketsStr);
//                                        int n = numOfPt - cancelTickets;
//
//                                        if (cancelTickets > 0 && cancelTickets <= numOfPt) {
//                                            PurchasedTickets purTic = new PurchasedTickets(userId, movieId, ticket.getPurchaseDate(), "PURCHASED", n);
//                                            purchasedTicketDao.updateTickets(purTic, ticketId);
//                                            out.println("Tickets Successfully Canceled");
//
//                                            int avt = selectedMovie.getAvailableTickets();
//                                            avt = avt + cancelTickets;
//                                            Movies mov = new Movies(selectedMovie.getTitle(), selectedMovie.getDescription(), selectedMovie.getReleaseDate(), selectedMovie.getTicketPrice(), avt);
//                                            movieDao.updateMovies(mov, selectedMovieId);
//                                        } else {
//                                            out.println("Invalid number of tickets to cancel.");
//                                        }
//                                    }
//                                }
//                            }
//                        }
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
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.beans.PurchasedTickets;
//import com.onlinecinema.beans.User;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.dao.PurchasedTicketDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
////@WebServlet("/CancelTickets")
//public class CancelTickets extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        out.println("<style>");
//        out.println(".logout-button {");
//        out.println("  position: absolute;");
//        out.println("  top: 10px;");
//        out.println("  right: 10px;");
//        out.println("}");
//        out.println("</style>");
//
//        out.println("<form action='LogoutServlet' method='get' class='logout-button'>");
//        out.println("<input type='submit' value='Logout'>");
//        out.println("</form>");
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
//                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
//                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);
//                    
//            
//                    
//                    if (!purchasedTickets.isEmpty()) {
//                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());
//
//                        out.println("<form action='CancelTickets' method='get'>");
//                        out.println("<select name='movieId'>");
//
//                        for (PurchasedTickets ticket : purchasedTickets) {
//                            int movieId = ticket.getMovieId();
//                            Movies movie = movieDao.getMoviesById(movieId);
//                            out.println("<option value='" + movieId + "'>" + movie.getTitle() + "</option>");
//                        }
//
//                        out.println("</select>");
//                        out.println("<input type='submit' value='Show Movie'>");
//                        out.println("</form>");
//
//                        String selectedMovieIdStr = request.getParameter("movieId");
//
//                        if (selectedMovieIdStr != null) {
//                            int selectedMovieId = Integer.parseInt(selectedMovieIdStr);
//                            Movies selectedMovie = movieDao.getMoviesById(selectedMovieId);
//
//                            out.println("<h1>Purchase Tickets for " + selectedMovie.getTitle() + "</h1>");
//
//                            for (PurchasedTickets ticket : purchasedTickets) {
//                                int movieId = ticket.getMovieId();
//
//                                if (movieId == selectedMovieId) {
//                                    out.println("<form action='CancelTicketRequest' method='get'>");
//                                    out.println("<p>Number Of Tickets Purchased: " + ticket.getNumberOfTickets() + "</p>");
//                                    out.println("<p>How Many Tickets You Want To Cancel</p><input type='text' name='cancelticket'>");
//                                    out.println("<input type='submit'  >");
//                                    out.println("</form>");
//                                   
//                                  //  int numOfPt = ticket.getNumberOfTickets();
//                                    
//                                  //  String cancelTicketsStr = request.getParameter("cancelticket");
//                                    
//                                  
//                                    
//                                  //  if (cancelTicketsStr != null) {
////                                    	out.println("CancelTickets"+cancelTicketsStr);
////                                        int cancelTickets = Integer.parseInt(cancelTicketsStr);
////                                        
////                                        int n = numOfPt - cancelTickets;
////
////                                        if (cancelTickets > 0 && cancelTickets <= numOfPt) {
////                                            PurchasedTickets purTic = new PurchasedTickets(userId, movieId, ticket.getPurchaseDate(), "PURCHASED", n);
////                                            purchasedTicketDao.updateTickets(purTic, ticket.getTicketId());
////                                            out.println("Tickets Successfully Canceled");
////
////                                            int avt = selectedMovie.getAvailableTickets();
////                                            avt = avt + cancelTickets;
////                                            Movies mov = new Movies(selectedMovie.getTitle(), selectedMovie.getDescription(), selectedMovie.getReleaseDate(), selectedMovie.getTicketPrice(), avt);
////                                            movieDao.updateMovies(mov, selectedMovieId);
////                                        } else {
////                                            out.println("Invalid number of tickets to cancel.");
////                                        }
//                                   // }
//                                }
//                            }
//                        }
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
import java.util.List;

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
public class CancelTickets extends HttpServlet {
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
        out.println("</form>");

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                User loggedInUser = (User) session.getAttribute("LoggedInUser");

                if (loggedInUser != null) {
                    int userId = loggedInUser.getUserId();

                    PurchasedTicketDao purchasedTicketDao = new PurchasedTicketDao(ConnectionProvider.getConnection());
                    List<PurchasedTickets> purchasedTickets = purchasedTicketDao.getPurchasedTicketsByUserId(userId);

                    if (!purchasedTickets.isEmpty()) {
                        MoviesDao movieDao = new MoviesDao(ConnectionProvider.getConnection());

                        String selectedMovieIdStr = request.getParameter("movieId");

                        if (selectedMovieIdStr != null) {
                            int selectedMovieId = Integer.parseInt(selectedMovieIdStr);
                            Movies selectedMovie = movieDao.getMoviesById(selectedMovieId);

                            out.println("<h1>Purchase Tickets for " + selectedMovie.getTitle() + "</h1>");

                            for (PurchasedTickets ticket : purchasedTickets) {
                                int movieId = ticket.getMovieId();

                                if (movieId == selectedMovieId) {
                                    out.println("<form action='CancelTicketRequest' method='get'>");
                                    out.println("<p>Number Of Tickets Purchased: " + ticket.getNumberOfTickets() + "</p>");
                                    out.println("<input type=hidden name=ticketId value= "+ ticket.getTicketId() +" ></p>");
                                    out.println("<p>How Many Tickets You Want To Cancel</p><input type='text' name='cancelticket'>");
                                    out.println("<input type='submit'  >");
                                    out.println("</form>");
//
//                                    int numOfPt = ticket.getNumberOfTickets();
//                                    String cancelTicketsStr = request.getParameter("cancelticket");
//
//                                    if (cancelTicketsStr != null) {
//                                        int cancelTickets = Integer.parseInt(cancelTicketsStr);
//
//                                        int n = numOfPt - cancelTickets;
//
//                                        if (cancelTickets > 0 && cancelTickets <= numOfPt) {
//                                            PurchasedTickets purTic = new PurchasedTickets(userId, movieId, ticket.getPurchaseDate(), "PURCHASED", n);
//                                            purchasedTicketDao.updateTickets(purTic, ticket.getTicketId());
//                                            out.println("Tickets Successfully Canceled");
//
//                                            int avt = selectedMovie.getAvailableTickets();
//                                            avt = avt + cancelTickets;
//                                            Movies mov = new Movies(selectedMovie.getTitle(), selectedMovie.getDescription(), selectedMovie.getReleaseDate(), selectedMovie.getTicketPrice(), avt);
//                                            movieDao.updateMovies(mov, selectedMovieId);
//                                        } else {
//                                            out.println("Invalid number of tickets to cancel.");
//                                        }
//                                    }else {
//                                    	out.println("Cancel Ticket is null");
//                                    }
                                }
                            }
                        }
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
