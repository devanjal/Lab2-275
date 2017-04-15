package edu.sjsu.cmpe275.lab2;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {

   // private static final String template = "Hello, %s!";
	private final AtomicInteger counter = new AtomicInteger();
	
    @RequestMapping(value="/passenger", method=RequestMethod.POST)
    public Passenger posting(@RequestParam String firstname, @RequestParam String lastname, @RequestParam int age,
    		@RequestParam String gender,@RequestParam String phone) {
    	try{
		    	PassengerRepository pr = new PassengerRepository();   	
		    	Passenger temp= new Passenger(Integer.toString(counter.incrementAndGet()), firstname, lastname,age,gender,phone);
		    	pr.store(temp);
		    	return temp;
    		} catch (RuntimeException e){ throw e;}
    }
    
    @RequestMapping(params = "xml",value="/passenger/{id}", method = RequestMethod.GET, produces= "application/xml" )
    public Passenger gettingXML(@PathVariable String id, @RequestParam boolean xml ) {
    	try{	System.out.println("Im inside");
    			if (xml){
	        		PassengerRepository pr = new PassengerRepository();
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
	        		PassengerRepository pr = new PassengerRepository();
	        		System.out.println("ID "+id);
	        	  	return pr.findById(id);
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
    @RequestMapping(value="/passenger/{id}", method = RequestMethod.DELETE)
    public String deleting(@PathVariable String id) {
    	try{	
    			
	        	PassengerRepository pr = new PassengerRepository();
	        	System.out.println("ID "+id);
	        	pr.delete(id);
	        	return "success";
    		  				
        	} catch (RuntimeException e){ throw e;}
    }
}
