package com.itc.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itc.beans.Audi;
import com.itc.dao.AudiDAO;


@Controller
public class AudiController {
	
	@Autowired
	AudiDAO dao;
	
	@RequestMapping("/viewemp")
	public ModelAndView viewemp(){
		
		List<Audi> list=dao.getAllEvents();
		return new ModelAndView("viewAll","list",list);	
	}
	
	@RequestMapping("/buy/{eventid}")
	public ModelAndView buy(@PathVariable int eventid){
		
		Audi audi=dao.getEventBean(eventid);
		return new ModelAndView("buyForm","command",audi);
	}
	
	@RequestMapping(value="/buySave",method = RequestMethod.GET)
	public String editsave(@ModelAttribute("audi") Audi audi,Model model){
		int qty=audi.getQuantity();
		model.addAttribute("audi", audi);
		model.addAttribute("qty",qty);
		dao.update(audi);
		return "confirm";
	}
}
