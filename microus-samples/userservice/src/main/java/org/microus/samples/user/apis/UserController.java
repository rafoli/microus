package org.microus.samples.user.apis;

import java.util.Arrays;
import java.util.List;

import org.microus.samples.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST endpoint for the user functionality
 * 
 * @author anilallewar
 *
 */
@RestController
@RequestMapping("/")
@Api(value = "/user")
public class UserController {

	@Value("${mail.domain}")
	private String mailDomain;

	private List<UserDTO> users = Arrays.asList(new UserDTO("Anil", "Allewar", "1", "anil.allewar@" + mailDomain),
			new UserDTO("Rohit", "Ghatol", "2", "rohit.ghatol@" + mailDomain),
			new UserDTO("John", "Snow", "3", "john.snow@" + mailDomain));

	/**
	 * Return all users
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	@ApiOperation(value = "All users")
	public List<UserDTO> getUsers() {
		return users;
	}

	/**
	 * Return user associated with specific user name
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ApiOperation(value = "Get user by username")
	public UserDTO getUserByUserName(@PathVariable("userName") String userName) {
		UserDTO userDtoToReturn = null;
		for (UserDTO currentUser : users) {
			if (currentUser.getUserName().equalsIgnoreCase(userName)) {
				userDtoToReturn = currentUser;
				break;
			}
		}

		return userDtoToReturn;
	}
}
