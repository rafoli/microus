package org.microus.sample.todo.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.microus.sample.todo.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private Set<Todo> todos = new HashSet<>();

	public Collection<Todo> getAllTodos() {
		return todos;
	}

	public Optional<Todo> getTodo(long id) {		
		return todos.stream().filter(todo -> todo.getId() == id).findFirst();
	}

	public Todo saveTodo(Todo todo) {
		
        todo.setId(todos.size() + 1L);
        todos.add(todo);
        
		return todo;
	}

	public void deleteAllTodos() {
		todos.clear();
	}

	public void deleteTodo(long todoId) {
        Optional<Todo> todoOptional = getTodo(todoId);

        if ( todoOptional.isPresent() ) {
            todos.remove(todoOptional.get());
        }
	}

	public void updateTodo(Todo todo, Todo newTodo) {
		todos.remove(todo);
		todos.add(newTodo);
	}

}
