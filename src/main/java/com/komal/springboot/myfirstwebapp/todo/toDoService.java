package com.komal.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class toDoService {
	private static List<toDo> todos = new ArrayList<toDo>();
	private static int todosCount = 0;
	static {
		todos.add(new toDo(++todosCount, "komal","Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new toDo(++todosCount, "komal","Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new toDo(++todosCount, "komal","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}
	
	public List<toDo> findAlltodos(String Username)
	{
		Predicate<toDo> pre = (toDo x)->x.getUsername().equalsIgnoreCase(Username);
		return todos.stream().filter(pre).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		toDo todo = new toDo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	}
	
	public void deleteTodo(int id) {
		
		Predicate<toDo> predicate= (toDo x) -> x.getId() == id;
		todos.removeIf(predicate);
//		 Iterator<toDo> itr = todos.iterator(); 
//		  
//	        // Holds true till there is single element 
//	        // remaining in the object 
//	        while (itr.hasNext()) { 
//	  
//	            // Remove elements smaller than 10 using 
//	            // Iterator.remove() 
//	        	toDo x = (toDo)itr.next(); 
//	            if (x.getId() == id) 
//	                itr.remove(); 
//	        } 
	}
	
//	public void updateTodo(@Valid toDo todo) {
//		
//		Iterator<toDo> itr = todos.iterator(); 
//		while (itr.hasNext()) { 
//			toDo x = (toDo)itr.next(); 
//			if (x.getId() == id)
//			{
//				
//			}
//		}
//	}
	
	public toDo findById(int id) {
		
		Predicate<toDo> predicate= (toDo x) -> x.getId() == id;
		toDo data = todos.stream().filter(predicate).findFirst().get();
		return data;
//		Iterator<toDo> itr = todos.iterator(); 
//		while (itr.hasNext()) { 
//			toDo x = (toDo)itr.next(); 
//			if (x.getId() == id)
//			{
//				return x;
//			}
//		}
	}

	public void updateTodo(@Valid toDo data) {
		deleteTodo(data.getId());
		todos.add(data);
	}
}
