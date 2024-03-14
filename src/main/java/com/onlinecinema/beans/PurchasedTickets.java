//package com.onlinecinema.beans;
//
//import java.util.Date.*;
//
//public class PurchasedTickets {
//	private int ticketId;
//	private int userId;
//	private int movieId;
//	private Date purchaseDate ;
//	private String ticketStatus;
//	private int numberOfTickets;
//	
//	
//
//	public PurchasedTickets(){
//		
//	}
//	
//	public PurchasedTickets(int userId, int movieId, Date purchaseDate2, String ticketStatus,int numberOfTickets) {
//		super();
//		this.userId = userId;
//		this.movieId = movieId;
//		this.purchaseDate =purchaseDate2;
//		this.ticketStatus = ticketStatus;
//		this.numberOfTickets = numberOfTickets;
//		
//	}
//	
//	public int getTicketId() {
//		return ticketId;
//	}
//	public void setTicketId(int ticketId) {
//		this.ticketId = ticketId;
//	}
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//	public int getMovieId() {
//		return movieId;
//	}
//	public void setMovieId(int movieId) {
//		this.movieId = movieId;
//	}
//	public Date getPurchaseDate() {
//		return purchaseDate;
//	}
//	public void setPurchaseDate(Date purchaseDate) {
//		this.purchaseDate = purchaseDate;
//	}
//	public String getTicketStatus() {
//		return ticketStatus;
//	}
//	public void setTicketStatus(String ticketStatus) {
//		this.ticketStatus = ticketStatus;
//	}
//	public int getNumberOfTickets() {
//		return numberOfTickets;
//	}
//
//	public void setNumberOfTickets(int numberOfTickets) {
//		this.numberOfTickets = numberOfTickets;
//	}
//
//}






package com.onlinecinema.beans;

import java.util.Date;

public class PurchasedTickets {
    private int ticketId;
    private int userId;
    private int movieId;
    private Date purchaseDate;
    private String ticketStatus;
    private int numberOfTickets;

    public PurchasedTickets() {

    }

    public PurchasedTickets(int userId, int movieId, Date purchaseDate, String ticketStatus, int numberOfTickets) {
        super();
        this.userId = userId;
        this.movieId = movieId;
        this.purchaseDate = purchaseDate;
        this.ticketStatus = ticketStatus;
        this.numberOfTickets = numberOfTickets;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}

