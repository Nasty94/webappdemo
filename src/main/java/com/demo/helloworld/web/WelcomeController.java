package com.demo.helloworld.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.calculator.CalculatorRESTServiceClient;
import com.demo.calculator.CalculatorServlet;
import com.demo.helloworld.service.HelloWorldService;
import com.demo.service.EmployeeChange;
import com.demo.service.EmployeeCreater;
import com.demo.service.EmployeeUpdate;
import com.demo.service.EmployeeManager;
import com.demo.service.EmployeeManagerImpl;
import com.demo.service.BossChange;
import com.demo.service.BossCreater;
import com.demo.service.BossUpdate;
import com.demo.service.BossManager;
import com.demo.service.BossManagerImpl;

@Controller
public class WelcomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
		
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");

		model.put("title", helloWorldService.getTitle(""));
		model.put("msg", helloWorldService.getDesc());
		
		return "index";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());
		
		return model;

	}
	
	    @Resource(name="employeeManagerImpl")
	    EmployeeManager manager;
	 
	    @RequestMapping(value = "/employee",method = RequestMethod.GET)
	    public String getAllEmployees(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("client() is executed!");

	        model.addAttribute("employees", manager.getAllEmployees());
	        return "index";
	    }
	    
	    @Resource(name="employeeChange")
	    EmployeeChange change;
	    
	    @RequestMapping(value = "editEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String getEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("change() is executed!");

	        model.addAttribute("employee", change.getEmployee(request, response));
	        return "index";
	    }
	    
	    @Resource(name="employeeUpdate")
	    EmployeeUpdate update;
	    
	    @RequestMapping(value = "doEditEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String updateEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("update() is executed!");

	        model.addAttribute("NewEmployee", update.updateEmployee(request, response));
	        return "index";
	    }
	 

	    
	  
	    
	    @Resource(name="employeeCreaterImpl")
	    EmployeeCreater createrE;
	    
	    @RequestMapping(value = "doCreateEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String insertEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("employeeInsert() is executed!");

	        model.addAttribute("NewEmployee", createrE.insertEmployee(request, response));
	        return "index";
	    }
		
	//##################### BOSS ##########################
	
	 @Resource(name="bossManagerImpl")
	    BossManager managerB;
	 
	    @RequestMapping(value = "/boss",method = RequestMethod.GET)
	    public String getAllBosses(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("client() is executed!");

	        model.addAttribute("bosses", managerB.getAllBosses());
	        return "index";
	    }
	    
	    @Resource(name="bossChange")
	    BossChange changeB;
	    
	    @RequestMapping(value = "editBoss",method = {RequestMethod.POST, RequestMethod.GET})
	    public String getBoss(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("change() is executed!");

	        model.addAttribute("boss", changeB.getBoss(request, response));
	        return "index";
	    }
	    
	    @Resource(name="BossUpdate")
	    BossUpdate updateB;
	    
	    @RequestMapping(value = "doEditBoss",method = {RequestMethod.POST, RequestMethod.GET})
	    public String updateBoss(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("update() is executed!");

	        model.addAttribute("NewBoss", updateB.updateBoss(request, response));
	        return "index";
	    }
	 

	    
	  
	    
	    @Resource(name="BossCreaterImpl")
	    BossCreater createrB;
	    
	    @RequestMapping(value = "doCreateBoss",method = {RequestMethod.POST, RequestMethod.GET})
	    public String insertBoss(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("BossInsert() is executed!");

	        model.addAttribute("NewBoss", createrB.insertBoss(request, response));
	        return "index";
	    }
	    
	    //############# CALCULATOR ##############
	    

	    @Resource(name="calculatorServlet")
	    CalculatorServlet calc;
	    
	    @RequestMapping(value = "CalculatorServlet",method = {RequestMethod.POST, RequestMethod.GET})
	    public String calculate(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("calculator() is executed!");

	        model.addAttribute("calculator", calc.calculate(request, response));
	        return "index";
	    }
	    
	    @Resource(name="calculatorRESTServiceClient")
	    CalculatorRESTServiceClient calcR;
	    
	    @RequestMapping(value = "CalculatorRESTServiceClient",method = {RequestMethod.POST, RequestMethod.GET})
	    public String calculatePOST(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("calculator() is executed!");

	        model.addAttribute("calculator", calcR.calculate(request, response));
	        return "index";
	    }
	    
	    
	    
	 
	    

}