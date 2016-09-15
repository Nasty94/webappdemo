package com.demo.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.BossVO;
 
public interface BossDAO 
{
    public List<BossVO> getAllBosses() throws SQLException;
    
    public BossVO getBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
    
    public BossVO updateBoss(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;

	public BossVO insertBoss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
}