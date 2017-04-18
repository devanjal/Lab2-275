package edu.sjsu.cmpe275.lab2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Reservation {
		@Id
		@Column(name = "ORDER_NUMBER" )
	    private String orderNumber;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "PASSENGER_ID")
	    private Passenger passenger;
		
		@Column(name = "PRICE" )
	    private int price; // sum of each flight’s price.
		
		@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
		@JoinTable(
				name="T_RESERVATION_FLIGHT",
				joinColumns={@JoinColumn(name="RESERVATION_NO", referencedColumnName="ORDER_NUMBER")},
				inverseJoinColumns={@JoinColumn(name="FLIGHT_ID", referencedColumnName="NUMBER")})
		private List<Flight> flights= new ArrayList<>();
		
		public Reservation(){}
		
		public Reservation(String orderNumber, Passenger passenger, List<Flight> flights) {
		super();
		this.orderNumber = orderNumber;
		this.passenger = passenger;
		this.flights = flights;
	}

		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		

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

		public List<Flight> getFlights() {
			return flights;
		}
		public void setFlights(List<Flight> flights) {
			this.flights = flights;
		}
}
