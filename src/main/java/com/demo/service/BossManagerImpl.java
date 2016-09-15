package com.demo.service;

import java.sql.SQLException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.BossDAO;
import com.demo.model.BossVO;
 
@Service
public class BossManagerImpl implements BossManager {
 
    @Autowired
    BossDAO dao;
    
    int securitycode;
     
    public List<BossVO> getAllBosses() throws SQLException 
    {
        return dao.getAllBosses();
    }
    

}
