package com.komal.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<toDo, Integer>{
	public List<toDo> findByusername( String UserName);
}
