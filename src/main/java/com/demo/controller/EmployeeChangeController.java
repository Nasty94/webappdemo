package com.demo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.demo.service.EmployeeChange;
 
@Controller
@RequestMapping("/editEmployee")
public class EmployeeChangeController extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    EmployeeChange manager;
   
 
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String getEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
        model.addAttribute("employee", manager.getEmployee(request,  response));
        return "index";
    }
    
 
}