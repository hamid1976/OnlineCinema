package com.onlinecinema.helper;

import java.sql.*;

	public class ConnectionProvider {
		
		    private static Connection con;
		    
		    public static Connection getConnection(){
		        if(con==null){
		            try{
		              
		                Class.forName("com.mysql.jdbc.Driver");
		                
		             
		                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinecinema","root","HAMID1976");
		            }catch(Exception e){
		                e.printStackTrace();
		            }
		        }//if con==null
		        return con;
		    }
		    public static void main(String arg[]){
		    
		    getConnection();
		    System.out.println(con);
	    }
	}
		

