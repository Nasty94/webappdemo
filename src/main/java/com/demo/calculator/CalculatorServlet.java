package com.demo.calculator;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.demo.model.CalculatorVO;

//http://codesstore.blogspot.com.ee/2011/12/json-with-jquery-jsp-servlets.html

public class CalculatorServlet extends HttpServlet
{ 
    private static final long serialVersionUID = 1L;
	
	public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		
		
		response.setContentType("text/html"); // informing the client that which format of data/response will be send
		
		int a1= Integer.parseInt(request.getParameter("n1")); 
		// using "getParameter()" to retrieve data entered by user in "n1" field
		
		int a2= Integer.parseInt(request.getParameter("n2")); 
		
		
		
		if (request.getParameter("r1")!=null) // checking if 1st radio button checked or not?
		{

			int sum = a1+a2;
		    request.setAttribute("result", sum); // It'll be available as ${sum}.

		}
		else if(request.getParameter("r2")!=null) // checking if 2nd radio button checked or not?
		{
			
			int sub = a1 - a2;
		    request.setAttribute("result", sub); // It'll be available as ${sum}.

		}
		else if(request.getParameter("r3")!=null) //checking if 3rd radio button checked or not?
		{
			
			int mult = a1*a2;
		    request.setAttribute("result", mult); // It'll be available as ${sum}.

		}
		else
		{
			int dev = a1/a2;
		    request.setAttribute("result", dev); // It'll be available as ${sum}.

		}		
		
	
	}
	
	public CalculatorVO calculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		
		doGet(request, response);
		CalculatorVO vo1 =  new CalculatorVO();
		
		response.setContentType("text/html"); // informing the client that which format of data/response will be send
		
		
		//All data entered into a "TEXT" field of form, is stored as "STRING"
		//Hence if we enter "1" in a "TEXT" field of form, it will be stored as "ONE"
		//To convert it to "INT", we use a predefined function called= "parseInt()"
		
		int a1= Integer.parseInt(request.getParameter("n1")); 
		// using "getParameter()" to retrieve data entered by user in "n1" field
		
		int a2= Integer.parseInt(request.getParameter("n2")); 
		PrintWriter out= response.getWriter(); 
		vo1.setN1(a1);
		vo1.setN2(a2);
		
		if (request.getParameter("r1")!=null) // checking if 1st radio button checked or not?
		{

			vo1.setOp("add");
			out.println("<h3> Addition= </h3>"+(a1+a2));
			int sum = a1+a2;
		    request.setAttribute("result", sum); // It'll be available as ${sum}.


			
		}
		else if(request.getParameter("r2")!=null) // checking if 2nd radio button checked or not?
		{
			
			vo1.setOp("sub");
			
		}
		else if(request.getParameter("r3")!=null) //checking if 3rd radio button checked or not?
		{
			vo1.setOp("mult");
			
		}
		else
		{
			vo1.setOp("dev");
			
		}		
		
		return vo1;
	}
	
/*
        public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
        {
				JSONObject json = new JSONObject();

                display();
                PrintWriter out = response.getWriter();

                String n1 = request.getParameter("no1");
                String n2 = request.getParameter("no2");
                if (request.getParameter("r1")!=null) // checking if 1st radio button checked or not?
		{
			json.put("<h3> Addition= </h3>",new Integer((a1+a2)));
			out.println(json);
		}
		else if(request.getParameter("r2")!=null) // checking if 2nd radio button checked or not?
		{
			json.put("<h3> Subtraction= </h3>",new Integer((a1-a2)));
			out.println(json);
		}
		else if(request.getParameter("r3")!=null) //checking if 3rd radio button checked or not?
		{
			json.put("<h3> Multiply= </h3>",new Integer((a1*a2)));
			out.println(json);
		}
		else
		{
			
			json.put("<h3> Divide= </h3>",new Integer((a1/a2)));
			out.println(json);
		}	
        }*/
		
		//https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
		//http://crunchify.com/create-very-simple-jersey-rest-service-and-send-json-data-from-java-client/
        // String loudScreaming = json.getJSONObject("LabelData").getString("slogan");
        //http://stackoverflow.com/questions/5338943/read-json-string-in-servlet
}