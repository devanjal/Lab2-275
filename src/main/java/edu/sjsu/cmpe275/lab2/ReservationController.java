package edu.sjsu.cmpe275.lab2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.codehaus.jackson.map.ObjectMapper;

@RestController
public class ReservationController {
	private final AtomicInteger counterReserv = new AtomicInteger();
	
    @RequestMapping(params = "json",value="/reservation/{id}", method = RequestMethod.GET, produces= "application/json" )
    public String get(@PathVariable String id, @RequestParam boolean json ) throws IOException {
    	try{	System.out.println("Im inside");
    				if(json){
    				ReservationDAO pr = new ReservationDAO();
	        		System.out.println("ID "+id);
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        	  	return "\"reservation\":"+ jso;}
    				else return null;
    			
    	   	} catch (IOException e){ throw e;}
    }
    @RequestMapping(params = "xml",value="/reservation/{id}", method = RequestMethod.GET, produces= "application/xml" )
    public String getXML(@PathVariable String id , @RequestParam boolean xml) throws IOException {
    	try{	System.out.println("Im inside");
    			if(xml){
    				ReservationDAO pr = new ReservationDAO();
	        		System.out.println("ID "+id);
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        	  	return "\"reservation\":"+ jso;}
	        	  	else return null;
    	   	} catch (IOException e){ throw e;}
    }
    @RequestMapping(value="/reservation", method=RequestMethod.POST)
    public Reservation posting(@RequestParam String passengerId, @RequestParam String flightLists) {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab2");
    	EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = manager.getTransaction();	
    	try{
	        		
        			ReservationDAO resrv = new ReservationDAO();   	
    		    	PassengerDAO psgr = new PassengerDAO(); 
    		    	//System.out.println("fightLSIT::"+flightLists);
    		    	List<String> items = Arrays.asList(flightLists.split("\\s*,\\s*"));
    		    	List<Flight> flights = new ArrayList<Flight>();
    		    	FlightDAO fd= new FlightDAO();
    		    	int sum_amount=0;
    		    	if (items== null && flightLists!= null){items.add(flightLists);}
    		    	tx.begin();
    		    	for (String x:items){ 
    		    		Flight f = new Flight();
    		    		f = fd.findById(x);
    		    		if (f.getSeatsLeft() >0){
    		    			f.setSeatsLeft(f.getSeatsLeft()-1);
    		    			Passenger p = psgr.findById(passengerId);
    		    			List<Passenger> pas = new ArrayList<>();
    		    			pas.add(p);
    		    			System.out.println("Printing created passenger list");
    		    			System.out.println(pas);
    		    			//f.setPassengers(pas);
    		    			fd.store(f);
    			    		flights.add(f);
    			    		sum_amount+= fd.findById(x).getPrice();
    		    		}
    		    		else{ throw new RuntimeException();} 
    		    	
    		    	}
 
    		    	System.out.println(flights);
    		    	Reservation temp= new Reservation(Integer.toString(counterReserv.incrementAndGet()), psgr.findById(passengerId), flights);
    		    	temp.setPrice(sum_amount);
    		    	resrv.store(temp);
       		    	tx.commit();
    		    	return temp;
        		} catch (RuntimeException e){ tx.rollback();
        				throw e;}
        }

    }

