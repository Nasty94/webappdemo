package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;

import org.springframework.stereotype.Repository;

import com.demo.h2.DBUtils;
import com.demo.model.EmployeeVO;
 
@Repository
public class EmployeeDAOImpl  extends HttpServlet implements EmployeeDAO{
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
   
	 public EmployeeDAOImpl() {
	        super();
	    }

    public List<EmployeeVO> getAllEmployees() throws SQLException 
   
    {
    	logger.debug("getAllEmployees() is executed!");
        String query =
        		"SELECT * FROM EMPLOYEE ORDER BY LASTNAME";
    
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
	     Statement s=connection.createStatement();
	     
	     String create = "create table employee(id int auto_increment not null, securitycode int not null primary key, firstname varchar(255) not null, lastname varchar(255) not null, phone int not null, country varchar(255) , address varchar(255) not null, boss int not null)";
	 	 TableCheck(connection, "EMPLOYEE", create);

         String create2 = "create table boss(id int auto_increment not null, securitycode int not null primary key, firstname varchar(255) not null, lastname varchar(255) not null, phone int not null, country varchar(255) , address varchar(255) not null)";
	 	 TableCheck(connection, "BOSS", create2);
		 
            
	     ResultSet rs = s.executeQuery(query);
	     List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
	     
	     while(rs.next()) {
	    	 int id = rs.getInt("ID");
	    	 int SecurityCode = rs.getInt("SECURITYCODE");
	    	 String FirstName = rs.getString("FIRSTNAME");
	    	 String LastName = rs.getString("LASTNAME");
	    	 int Phone = rs.getInt("PHONE");
	    	 String Country = rs.getString("COUNTRY");
	    	 String Address = rs.getString("ADDRESS");
	    	 int boss = rs.getInt("BOSS");
	    	 
	    	 EmployeeVO vo1 = new EmployeeVO();
	    	 vo1.setId(id);
	         vo1.setSecuritycode(SecurityCode);
	         vo1.setFirstName(FirstName);
	         vo1.setLastName(LastName);
	         vo1.setPhone(Phone);
	         vo1.setCountry(Country);
	         vo1.setAddress(Address);
	         vo1.setBoss(boss);
	         employees.add(vo1);
	     }
    	
	    

         
        return employees;
    }
    
    public EmployeeVO getEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("getEmployee() is executed!");
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
 		} catch (SQLException e1) {
 			logger.debug("getEmployee Connection Exception() is executed!" + e1.getMessage());
 			e1.printStackTrace();
 		}
  
         String securitycode = (String) request.getParameter("securitycode");
  
         EmployeeVO employee = null;
  
         String errorString = null;
  
         try {
             employee = DBUtils.findEmployee(conn, Integer.parseInt(securitycode));
         } catch (SQLException e) {
             e.printStackTrace();
             errorString = e.getMessage();
             logger.debug("getEmployee() DBUtils.findEmployee Exception is executed!" + errorString);
         }
  
         if (errorString != null && employee == null) {
             try {
				response.sendRedirect(request.getServletPath() + "/employee");
				request.getSession().setAttribute("messageEmployeeEdit", "cannot find such employee in database");
			} catch (IOException e) {
				logger.debug("getEmployee() Send Request Exception is executed!" +e.getMessage());
				e.printStackTrace();
			}
             return null;
         }
  
         // Store errorString in request attribute, before forward to views.
         request.setAttribute("errorString", errorString);
         request.setAttribute("employee", employee);
         ServletContext context = request.getSession().getServletContext();

         RequestDispatcher dispatcher = context
                 .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
         dispatcher.forward(request, response);
         
         return employee;
	         
	        
	     }
    
    public EmployeeVO updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("updateEmployee() is executed!");
    	 Connection conn = null;
  		try {
  			conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
  		} catch (SQLException e1) {
  			logger.debug("updateEmployee()Connection Exception is executed! " + e1.getMessage());
  			e1.printStackTrace();
  		}
  		int id = Integer.parseInt((String) request.getParameter("id"));
        int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
        String FirstName = (String) request.getParameter("firstname");
        String LastName = (String) request.getParameter("lastname");
        int Phone = Integer.parseInt((String) request.getParameter("phone"));
        String Country = (String) request.getParameter("country");
        String Address = (String) request.getParameter("address");
        int boss = Integer.parseInt((String) request.getParameter("boss"));

        EmployeeVO vo1 = new EmployeeVO();
        vo1.setId(id);
        vo1.setSecuritycode(securitycode);
        vo1.setFirstName(FirstName);
        vo1.setLastName(LastName);
        vo1.setPhone(Phone);
        vo1.setCountry(Country);
        vo1.setAddress(Address);
        vo1.setBoss(boss);
        String errorString = null;
        
     
        
     
  
        try {
            DBUtils.updateEmployee(conn, vo1);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            logger.debug("updateEmployee() DBUtils.updateEmployee Exception is executed! " + errorString);
        } catch (ParseException e) {
 		// TODO Auto-generated catch block
        	logger.debug("updateEmployee() ParseException is executed! " + e.getMessage());
 		 return null;
 	}
        
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("NewEmployee", vo1);
  
  
        // If error, forward to Edit page.
        if (errorString != null) {
        	ServletContext context = request.getSession().getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
         
        // If everything nice.
        // Redirect to the product listing page.            
        else {
            response.sendRedirect(request.getContextPath() + "/employee");
            request.getSession().setAttribute("messageEmployeeEdit", "employee: " + vo1.getSecuritycode() + " has been updated.");
        }
        
        return vo1;
  
	        
	     }

	@Override
	public EmployeeVO insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		logger.debug("insertEmployee() is executed!");
	   	 Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
			} catch (SQLException e1) {
				logger.debug("InsertEmployee Connection Exception() is executed!" + e1.getMessage());
				e1.printStackTrace();
			}
			
		
		// int id = Integer.parseInt((String) request.getParameter("id"));
		 int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
	     String firstname = (String) request.getParameter("firstname");
	     String lastname = (String) request.getParameter("lastname");
	     int phone = Integer.parseInt((String)request.getParameter("phone"));
	     String country = (String) request.getParameter("country");
	     String address = (String) request.getParameter("address");
	     int boss = Integer.parseInt((String)request.getParameter("boss"));
	     String errorString = null;

	     EmployeeVO vo1 = new EmployeeVO();
	    // vo1.setId(id);
	     vo1.setSecuritycode(securitycode);
	     vo1.setFirstName(firstname); 
	     vo1.setLastName(lastname);
	     vo1.setPhone(phone);
	     vo1.setCountry(country);
	     vo1.setAddress(address);
	     vo1.setBoss(boss);

	     
	     try {
         
         Statement validate1 = conn.createStatement();
         Statement validate2 = conn.createStatement();
         ResultSet vresult1 = validate1.executeQuery("SELECT * FROM EMPLOYEE WHERE SECURITYCODE="+securitycode);
         ResultSet vresult2 = validate2.executeQuery("SELECT * FROM BOSS WHERE ID="+boss);
         if (vresult1.next()){
        	 errorString = "Sorry! This securitycode is already assigned to another employee " + vo1.getFirstName() + " " + vo1.getLastName();
          	  //System.out.println(errorString);
          	  
          	  logger.debug(errorString);
          	  
          	// Set the MIME type for the response message
                response.setContentType("text/html");
  
                response.sendRedirect(request.getContextPath() + "/employee");
                request.getSession().setAttribute("messageEmployeeAdd", errorString);
          	  
            }
         else if(!vresult2.next()){
        	 errorString = "Sorry! This boss id does not exist in Boss table: "+boss;
         	  //System.out.println(errorString);
         	  
         	  logger.debug(errorString);
         	  
         	// Set the MIME type for the response message
               response.setContentType("text/html");
 
               response.sendRedirect(request.getContextPath() + "/employee");
               request.getSession().setAttribute("messageEmployeeAdd", errorString);
         }
         
         else{

	     try {
	         DBUtils.insertEmployee(conn, vo1);
	     } catch (SQLException e) {
	         e.printStackTrace();
	         errorString = e.getMessage();
	         logger.debug("insertEmployee() DBUtils.insertEmployee Exception is executed! " + errorString);
	     }
         }
	     } catch(SQLException e) {
	         e.printStackTrace();
	         errorString = e.getMessage();
	         logger.debug("insertEmployee() DBUtils.insertEmployee Exception is executed! " + errorString);
	         response.sendRedirect(request.getContextPath() + "/employee");
	         request.getSession().setAttribute("messageEmployeeAdd", errorString);
	     }
	      
	     // Store infomation to request attribute, before forward to views.
	     request.setAttribute("errorString", errorString);
	     request.setAttribute("NewEmployee", vo1);

	     // If error, forward to Edit page.
	     if (errorString != null) {
	   	  ServletContext context = request.getSession().getServletContext();
	         RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/index.jsp");
	         dispatcher.forward(request, response);
	         request.getSession().setAttribute("messageEmployeeAdd", errorString);
	     }

	     // If everything nice.
	     // Redirect to the product listing page.            
	     else {
	         response.sendRedirect(request.getContextPath() + "/employee");
	         request.getSession().setAttribute("messageEmployeeAdd", "employee: " + vo1.getFirstName() + " " + vo1.getLastName() + " added.");
	     }
	     return vo1;
		}
	
	private static  void TableCheck (Connection conn, String TableName, String sql) throws SQLException {
		 DatabaseMetaData dbm = conn.getMetaData();
		// check if "employee" table is there
		ResultSet tables = dbm.getTables(null, null, TableName, null);
		if (!tables.next()) {
			PreparedStatement create = conn.prepareStatement(sql);
		    create.executeUpdate();
		}
		
		
	 }
	 
    	

}