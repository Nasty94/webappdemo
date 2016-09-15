package com.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.demo.service.BossManager;
 
@Controller
@RequestMapping("/boss")
public class BossController 
{
    @Autowired
    BossManager manager;
 
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String getAllBosses(Model model) throws SQLException
    {
        model.addAttribute("bosses", manager.getAllBosses());
        return "index";
    }
    
 
}