package com.komal.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class toDoController {
	
	private toDoService toDoService;
	//private Logger log = LoggerFactory.getLogger(getClass());
	public toDoController(com.komal.springboot.myfirstwebapp.todo.toDoService toDoService) {
		super();
		this.toDoService = toDoService;
	}
	
	private String getLoggedInUserName()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping("todo")
	public String gotoTodoPage(ModelMap model)
	{
		//log.debug((String)model.get("name"));
		List<toDo> todos = toDoService.findAlltodos(getLoggedInUserName());
		model.addAttribute("list", todos);
		return "/WEB-INF/jsp/toDo.jsp";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showAddtodo(ModelMap model)
	{
		toDo todo = new toDo(0,getLoggedInUserName(),"",LocalDate.now().plusYears(2),false);
		model.put("data", todo);
		return "/WEB-INF/jsp/addTodo.jsp";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addTodoIntoList(ModelMap model , @Valid toDo data, BindingResult result)
	{
		if(result.hasErrors())
		{
			toDo todo = new toDo(0,getLoggedInUserName(),data.getDescription(),LocalDate.now().plusYears(2),false);
			model.put("data", todo);
			return "/WEB-INF/jsp/addTodo.jsp";
		}
		toDoService.addTodo(getLoggedInUserName(), data.getDescription(), data.getTargetDate(), false);
		return "redirect:todo";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)
	{
		toDoService.deleteTodo(id);
		return "redirect:todo";	
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
	{
		toDo todo = toDoService.findById(id);
		model.addAttribute("data", todo);
		return "/WEB-INF/jsp/addTodo.jsp";	
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodoIntoList(ModelMap model , @Valid toDo data, BindingResult result)
	{
		if(result.hasErrors())
		{
			toDo todo = new toDo(0,getLoggedInUserName(),data.getDescription(),LocalDate.now().plusYears(2),false);
			model.addAttribute("data", todo);
			return "/WEB-INF/jsp/addTodo.jsp";
		}
		String userName = getLoggedInUserName();
		data.setUsername(userName);
		data.setTargetDate(LocalDate.now().plusYears(2));
		toDoService.updateTodo(data);
		return "redirect:todo";
	}
}
