package com.onlinecinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.onlinecinema.beans.PurchasedTickets;

public class PurchasedTicketDao {
private  final Connection con;
	
	public PurchasedTicketDao(Connection con){
		
		this.con=con;
	}
	
	 public  List<PurchasedTickets> getAllPurchasedTickets() throws Exception {
		    String query = "select * from purchasedtickets";
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
		        List<PurchasedTickets> beans=new ArrayList();
		
		        while (result.next()) {
		        	PurchasedTickets bean = new PurchasedTickets();
		            bean.setTicketId(result.getInt("ticket_id"));
		            bean.setUserId(result.getInt("user_id"));
		            bean.setMovieId(result.getInt("movie_id"));
		            bean.setPurchaseDate(result.getDate("purchase_date"));
		            bean.setTicketStatus(result.getString("ticket_status"));
		     
		           
		
		            // Print the values of all columns
		            System.out.println("Ticket ID: " + bean.getTicketId());
		            System.out.println("User Id: " + bean.getUserId());
		            System.out.println("Movie ID: " + bean.getMovieId());
		            System.out.println("Purchase Date: " + bean.getPurchaseDate());
		            System.out.println("Ticket Status: " + bean.getTicketStatus());
		            
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
	 
	 
	 

	    public  List<PurchasedTickets> getTicketsByMovieId(int movieId) throws Exception {
		    String query = "select * from purchasedtickets where movie_id=" + movieId;
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		        List<PurchasedTickets> beans=new ArrayList();
		
		        while(result.next()) {
		        	PurchasedTickets bean = new PurchasedTickets();
		            bean.setTicketId(result.getInt("ticket_id"));
		            bean.setUserId(result.getInt("user_id"));
		            bean.setMovieId(result.getInt("movie_id"));
		            bean.setPurchaseDate(result.getDate("purchase_date"));
		            bean.setTicketStatus(result.getString("ticket_status"));
		            bean.setNumberOfTickets(result.getInt("number_of_tickets"));
		         //   numberOfTickets
		           
		
		            // Print the values of all columns
		            System.out.println("Ticket ID: " + bean.getTicketId());
		            System.out.println("User Id: " + bean.getUserId());
		            System.out.println("Movie ID: " + bean.getMovieId());
		            System.out.println("Purchase Date: " + bean.getPurchaseDate());
		            System.out.println("Ticket Status: " + bean.getTicketStatus());
		            System.out.println("NumberOfTickets: " + bean.getNumberOfTickets());
		            
		            System.out.println("*****************************************");
			            
		            beans.add(bean);
		          
		        }
		        return beans; // Return null if no product with the specified ID was found
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
	    
	    public  PurchasedTickets getTicketsById(int ticketId) throws Exception {
		    String query = "select * from purchasedtickets where ticket_id=" + ticketId;
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		
		        if (result.next()) {
		        	PurchasedTickets bean = new PurchasedTickets();
		            bean.setTicketId(result.getInt("ticket_id"));
		            bean.setUserId(result.getInt("user_id"));
		            bean.setMovieId(result.getInt("movie_id"));
		            bean.setPurchaseDate(result.getDate("purchase_date"));
		            bean.setTicketStatus(result.getString("ticket_status"));
		            bean.setNumberOfTickets(result.getInt("number_of_tickets"));
		         //   numberOfTickets
		           
		
		            // Print the values of all columns
		            System.out.println("Ticket ID: " + bean.getTicketId());
		            System.out.println("User Id: " + bean.getUserId());
		            System.out.println("Movie ID: " + bean.getMovieId());
		            System.out.println("Purchase Date: " + bean.getPurchaseDate());
		            System.out.println("Ticket Status: " + bean.getTicketStatus());
		            System.out.println("NumberOfTickets: " + bean.getNumberOfTickets());
		            
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
	    
	    public  List<PurchasedTickets> getPurchasedTicketsByUserId(int userId) throws Exception {
		    String query = "select * from purchasedtickets where user_id=" + userId;
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		        List<PurchasedTickets> beans=new ArrayList();
		        
		        while(result.next()) {
		        	PurchasedTickets bean = new PurchasedTickets();
		            bean.setTicketId(result.getInt("ticket_id"));
		            bean.setUserId(result.getInt("user_id"));
		            bean.setMovieId(result.getInt("movie_id"));
		            bean.setPurchaseDate(result.getDate("purchase_date"));
		            bean.setTicketStatus(result.getString("ticket_status"));
		            bean.setNumberOfTickets(result.getInt("number_of_tickets"));
		     
		           
		
		            // Print the values of all columns
		            System.out.println("Ticket ID: " + bean.getTicketId());
		            System.out.println("User Id: " + bean.getUserId());
		            System.out.println("Movie ID: " + bean.getMovieId());
		            System.out.println("Purchase Date: " + bean.getPurchaseDate());
		            System.out.println("Ticket Status: " + bean.getTicketStatus());
		            System.out.println("Number Of Tickets: " + bean.getNumberOfTickets());
		            
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
	    
	    public PurchasedTickets getPurchasedTicketByUserAndMovie(int userId, int selectedMovieId) {
	        String query = "SELECT * FROM purchasedtickets WHERE user_id = ? AND movie_id = ?";
	        
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        PurchasedTickets purchasedTicket = null;

	        try {
	            ps = con.prepareStatement(query);
	            ps.setInt(1, userId);
	            ps.setInt(2, selectedMovieId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                int ticketId = rs.getInt("ticket_id");
	                Date purchaseDate = rs.getDate("purchase_date");
	                String ticketStatus = rs.getString("ticket_status");
	                int numberOfTickets = rs.getInt("number_of_tickets");

	                purchasedTicket = new PurchasedTickets(userId, selectedMovieId, purchaseDate, ticketStatus, numberOfTickets);
	                purchasedTicket.setTicketId(ticketId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return purchasedTicket;
	    }



//	    public int addTickets(PurchasedTickets tic) throws Exception {
//	        String query = "INSERT INTO purchasedtickets (user_id,movie_id,purchase_date,ticket_status,number_of_tickets) VALUES (?, ?, ?, ?, ?)";
//	        System.out.println(query);
//	        
//
//	        PreparedStatement ps = null;
//	        try {
//	            ps = con.prepareStatement(query);
//	            ps.setInt(1, tic.getUserId());
//	            ps.setInt(2, tic.getMovieId());
//	            ps.setDate(3, (java.sql.Date) tic.getPurchaseDate());
//	            ps.setString(4, tic.getTicketStatus());
//	            ps.setInt(5, tic.getNumberOfTickets());
//	        
//	 
//	            int rows = ps.executeUpdate();
//	            return rows;
//	        } finally {
//	            if (ps != null) {
//	                ps.close();
//	            }
//	        }
//	    }
	    public int addTickets(PurchasedTickets tic) throws Exception {
	        String query = "INSERT INTO purchasedtickets (user_id, movie_id, purchase_date, ticket_status, number_of_tickets) VALUES (?, ?, ?, ?, ?)";
	        
	        System.out.println(query);

	        PreparedStatement ps = null;
	        try {
	            ps = con.prepareStatement(query);
	            ps.setInt(1, tic.getUserId());
	            ps.setInt(2, tic.getMovieId());

	            // Convert java.util.Date to java.sql.Date
	            ps.setDate(3, new java.sql.Date(tic.getPurchaseDate().getTime()));

	            ps.setString(4, tic.getTicketStatus());
	            ps.setInt(5, tic.getNumberOfTickets());

	            int rows = ps.executeUpdate();
	            return rows;
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }
	    }

	    public int updateTickets(PurchasedTickets tic,int ticketId) throws Exception {
	   //   String query = "UPDATE purchasedtickets SET user_id=?, movie_id=?, purchase_date=?, ticket_status=?, number_of_tickets  WHERE ticket_id=?";
	        String query = "UPDATE purchasedtickets SET user_id=?, movie_id=?, purchase_date=?, ticket_status=?, number_of_tickets=? WHERE ticket_id=?";

	        System.out.println(query);

	        PreparedStatement ps = null;
	        int rowsUpdated = 0;

	        try {
	            ps = con.prepareStatement(query);
	            ps.setInt(1, tic.getUserId());
	            ps.setInt(2, tic.getMovieId());
	            ps.setDate(3, (java.sql.Date) tic.getPurchaseDate());
	            ps.setString(4, tic.getTicketStatus());
	            ps.setInt(5, tic.getNumberOfTickets());
	            ps.setInt(6, tic.getTicketId());


	            rowsUpdated = ps.executeUpdate();
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }

	        return rowsUpdated;
	    }


 
	    public  int deleteTickets(int ticketId)throws Exception{
	        String query="delete from purchasedtickets where ticket_id="+ticketId;
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
	    }

//		public PurchasedTickets getPurchasedTicketByUserAndMovie(int userId, int selectedMovieId) {
//			 String query="select * from purchasedtickets";
//		}
}
