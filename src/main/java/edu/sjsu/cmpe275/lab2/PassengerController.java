package edu.sjsu.cmpe275.lab2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
public class PassengerController {

   // private static final String template = "Hello, %s!";
	private final AtomicInteger counter = new AtomicInteger();
	

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
    public ResponseEntity<Object> gettingXML(@PathVariable String id, @RequestParam boolean xml ) throws JsonGenerationException, JsonMappingException, IOException, JSONException  {
    	try{	System.out.println("Im inside");
    			if (xml){
	        		PassengerDAO pr = new PassengerDAO();
	        		System.out.println("ID "+id);
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        		jso =jso.substring(1, jso.length()-2);
	        		System.out.println("JSO SUB");
	        		System.out.println(jso);
	        		String reserv = mapperObj.writeValueAsString(pr.findPassengerReservations(id));
	        		System.out.println(reserv);
	        		JSONObject json = new JSONObject("\"passenger\":{"+jso+",\"reservations\": {\"reservation\": ["+reserv+"}}");
	        		return ResponseEntity.status(HttpStatus.OK).body(XML.toString(json));
    			}
    			else {return null;}
    	   	} catch (RuntimeException e){ 
    	   		String s = "{\"BadRequest\": {\"code\": \" 404 \",\"msg\": \" Sorry, the requested passenger with id "+id+" does not exist\""+
    	   			"}}";
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(s);
    	   	}

    }
    @RequestMapping(params = "json", value="/passenger/{id}", method = RequestMethod.GET, produces= "application/json" )
    public ResponseEntity<Object> gettingJSON(@PathVariable String id, @RequestParam boolean  json ) throws JSONException, JsonGenerationException, JsonMappingException, IOException {
    	try{	System.out.println("Im inside");
    			if (json){
	        		PassengerDAO pr = new PassengerDAO();
	        		System.out.println("ID "+id);
	        		ObjectMapper mapperObj = new ObjectMapper();
	        		String jso = mapperObj.writeValueAsString(pr.findById(id));
	        		jso =jso.substring(1, jso.length()-2);
	        		String reserv = mapperObj.writeValueAsString(pr.findPassengerReservations(id));
	        		return ResponseEntity.status(HttpStatus.OK).body(new JSONObject("\"passenger\":{"+jso+",\"reservations\": {\"reservation\": ["+reserv+"}}"));
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

    @RequestMapping(value="/passenger/{id}", method = RequestMethod.DELETE)
    public String deleting(@PathVariable String id) {
    	try{	
    			
	        	PassengerDAO pr = new PassengerDAO();
	        	System.out.println("ID "+id);
	        	pr.delete(id);
	        	return "success";
    		  				
        	} catch (RuntimeException e){ throw e;}
    }
    
}
