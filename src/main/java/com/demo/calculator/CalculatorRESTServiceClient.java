package com.demo.calculator;
 
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.helloworld.web.WelcomeController;
import com.demo.model.CalculatorVO;

 
public class CalculatorRESTServiceClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(CalculatorRESTServiceClient.class);
	
	public CalculatorRESTServiceClient() {
        super();
        // TODO Auto-generated constructor stub
    }
	


	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
		logger.debug("doPost mehtod");
		String string = "";
		try {
			
			String userhome = System.getProperty("user.home");
			// System.out.println(userhome);
		    //System.out.println(getClass().getResourceAsStream("calc.txt"));
	    	//String dir = CalculatorRESTServiceClient.class.getClassLoader().getResource("calc.txt").getPath();
			InputStream calculateInputStream = new FileInputStream(userhome + "/Documents/GitHub/webapp/src/main/java/com/demo/calculator/calc.txt");
			logger.debug("fot calculation file...processing...");
			InputStreamReader calculateReader = new InputStreamReader(calculateInputStream);
			BufferedReader br = new BufferedReader(calculateReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line;
				//System.out.println(string);
				
			}
			
			JSONObject jsonObject = new JSONObject(string);
			JSONObject json = new JSONObject();
			JSONObject json1 = new JSONObject();
		
            //PrintWriter out = response.getWriter();
			logger.debug("printing jsonObject");
            System.out.println(jsonObject);
            json1.put("calculation",string);
			request.setAttribute("calculation", json1); 
			String op = jsonObject.getJSONObject("calculate").getString("op");
			int a1 = jsonObject.getJSONObject("calculate").getInt("no1");
			int a2 = jsonObject.getJSONObject("calculate").getInt("no2");

			if (op.equals("add")) { 
			   json.put("resultPost",new Integer((a1+a2)));
			   request.setAttribute("resultPost", json); 
			}
			else if (op.equals("sub")) { 
			   json.put("resultPost",new Integer((a1-a2)));
			   request.setAttribute("resultPost", json); 
			}
			else if (op.equals("mul")) { 
				   json.put("resultPost",new Integer((a1*a2)));
				   request.setAttribute("resultPost", json); 
				}
			else if (op.equals("div")){
				json.put("resultPost",new Integer((a1/a2)));
				request.setAttribute("resultPost", json); 
			}
			else {
				json.put("resultPost","not supported operation");
				request.setAttribute("resultPost", json); 
			}
			
			/*
			JSONObject jsonObject = new JSONObject(string);
			    //System.out.println(jsonObject);
			
			display();
			
            PrintWriter out = response.getWriter();
            out.println(jsonObject);
			//http://stackoverflow.com/questions/18393210/how-to-write-regex-to-extract-pattern-from-string
		//	String[] ops = jsonObject.getJSONObject("calculate") //jsonObject.getJSONObject("calculate").getString("op");
			
			for(String[] s : ops) {
			    String op = s[2];
		    	int a1 = Integer.parseInt(jsonObject.getJSONObject("calculate").getString("no1"));
		    	int a2 = Integer.parseInt(jsonObject.getJSONObject("calculate").getString("no2"));
			
		     	if (op.equals("add")) { 
				   json.put("<h3> Addition= </h3>",new Integer((a1+a2)));
			       out.println(json);
				}
				else if (op.equals("sub")) { 
				   json.put("<h3> Substraction= </h3>",new Integer((a1-a2)));
			       out.println(json);
				}
			}    */
			
			try {
				URL url = new URL("http://localhost:8080/webapp/api/calculateRESTServiceClient");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while (in.readLine() != null) {
				}
				System.out.println("\ncalculate REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling calculate REST Service");
				System.out.println(e);
			}
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CalculatorVO calculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
		
		doPost(request, response);
		CalculatorVO vo1 =  new CalculatorVO();
		
		response.setContentType("text/html"); // informing the client that which format of data/response will be send
		
		
		String string = "";
		try {
 
			String userhome = System.getProperty("user.home");
			// System.out.println(userhome);
		    //System.out.println(getClass().getResourceAsStream("calc.txt"));
	    	//String dir = CalculatorRESTServiceClient.class.getClassLoader().getResource("calc.txt").getPath();
			InputStream calculateInputStream = new FileInputStream(userhome + "/Documents/GitHub/webapp/src/main/java/com/demo/calculator/calc.txt");
			InputStreamReader calculateReader = new InputStreamReader(calculateInputStream);
			BufferedReader br = new BufferedReader(calculateReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
				JSONObject jsonObject = new JSONObject(string);
				
			
				String op = jsonObject.getJSONObject("calculate").getString("op");
				int a1 = Integer.parseInt(jsonObject.getJSONObject("calculate").getString("no1"));
				int a2 = Integer.parseInt(jsonObject.getJSONObject("calculate").getString("no2"));

				vo1.setN1(a1);
				vo1.setN2(a2);
				vo1.setOp(op);
			}
			br.close();
		}catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return vo1;
	}
	
}