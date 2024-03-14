//package com.onlinecinema.beans;
//
//import java.sql.Date;
//
//public class Movies{
//    private int movieId;
//    private String title;
//    private String description;
//    private Date releaseDate;
//	private Double ticketPrice;
//	
//    public Movies(){
//    	
//    }
//    
//    public Movies(String title, String description, java.util.Date releaseDate2, Double ticketPrice) {
//		super();
//		this.title = title;
//		this.description = description;
//		this.releaseDate = (Date) releaseDate2;
//		this.ticketPrice = ticketPrice;
//		
//	}
//
//    // Getter and Setter methods for movieId
//    public int getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(int movieId) {
//        this.movieId = movieId;
//    }
//
//    // Getter and Setter methods for title
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    // Getter and Setter methods for description
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    // Getter and Setter methods for releaseDate
//    public Date getReleaseDate() {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(Date releaseDate) {
//        this.releaseDate = releaseDate;
//    }
//
//    // Getter and Setter methods for ticketPrice
//    public Double getTicketPrice() {
//        return ticketPrice;
//    }
//
//    public void setTicketPrice(Double ticketPrice) {
//        this.ticketPrice = ticketPrice;
//    }
//  
//}

package com.onlinecinema.beans;

import java.sql.Date;

public class Movies {
    private int movieId;
    private String title;
    private String description;
    private Date releaseDate;
    private Double ticketPrice;
    private int availableTickets;
    
   
    public Movies() {
        
    }
    
    public Movies(String title, String description, java.util.Date releaseDate2, Double ticketPrice,int availableTickets) {
        super();
        this.title = title;
        this.description = description;
        
        // Convert java.util.Date to java.sql.Date
        if (releaseDate2 != null) {
            this.releaseDate = new Date(releaseDate2.getTime());
        } else {
            this.releaseDate = null; // Set to null if releaseDate2 is null
        }
        
        this.ticketPrice = ticketPrice;
        this.availableTickets = availableTickets;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	
}
