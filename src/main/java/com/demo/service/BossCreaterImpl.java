package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.BossDAO;
import com.demo.model.BossVO;

public class BossCreaterImpl implements BossCreater {
	
	 @Autowired
	    BossDAO dao1;
	     
	    public BossVO insertBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
	    {
	        return dao1.insertBoss(request, response);
	    }


		

}
