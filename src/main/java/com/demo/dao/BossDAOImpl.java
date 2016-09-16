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
import com.demo.model.BossVO;
 
@Repository
public class BossDAOImpl  extends HttpServlet implements BossDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(BossDAOImpl.class);
   
	 public BossDAOImpl() {
	        super();
	    }
	 
	 
    public List<BossVO> getAllBosses() throws SQLException 
   
    {
    	logger.debug("getAllBosses() is executed!");
        String query =
        		"SELECT * FROM BOSS ORDER BY LASTNAME";
    
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
	     Statement s=connection.createStatement();
	     
	     String create = "create table boss(id int auto_increment not null, securitycode int not null primary key, firstname varchar(255) not null, lastname varchar(255) not null, phone int not null, country varchar(255) , address varchar(255) not null)";
	 	 TableCheck(connection, "BOSS", create);
	 	 
		String create2 = "create table employee(id int auto_increment not null, securitycode int not null primary key, firstname varchar(255) not null, lastname varchar(255) not null, phone int not null, country varchar(255) , address varchar(255) not null, boss int not null)";
	 	TableCheck(connection, "EMPLOYEE", create2);
         
       

	     ResultSet rs = s.executeQuery(query);
	     List<BossVO> bosses = new ArrayList<BossVO>();
	     
	     while(rs.next()) {
		     int id = rs.getInt("ID");
	    	 int SecurityCode = rs.getInt("SECURITYCODE");
	    	 String FirstName = rs.getString("FIRSTNAME");
	    	 String LastName = rs.getString("LASTNAME");
	    	 int Phone = rs.getInt("PHONE");
	    	 String Country = rs.getString("COUNTRY");
	    	 String Address = rs.getString("ADDRESS");
	    	 
	    	 BossVO vo1 = new BossVO();
			 vo1.setId(id);
	         vo1.setSecuritycode(SecurityCode);
	         vo1.setFirstName(FirstName);
	         vo1.setLastName(LastName);
	         vo1.setPhone(Phone);
	         vo1.setCountry(Country);
	         vo1.setAddress(Address);
	         bosses.add(vo1);
	     }
    	

         
        return bosses;
    }
    
    public BossVO getBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("getBoss() is executed!");
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
 		} catch (SQLException e1) {
 			logger.debug("getBoss Connection Exception() is executed!" + e1.getMessage());
 			e1.printStackTrace();
 		}
  
         String securitycode = (String) request.getParameter("securitycode");
  
         BossVO boss = null;
  
         String errorString = null;
  
         try {
             boss = DBUtils.findBoss(conn, Integer.parseInt(securitycode));
         } catch (SQLException e) {
             e.printStackTrace();
             errorString = e.getMessage();
             logger.debug("getBoss() DBUtils.findBoss Exception is executed!" + errorString);
         }
  
         if (errorString != null && boss == null) {
             try {
				response.sendRedirect(request.getServletPath() + "/boss");
				logger.debug("getBoss() sth went wrong / found no boss");
				//request.setAttribute("messageEmployeeEdit", "cannot find such boss in database");
			} catch (IOException e) {
				logger.debug("getBoss() Send Request Exception is executed!" +e.getMessage());
				e.printStackTrace();
			}
             return null;
         }
  
         // Store errorString in request attribute, before forward to views.
         request.setAttribute("errorString", errorString);
         request.setAttribute("boss", boss);
         ServletContext context = request.getSession().getServletContext();

         RequestDispatcher dispatcher = context
                 .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
         dispatcher.forward(request, response);
         
         return boss;
	         
	        
	     }
    
    public BossVO updateBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("updateBoss() is executed!");
    	 Connection conn = null;
  		try {
  			conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
  		} catch (SQLException e1) {
  			logger.debug("updateBoss()Connection Exception is executed! " + e1.getMessage());
  			e1.printStackTrace();
  		}
    	 
        int id = Integer.parseInt((String) request.getParameter("id"));
		int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
        String FirstName = (String) request.getParameter("firstname");
        String LastName = (String) request.getParameter("lastname");
        int Phone = Integer.parseInt((String) request.getParameter("phone"));
        String Country = (String) request.getParameter("country");
        String Address = (String) request.getParameter("address");

        BossVO vo1 = new BossVO();
		vo1.setId(id);
        vo1.setSecuritycode(securitycode);
        vo1.setFirstName(FirstName);
        vo1.setLastName(LastName);
        vo1.setPhone(Phone);
        vo1.setCountry(Country);
        vo1.setAddress(Address);
  
        String errorString = null;
        
        
       // if(countries.contains(vo1.getCountry())) {
  
        try {
            DBUtils.updateBoss(conn, vo1);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            logger.debug("updateBoss() DBUtils.updateBoss Exception is executed! " + errorString);
        } catch (ParseException e) {
 		// TODO Auto-generated catch block
        	logger.debug("updateBoss() ParseException is executed! " + e.getMessage());
 		 return null;
 	}
       // }
  
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("NewBoss", vo1);
  
  
        // If error, forward to Edit page.
        if (errorString != null) {
        	ServletContext context = request.getSession().getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
         
        // If everything nice.
        // Redirect to the product listing page.            
        else {
            response.sendRedirect(request.getContextPath() + "/boss");
            request.getSession().setAttribute("messageBossEdit", "boss: " + vo1.getSecuritycode() + " has been updated.");
        }
        
        return vo1;
  
	        
	     }

	@Override
	public BossVO insertBoss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		logger.debug("insertBoss() is executed!");
	   	 Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:h2:~/webapp", "sa", "");
			} catch (SQLException e1) {
				logger.debug("InsertBoss Connection Exception() is executed!" + e1.getMessage());
				e1.printStackTrace();
			}
		
		// int id = Integer.parseInt((String) request.getParameter("id"));
		 int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
	     String firstname = (String) request.getParameter("firstname");
	     String lastname = (String) request.getParameter("lastname");
	     int phone = Integer.parseInt((String)request.getParameter("phone"));
	     String country = (String) request.getParameter("country");
	     String address = (String) request.getParameter("address");

	     String errorString = null;

	     BossVO vo1 = new BossVO();
		// vo1.setId(id);
	     vo1.setSecuritycode(securitycode);
	     vo1.setFirstName(firstname);
	     vo1.setLastName(lastname);
	     vo1.setPhone(phone);
	     vo1.setCountry(country);
	     vo1.setAddress(address);
	     
	     // Product ID is the string literal [a-zA-Z_0-9]
	     // with at least 1 character
	     //String regex = "\\w+";
	     
	     
        
         Statement validate1 = conn.createStatement();
        
         ResultSet vresult1 = validate1.executeQuery("SELECT * FROM BOSS WHERE SECURITYCODE="+securitycode);
         if (vresult1.next()){
        	 errorString = "Sorry! This securitycode is already assigned to another boss " + vo1.getFirstName() + " " + vo1.getLastName();
          	  //System.out.println(errorString);
          	  
          	  logger.debug(errorString);
          	  
          	// Set the MIME type for the response message
                response.setContentType("text/html");

                response.sendRedirect(request.getContextPath() + "/boss");
                request.getSession().setAttribute("messageBossAdd", errorString);
          	  
            }
         else {

	     try {
	         DBUtils.insertBoss(conn, vo1);
	     } catch (SQLException e) {
	         e.printStackTrace();
	         errorString = e.getMessage();
	         logger.debug("insertBoss() DBUtils.insertBoss Exception is executed! " + errorString);
	     }
         }
	      
	     // Store infomation to request attribute, before forward to views.
	     request.setAttribute("errorString", errorString);
	     request.setAttribute("NewBoss", vo1);

	     // If error, forward to Edit page.
	     if (errorString != null) {
	   	  ServletContext context = request.getSession().getServletContext();
	         RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/index.jsp");
	         dispatcher.forward(request, response);
	     }

	     // If everything nice.
	     // Redirect to the product listing page.            
	     else {
	         response.sendRedirect(request.getContextPath() + "/boss");
	         request.getSession().setAttribute("messageBossAdd", "boss: " + vo1.getFirstName() + " " + vo1.getLastName() + " added.");
	     }
	     return vo1;
		}
	
	private static  void TableCheck (Connection conn, String TableName, String sql) throws SQLException {
		 DatabaseMetaData dbm = conn.getMetaData();
		// check if "Boss" table is there
		ResultSet tables = dbm.getTables(null, null, TableName, null);
		if (!tables.next()) {
			PreparedStatement create = conn.prepareStatement(sql);
		    create.executeUpdate();
		}
		
		
	 }
	 
    	

}