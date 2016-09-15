//http://calculate.com/create-very-simple-jersey-rest-service-and-send-json-data-from-java-client/

package com.demo.calculator;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/")
public class CalculatorRESTService {
	@POST
	@Path("/calculate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response calculateREST(InputStream incomingData) {
		StringBuilder calculateBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				calculateBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + calculateBuilder.toString());
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(calculateBuilder.toString()).build();
	}
 
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "calculateRESTService Successfully started..";
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
 
}