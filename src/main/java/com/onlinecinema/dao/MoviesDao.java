package com.onlinecinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlinecinema.beans.Movies;
import com.onlinecinema.beans.PurchasedTickets;
import com.onlinecinema.beans.User;
import com.onlinecinema.servlet.PurchaseTickets;

public class MoviesDao {
	private  final Connection con;
	
	public MoviesDao(Connection con){
		
		this.con=con;
	}
	
	 public  List<Movies> getAllMovies() throws Exception {
		    String query = "select * from movies";
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        try {
					try {
						st = con.createStatement();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        result = st.executeQuery(query);
		        List<Movies> beans=new ArrayList();
		
		        while (result.next()) {
		            Movies bean = new Movies();
		            bean.setMovieId(result.getInt("movie_id"));
		            bean.setTitle(result.getString("title"));
		            bean.setDescription(result.getString("description"));
		            bean.setReleaseDate(result.getDate("release_date"));
		            bean.setTicketPrice(result.getDouble("ticket_price"));
		            bean.setAvailableTickets(result.getInt("available_tickets"));
		     
		           
		
		            // Print the values of all columns
		            System.out.println("Movies ID: " + bean.getMovieId());
		            System.out.println("Title: " + bean.getTitle());
		            System.out.println("Description: " + bean.getDescription());
		            System.out.println("Release Date: " + bean.getReleaseDate());
		            System.out.println("Ticket Price: " + bean.getTicketPrice());
		            System.out.println("Available Tickets : " + bean.getAvailableTickets());
		            
		            System.out.println("*****************************************");
		            
		           beans.add(bean);
		        }
		        return beans;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        try {
		            if (result != null)
		                result.close();
		            if (st != null)
		                st.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		}//end getAllProducts

	    public  Movies getMoviesById(int movieId) throws Exception {
		    String query = "select * from movies where movie_id=" + movieId;
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		
		        if (result.next()) {
		        	    Movies bean = new Movies();
			            bean.setMovieId(result.getInt("movie_id"));
			            bean.setTitle(result.getString("title"));
			            bean.setDescription(result.getString("description"));
			            bean.setReleaseDate(result.getDate("release_date"));
			            bean.setTicketPrice(result.getDouble("ticket_price"));
			            bean.setAvailableTickets(result.getInt("available_tickets"));
			           
			
			            // Print the values of all columns
			            System.out.println("Movies ID: " + bean.getMovieId());
			            System.out.println("Title: " + bean.getTitle());
			            System.out.println("Description: " + bean.getDescription());
			            System.out.println("Release Date: " + bean.getReleaseDate());
			            System.out.println("Ticket Price: " + bean.getTicketPrice());
			            
			            System.out.println("*****************************************");
			            
		
		            return bean;
		        }
		        return null; // Return null if no product with the specified ID was found
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        try {
		            if (result != null)
		                result.close();
		            if (st != null)
		                st.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		}//end getProductById
	    
	    
	    public List<Movies> getMoviesPurchasedTickets() throws Exception {
		    String query = "SELECT s.movie_id, s.title, s.description, s.release_date, s.ticket_price,s.available_tickets , sc.purchase_date ,sc.number_of_tickets FROM movies s JOIN purchasedtickets sc ON s.movie_id = sc.movie_id JOIN users c ON sc.user_id = c.user_id";
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		        List<Movies> beans=new ArrayList();
		        
		        while (result.next()) {
		        	    Movies bean = new Movies();
		        	    PurchasedTickets bean1 = new PurchasedTickets();
			            bean.setMovieId(result.getInt("movie_id"));
			            bean.setTitle(result.getString("title"));
			            bean.setDescription(result.getString("description"));
			            bean.setReleaseDate(result.getDate("release_date"));	            
			            bean.setTicketPrice(result.getDouble("ticket_price"));
			            bean.setAvailableTickets(result.getInt("available_tickets"));
			            bean1.setPurchaseDate(result.getDate("purchase_date"));
			            bean1.setNumberOfTickets(result.getInt("number_of_tickets"));
			     

			
			            // Print the values of all columns
			            System.out.println("Movies ID: " + bean.getMovieId());
			            System.out.println("Title: " + bean.getTitle());
			            System.out.println("Description: " + bean.getDescription());
			            System.out.println("Release Date: " + bean.getReleaseDate());
			            System.out.println("Ticket Price: " + bean.getTicketPrice());
			            System.out.println("Purchase Date: " + bean1.getPurchaseDate());
			            System.out.println("Number Of Tickets: " + bean1.getNumberOfTickets());
				         
			            
			            
			            System.out.println("*****************************************");
			            
			            beans.add(bean);
		        }
		            return beans;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        try {
		            if (result != null)
		                result.close();
		            if (st != null)
		                st.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		}//end getProductById


	    public int addMovies(Movies mov) throws Exception {
	        String query = "INSERT INTO movies (title,description,release_date,ticket_price,available_tickets) VALUES (?, ?, ?, ?,?)";
	        System.out.println(query);
	        

	        PreparedStatement ps = null;
	        try {
	            ps = con.prepareStatement(query);
	            ps.setString(1, mov.getTitle());
	            ps.setString(2, mov.getDescription());
	            ps.setDate(3, mov.getReleaseDate());
	            ps.setDouble(4, mov.getTicketPrice());
	            ps.setDouble(5, mov.getAvailableTickets());
	        
	 
	            int rows = ps.executeUpdate();
	            return rows;
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }
	    }

	    public int updateMovies(Movies mov, int movieId) throws Exception {
	        String query = "UPDATE movies SET title=?, description=?, release_date=?, ticket_price=?, available_tickets=? WHERE movie_id=?";
	        System.out.println(query);

	        PreparedStatement ps = null;
	        int rowsUpdated = 0;

	        try {
	            ps = con.prepareStatement(query);
	            ps.setString(1, mov.getTitle());
	            ps.setString(2, mov.getDescription());
	            ps.setDate(3, mov.getReleaseDate());
	            ps.setDouble(4, mov.getTicketPrice());
	            ps.setDouble(5, mov.getAvailableTickets());
	            ps.setInt(6, movieId); // Use movieId as the last parameter

	            rowsUpdated = ps.executeUpdate();
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }

	        return rowsUpdated;
	    }



 
	    public  int deleteMovies(int moviesId)throws Exception{
	        String query="delete from movies where movie_id="+moviesId;
	         System.out.println(query);
	         Statement st=null;
	         try{
	             st=con.createStatement();
	             int rows=st.executeUpdate(query);
	             return rows;
	             
	        }finally{
	             if(st!=null){
	                 st.close();
	             }
	        }
	    }//end DeleteUser

}
