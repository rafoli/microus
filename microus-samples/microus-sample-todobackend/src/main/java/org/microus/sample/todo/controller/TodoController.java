package org.microus.sample.todo.controller;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.microus.sample.todo.helper.ResourceHelper;
import org.microus.sample.todo.model.Todo;
import org.microus.sample.todo.service.TodoService;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

/**
 * @author rafoli@gmail.com
 *
 */
@RestController
@RequestMapping("/api")
@ExposesResourceFor(Todo.class)
public class TodoController {
	
    // ==================
 	// Private attributes
 	// ==================
	
    @Inject
    private TodoService todoService;
    
    @Inject
    private ResourceHelper resourceHelper;
    
    // ==============
 	// Public methods
 	// ==============
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/todos",
			method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Resources<Todo>> listAll() {
		
		Collection<Todo> todos = todoService.getAllTodos();
		
		return resourceHelper.convertToResources(todos);
    }


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/todos/{todo-id}", 
    		method = RequestMethod.GET,
    		produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Resource<Todo>> getTodo(@ApiParam(defaultValue="0") @PathVariable("todo-id") Long id) {

        Optional<Todo> todo = todoService.getTodo(id);

        if (!todo.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return resourceHelper.convertToResource(id, todo.get());
    }


    /**
     * @param todo
     * @return
     */
    @RequestMapping(value = "/todos",
    		method = RequestMethod.POST,
    	    produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Resource<Todo>> saveTodo(@RequestBody Todo todo) {
    	
    	Todo newTodo = todoService.saveTodo(todo);

        return resourceHelper.convertToResource(newTodo.getId(), newTodo);
    }

    /**
     * 
     */
    @RequestMapping(value = "/todos",
    		method = RequestMethod.DELETE)
    public void deleteAllTodos() {
    	
    	todoService.deleteAllTodos();
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/todos/{todo-id}", 
    		method = RequestMethod.DELETE)
    public void deleteOneTodo(@ApiParam(defaultValue="0") @PathVariable("todo-id") Long id) {
    	
    	todoService.deleteTodo(id);
    }

    /**
     * @param id
     * @param newTodo
     * @return
     */
    @RequestMapping(value = "/todos/{todo-id}", 
    		method = RequestMethod.PUT, 
    		produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Resource<Todo>> updateTodo(@ApiParam(defaultValue="0") @PathVariable("todo-id") Long id, @RequestBody Todo newTodo) {
    	
        Optional<Todo> todoOptional = todoService.getTodo(id);

        if ( !todoOptional.isPresent() ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if ( newTodo == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        todoService.updateTodo(todoOptional.get(), newTodo);

        return resourceHelper.convertToResource(id, newTodo);
    }    


}
