package com.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.demo.model.BossVO;
 
public interface BossManager 
{
    public List<BossVO> getAllBosses() throws SQLException;
    
}
