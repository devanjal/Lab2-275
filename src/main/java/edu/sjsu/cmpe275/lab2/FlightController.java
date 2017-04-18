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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.*;

@RestController
public class FlightController {
	
	private final AtomicInteger counterFlight = new AtomicInteger();

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
    public String getFlightXML(@PathVariable String id, @RequestParam boolean xml ) throws IOException, JSONException {
    	try{	System.out.println("Im inside");
    			if (xml){
	        		FlightDAO pr = new FlightDAO();
	        		System.out.println("ID "+id);
	        		
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        		List<Passenger> passengers = new ArrayList<>();
	        		passengers = pr.findFlightPassengers(id);
	        		String pasgr="";
	        		if (passengers!= null)
	        			pasgr = ",\"passengers\": { \"passenger\":"+mapperObj.writeValueAsString(passengers)+"}}";
	        		
	        		JSONObject json = new JSONObject("{\"flight\":"+jso+pasgr);
	        		return XML.toString(json);
	        			        	  	
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
    @RequestMapping(params = "json", value="/flight/{id}", method = RequestMethod.GET, produces= "application/json" )
    public String getFlightJSON(@PathVariable String id, @RequestParam boolean  json )throws IOException {
    	try{	System.out.println("Im inside");
    			if (json){
	        		FlightDAO pr = new FlightDAO();
	        		System.out.println("ID "+id);
	        		
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        		List<Passenger> passengers = new ArrayList<>();
	        		passengers = pr.findFlightPassengers(id);
	        		String pasgr="";
	        		if (passengers!= null)
	        			pasgr = ",\"passengers\": { \"passenger\":"+mapperObj.writeValueAsString(passengers)+"}}";
	        		        	  	
	        		return "{\"flight\":"+jso+pasgr ;
	        	  	
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ throw e;}

    }
	
	
}
