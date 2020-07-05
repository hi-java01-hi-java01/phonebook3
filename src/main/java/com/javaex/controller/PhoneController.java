package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone" )
public class PhoneController {
	
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String list(Model model) {
		
		PhoneDao phone = new PhoneDao();
		List<PersonVo> personList = phone.getPersonList();
		
		model.addAttribute("psersonList", personList);
		
		return "list";
	}
	
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String writeForm() {

		return "writeForm";
	}

	
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String write(@ModelAttribute PersonVo personVo) {
		
		PhoneDao phone = new PhoneDao();
		phone.personInsert(personVo);
		

		return "redirect:/phone/list";
	}

	
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		
		PhoneDao phone = new PhoneDao();
		PersonVo personVo = phone.getPerson(personId);
		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String update(@ModelAttribute PersonVo personVo) {

		PhoneDao phone = new PhoneDao();
		phone.personUpdate(personVo);

		return "redirect:/phone/list";
	}
	
	
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String delete(@RequestParam("personId") int personId) {
		
		PhoneDao phone = new PhoneDao();
		phone.personDelete(personId);

		return "redirect:/phone/list";
	}
	
}
