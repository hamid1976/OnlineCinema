//package com.onlinecinema.servlet;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import com.onlinecinema.beans.Movies;
//import com.onlinecinema.dao.MoviesDao;
//import com.onlinecinema.helper.ConnectionProvider;
//
//public class AddMovie extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 response.setContentType("text/html");
//	      PrintWriter out = response.getWriter();
//	      
//	     
//	      MoviesDao dao=new MoviesDao(ConnectionProvider.getConnection());
//	      
//	      out.println("<h1>Add Movies</h1>");
//	      out.println("<form action=\"AddMovie\" method=\"get\">");
//	      out.println("<table border='1'>");
//	      out.println("<tr><td>Title:		</td><td> <input type=\"text\" name=\"title\"></td></tr>");
//	      out.println("<tr><td>Description: </td><td> <input type=\"text\" name=\"description\"></td></tr>");
//	      out.println("<tr><td>Release Date:</td><td> <input type=\"text\" name=\"releaseDate\"></td></tr>");
//	      out.println("<tr><td>Ticket Price:</td><td> <input type=\"text\" name=\"ticketPrice\"></td></tr>");
//	      out.println("</table>");
//	      out.println("<input type=\"submit\" value=\"Submit\">");
//	      out.println("</form>");
//
//	      String title = request.getParameter("title");
//	      String description = request.getParameter("description");
//	      String release = request.getParameter("releaseDate");
//	      String ticketprice = request.getParameter("ticketPrice");
//	      
//	      
//       if (title != null && description != null && release != null && ticketprice != null ) {
//           try {
//             
//               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//               Date releaseDate = null;
//               releaseDate = dateFormat.parse(release);
//               double ticketPrice=Double.parseDouble(ticketprice);
//              
//         
//              
//    	      Movies ob1=new Movies(title,description,releaseDate,ticketPrice);
//    	      dao.addMovies(ob1);
//              out.println("Movie added successfully.");
//          } catch (NumberFormatException e) {
//               out.println("Invalid input data.");
//           } catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       }
//       //out.println("<a href=http://localhost:8080/ECommerce/AddServlet>AddProduct</a>");
//	    
//       out.println("<a href=ShowMovies>View Movies</a>");
//	}
//
//	
//
//}



package com.onlinecinema.servlet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.dao.MoviesDao;
import com.onlinecinema.helper.ConnectionProvider;

//@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        MoviesDao dao = new MoviesDao(ConnectionProvider.getConnection());
        
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
        
        
        out.println("<a href=ShowMovies>Show Movies</a>");
        
        out.println("<h1>Add Movies</h1>");
        out.println("<form action=\"AddMovie\" method=\"get\">");
        out.println("<table border='1'>");
        out.println("<tr><td>Title:</td><td> <input type=\"text\" name=\"title\"></td></tr>");
        out.println("<tr><td>Description:</td><td> <input type=\"text\" name=\"description\"></td></tr>");
        out.println("<tr><td>Release Date:</td><td> <input type=\"text\" name=\"releaseDate\"></td></tr>");
        out.println("<tr><td>Ticket Price:</td><td> <input type=\"text\" name=\"ticketPrice\"></td></tr>");
        out.println("<tr><td>Available Tickets:</td><td> <input type=\"text\" name=\"availableTickets\"></td></tr>");
        out.println("</table>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String release = request.getParameter("releaseDate");
        String ticketPrice = request.getParameter("ticketPrice");
        String availableTicketStr = request.getParameter("availableTickets");
        
        try {
            if (title != null && description != null && release != null && ticketPrice != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date releaseDate = dateFormat.parse(release);
                double ticketPriceValue = Double.parseDouble(ticketPrice);
                int availableTicket =Integer.parseInt(availableTicketStr);
                
                Movies movie = new Movies(title, description, releaseDate, ticketPriceValue,availableTicket);
                dao.addMovies(movie);
                out.println("Movie added successfully.");
            } else {
                out.println("Please fill in all the required fields.");
            }
        } catch (NumberFormatException e) {
            out.println("Invalid ticket price. Please enter a valid number.");
        } catch (ParseException e) {
            out.println("Invalid date format. Please use yyyy-MM-dd format.");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while adding the movie.");
        } finally {
            out.close();
        }
        
        out.println("<a href=ShowMovies>View Movies</a>");
    }
}
