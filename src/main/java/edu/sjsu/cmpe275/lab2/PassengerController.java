package edu.sjsu.cmpe275.lab2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.codehaus.jackson.map.ObjectMapper;

@RestController
public class PassengerController {

   // private static final String template = "Hello, %s!";
	private final AtomicInteger counter = new AtomicInteger();
	private final AtomicInteger counterReserv = new AtomicInteger();
	private final AtomicInteger counterFlight = new AtomicInteger();
	
    @RequestMapping(value="/passenger", method=RequestMethod.POST)
    public Passenger posting(@RequestParam String firstname, @RequestParam String lastname, @RequestParam int age,
    		@RequestParam String gender,@RequestParam String phone) {
    	try{
		    	PassengerDAO pr = new PassengerDAO();   	
		    	Passenger temp= new Passenger(Integer.toString(counter.incrementAndGet()), firstname, lastname,age,gender,phone);
		    	pr.store(temp);
		    	return temp;
    		} catch (RuntimeException e){ throw e;}
    }
    
    @RequestMapping(params = "xml",value="/passenger/{id}", method = RequestMethod.GET, produces= "application/xml" )
    public Passenger gettingXML(@PathVariable String id, @RequestParam boolean xml ) {
    	try{	System.out.println("Im inside");
    			if (xml){
	        		PassengerDAO pr = new PassengerDAO();
	        		System.out.println("ID "+id);
	        	  	return pr.findById(id);
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
    @RequestMapping(params = "json", value="/passenger/{id}", method = RequestMethod.GET, produces= "application/json" )
    public Passenger gettingJSON(@PathVariable String id, @RequestParam boolean  json ) {
    	try{	System.out.println("Im inside");
    			if (json){
	        		PassengerDAO pr = new PassengerDAO();
	        		System.out.println("ID "+id);
	        	  	return pr.findById(id);
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
    
    @RequestMapping(value="/passenger/{id}", method = RequestMethod.PUT)
    public Passenger updating(@PathVariable String id,@RequestParam String firstname, @RequestParam String lastname, @RequestParam int age,
    		@RequestParam String gender,@RequestParam String phone) {
    	try{	
    			
	        	PassengerDAO pr = new PassengerDAO();
	        	//System.out.println("ID "+id);
	        	Passenger temp= new Passenger();
	        	temp = pr.findById(id);
	        	if (firstname!=null){ temp.setFirstname(firstname);}
	        	if (lastname!=null){ temp.setLastname(lastname);}
	        	if (age>0){ temp.setAge(age);}
	        	if (gender!=null){ temp.setGender(gender);}
	        	if (phone!=null){ temp.setPhone(phone);}
	        	pr.store(temp);
	        	return temp;
    		  				
        	} catch (RuntimeException e){ throw e;}
    }
    @RequestMapping(value="/reservation/{id}", method = RequestMethod.GET, produces= "application/json" )
    public String get(@PathVariable String id ) throws IOException {
    	try{	System.out.println("Im inside");
    			
    				ReservationDAO pr = new ReservationDAO();
	        		System.out.println("ID "+id);
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        	  	return "\"reservation\":"+ jso;
    			
    	   	} catch (IOException e){ throw e;}

    }
    @RequestMapping(value="/passenger/{id}", method = RequestMethod.DELETE)
    public String deleting(@PathVariable String id) {
    	try{	
    			
	        	PassengerDAO pr = new PassengerDAO();
	        	System.out.println("ID "+id);
	        	pr.delete(id);
	        	return "success";
    		  				
        	} catch (RuntimeException e){ throw e;}
    }
    
    @RequestMapping(value="/reservation", method=RequestMethod.POST)
    public Reservation posting(@RequestParam String passengerId, @RequestParam String flightLists) {
    	try{
    			ReservationDAO resrv = new ReservationDAO();   	
		    	PassengerDAO psgr = new PassengerDAO(); 
		    	//System.out.println("fightLSIT::"+flightLists);
		    	List<String> items = Arrays.asList(flightLists.split("\\s*,\\s*"));
		    	List<Flight> flights = new ArrayList<Flight>();
		    	FlightDAO fd= new FlightDAO();
		    	int sum_amount=0;
		    	for (String x:items){ 
		    		Flight f = new Flight();
		    		f = fd.findById(x);
		    		if (f.getSeatsLeft() >0){
		    			f.setSeatsLeft(f.getSeatsLeft()-1);
		    			fd.store(f);
			    		flights.add(f);
			    		sum_amount+= fd.findById(x).getPrice();
		    		}
		    		else{ throw new RuntimeException();} 
		    	}
		    	
		    	Reservation temp= new Reservation(Integer.toString(counterReserv.incrementAndGet()), psgr.findById(passengerId), flights);
		    	temp.setPrice(sum_amount);
		    	resrv.store(temp);
		    	return temp;
    		} catch (RuntimeException e){ throw e;}
    }

    @RequestMapping(value="/flight")
    public Flight posting(@RequestParam int price,@RequestParam String from, @RequestParam String to,@RequestParam String departureTime, @RequestParam String arrivalTime,  @RequestParam String description, @RequestParam  int capacity, @RequestParam String model, @RequestParam String manufacturer, @RequestParam int yearOfManufacture) {
    	try{
    			FlightDAO resrv = new FlightDAO();   	
    			Plane p = new Plane(capacity , model,manufacturer, yearOfManufacture);
		    	Flight temp= new Flight(Integer.toString(counterFlight.incrementAndGet()),price, from , to,departureTime,arrivalTime ,capacity , description, p  );
		    	resrv.store(temp);
		    	return temp;
    		} catch (RuntimeException e){ throw e;}
    }    
    
    @RequestMapping(params = "xml",value="/flight/{id}", method = RequestMethod.GET, produces= "application/xml" )
    public Flight getFlightXML(@PathVariable String id, @RequestParam boolean xml ) {
    	try{	System.out.println("Im inside");
    			if (xml){
	        		FlightDAO pr = new FlightDAO();
	        		System.out.println("ID "+id);
	        	  	return pr.findById(id);
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
    @RequestMapping(params = "json", value="/flight/{id}", method = RequestMethod.GET, produces= "application/json" )
    public Flight getFlightJSON(@PathVariable String id, @RequestParam boolean  json ) {
    	try{	System.out.println("Im inside");
    			if (json){
	        		FlightDAO pr = new FlightDAO();
	        		System.out.println("ID "+id);
	        	  	return pr.findById(id);
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
}
