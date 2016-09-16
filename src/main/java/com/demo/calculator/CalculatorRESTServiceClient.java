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