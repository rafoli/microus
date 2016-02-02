package org.microus.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.microus.app.controller.dto.Change;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	private String message = "Hello World";
	private List<Change> changes = new ArrayList<Change>();

	@RequestMapping(value="/", method=RequestMethod.GET)
	public Map<String,Object> home() {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", message);
	    return model;
	  }

	@RequestMapping(value="/changes", method=RequestMethod.GET)
	public List<Change> listChanges() {
		return changes;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public Map<String,Object> update(@RequestBody Map<String,String> map, Principal principal) {
		message = map.get("content");
		
		if (message != null && !message.isEmpty()) {
			Change change = new Change();
			change.setTimestamp(new Date());
			change.setUser(principal.getName());
			change.setContent(message);
			changes.add(change);
			
			if (changes.size()>10) {
				changes = changes.subList(0, 9);
			}
		}
		
		
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("content", message);
	    
		return model;
	}

}
