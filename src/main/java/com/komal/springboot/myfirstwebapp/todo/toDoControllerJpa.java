package com.komal.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@SessionAttributes("name")
public class toDoControllerJpa {
	
	private toDoService toDoService;
	private todoRepository toDoRepository;
	private Logger log = LoggerFactory.getLogger(getClass());
	public toDoControllerJpa(com.komal.springboot.myfirstwebapp.todo.toDoService toDoService, todoRepository toDoRepository) {
		super();
		this.toDoService = toDoService;
		this.toDoRepository = toDoRepository;
	}
	
	private String getLoggedInUserName()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping("todo")
	public String gotoTodoPage(ModelMap model)
	{
		List<toDo> todos = toDoRepository.findByusername(getLoggedInUserName());
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
		data.setUsername(getLoggedInUserName());
		toDoRepository.save(data);
		return "redirect:todo";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)
	{
		toDoRepository.deleteById(id);
		return "redirect:todo";	
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
	{
		log.debug(getLoggedInUserName(),id);
		toDo todo = toDoRepository.findById(id).get();
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
//		data.setTargetDate(LocalDate.now().plusYears(2));
//		toDoService.updateTodo(data);
		toDoRepository.save(data);
		return "redirect:todo";
	}
}
