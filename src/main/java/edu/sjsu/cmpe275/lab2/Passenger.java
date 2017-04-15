package edu.sjsu.cmpe275.lab2;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="passenger")
public class Passenger {

		@Id
		private String id;  
		
		@Column(name = "FIRST_NAME" )
	    private String firstname;
		
		@Column(name = "LAST_NAME" )
	    private String lastname;
		
		@Column(name = "AGE" )
	    private int age;
		
		@Column(name = "GENDER" )
	    private String gender;
		
		@Column(name = "PHONE" , unique = true)
	    private String phone;
		
		//private List<Reservation> reservations = new ArrayList<>();
	
		
		public Passenger(){}
		
	 	public Passenger(String id, String firstname, String lastname, int age, String gender, String phone) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}
	 	
	 	
	 	//@GeneratedValue
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
//		@OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER)
//		public List<Reservation>  getReservations() {
//			return reservations;
//		}
}
