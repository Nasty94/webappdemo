package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.BossDAO;
import com.demo.model.BossVO;
 
@Service
public class BossUpdate extends HttpServlet implements BossUpdateInt {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    BossDAO dao;
    

    
    public BossVO updateBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
     return dao.updateBoss(request, response);
    }


}