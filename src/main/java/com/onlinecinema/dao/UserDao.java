package com.onlinecinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlinecinema.beans.User;


public class UserDao {
	
private  final Connection con;
	
	public UserDao(Connection con){
		
		this.con=con;
	}
	
	 public  List<User> getAllUsers() throws Exception {
		    String query = "select * from users";
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
		        List<User> beans=new ArrayList();
		
		        while (result.next()) {
		            User bean = new User();
		            bean.setUserId(result.getInt("user_id"));
		            bean.setUsername(result.getString("username"));
		            bean.setPassword(result.getString("password"));
		            bean.setFullName(result.getString("full_name"));
		            bean.setEmail(result.getString("email"));
		            bean.setRoll(result.getString("roll"));
		           
		
		            // Print the values of all columns
		            System.out.println("User ID: " + bean.getUserId());
		            System.out.println("Username: " + bean.getUsername());
		            System.out.println("Password: " + bean.getPassword());
		            System.out.println("Full Name: " + bean.getFullName());
		            System.out.println("Email: " + bean.getEmail());
		            System.out.println("Role: " + bean.getRoll());
		            
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

	    public  User getUserById(int userId) throws Exception {
		    String query = "select * from users where user_id=" + userId;
		    System.out.println(query);
		    Statement st = null;
		    ResultSet result = null;
		
		    try {
		        st = con.createStatement();
		        result = st.executeQuery(query);
		
		        if (result.next()) {
		            User bean = new User();
		            bean.setUserId(result.getInt("user_id"));
		            bean.setUsername(result.getString("username"));
		            bean.setPassword(result.getString("password"));
		            bean.setFullName(result.getString("full_name"));
		            bean.setEmail(result.getString("email"));
		            bean.setRoll(result.getString("roll"));
		
		            // Print the values of all columns
		            System.out.println("User ID: " + bean.getUserId());
		            System.out.println("Username: " + bean.getUsername());
		            System.out.println("Password: " + bean.getPassword());
		            System.out.println("Full Name: " + bean.getFullName());
		            System.out.println("Email: " + bean.getEmail());
		            System.out.println("Role: " + bean.getRoll());
		            
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


	    public int addUser(User user) throws Exception {
	        String query = "INSERT INTO users (username, password,full_name,email,roll) VALUES (?, ?, ?, ?, ?)";
	        System.out.println(query);
	        

	        PreparedStatement ps = null;
	        try {
	            ps = con.prepareStatement(query);
	            ps.setString(1, user.getUsername());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getFullName());
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getRoll());
	 
	            int rows = ps.executeUpdate();
	            return rows;
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }
	    }

	    public int updateProduct(User user,int userId) throws Exception {
	        String query = "UPDATE user SET username=?, password=?, full_name=?, email=?, roll=? WHERE user_id=?";
	        System.out.println(query);

	        PreparedStatement ps = null;
	        int rowsUpdated = 0;

	        try {
	            ps = con.prepareStatement(query);
	            ps = con.prepareStatement(query);
	            ps.setString(1, user.getUsername());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getFullName());
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getRoll());
	            ps.setInt(6, user.getUserId());

	            rowsUpdated = ps.executeUpdate();
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	        }

	        return rowsUpdated;
	    }


 
	    public  int deleteUser(int userId)throws Exception{
	        String query="delete from users where user_id="+userId;
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
