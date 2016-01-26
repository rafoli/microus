package org.microus.sample.todo.helper;

import java.util.Collection;

import javax.inject.Inject;

import org.microus.sample.todo.model.Todo;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author rafoli@gmail.com
 *
 */
@Component
public class ResourceHelper {
	
    @Inject
    private EntityLinks entityLinks;
    
	/**
	 * @param id
	 * @param todo
	 * @return
	 */
	public HttpEntity<Resource<Todo>> convertToResource(long id, Todo todo) {
		Resource<Todo> resource = new Resource<Todo>(todo);
		resource.add(this.entityLinks.linkToSingleResource(Todo.class, id));
		return new ResponseEntity<Resource<Todo>>(resource, HttpStatus.OK);
	}
	
	/**
	 * @param todos
	 * @return
	 */
	public HttpEntity<Resources<Todo>> convertToResources(Collection<Todo> todos) {
		Resources<Todo> resources = new Resources<Todo>(todos);
		resources.add(this.entityLinks.linkToCollectionResource(Todo.class));
		
		return new ResponseEntity<Resources<Todo>>(resources, HttpStatus.OK);
	}

}
