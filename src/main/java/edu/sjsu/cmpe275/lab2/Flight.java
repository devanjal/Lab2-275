package edu.sjsu.cmpe275.lab2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
@Entity
public class Flight {
		
		@Id
	    private String number;	// Each flight has a unique flight number.
		
		@Column(name = "PRICE",nullable = false, unique = false)
	    private int price;
		
		@Column(name = "FROM_DEST", nullable = false, unique = false)
	    private String from;
		
		@Column(name = "TO_DEST", nullable = false, unique = false)
	    private String to;

	    /*  Date format: yy-mm-dd-hh, do not include minutes and sceonds.
	    ** Example: 2017-03-22-19
	     The system only needs to supports . You can ignore other time zones.  */
		@Column(name = "DEPT_TIME", nullable = false, unique = false)
		private String departureTime;   
		
		@Column(name = "ARR_TIME", nullable = false, unique = false)
	    private String arrivalTime;
		
		@Column(name = "SEAT_LEFT", nullable = false, unique = false)
	    private int seatsLeft; 
		
		@Column(name = "DESCRIPTION", nullable = true, unique = false)
	    private String description;
		
		@Embedded
		private Plane plane; 
		
		public Flight(){}
		
		public Flight(String number, int price, String from, String to, String departureTime, String arrivalTime,
				int seatsLeft, String description,Plane plane) {
			super();
			this.number = number;
			this.price = price;
			this.from = from;
			this.to = to;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			this.seatsLeft = seatsLeft;
			this.description = description;
			this.plane= plane;
			}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getDepartureTime() {
			return departureTime;
		}
		public void setDepartureTime(String departureTime) {
			this.departureTime = departureTime;
		}
		public String getArrivalTime() {
			return arrivalTime;
		}
		public void setArrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		public int getSeatsLeft() {
			return seatsLeft;
		}
		public void setSeatsLeft(int seatsLeft) {
			this.seatsLeft = seatsLeft;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		public Plane getPlane() {
			return plane;
		}
		
		public void setPlane(Plane plane) {
			this.plane = plane;
		}
		
}
