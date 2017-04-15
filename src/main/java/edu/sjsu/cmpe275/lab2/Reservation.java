package edu.sjsu.cmpe275.lab2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Reservation {

		@Column(name = "ORDER_NUMBER" )
	    private String orderNumber;
		
		@Column(name = "PASSENGER_ID" )
	    private Passenger passenger;
		
		@Column(name = "PRICE" )
	    private int price; // sum of each flight’s price.
		
		private List<Flight> flights = new ArrayList<>();
		
		public Reservation(){}
		
		public Reservation(String orderNumber, Passenger passenger, int price) {
		super();
		this.orderNumber = orderNumber;
		this.passenger = passenger;
		this.price = price;
	}

		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "PASSENGER_ID")
		public Passenger getPassenger() {
			return passenger;
		}

		public void setPassenger(Passenger passenger) {
			this.passenger = passenger;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@OneToMany(mappedBy = "flight", fetch = FetchType.EAGER)
		public void getFlights(List<Flight> flights) {
			this.flights = flights;
		}
		
}
