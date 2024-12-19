package obsPack.obsApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import obsPack.obsApp.dio.LoginRequest;
//import emsPack.emsApp.entity.;
import obsPack.obsApp.entity.User;
import obsPack.obsApp.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/get/user")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/get/user/{userId}")
	public User getUser(@PathVariable Integer userId) {
		return userService.getUsers(userId);
	}
//
//	@DeleteMapping("/delete/user/{userId}")
//	public void deleteUser(@PathVariable  Long userId) {
//		userService.deleteUser(userId);
//	}

	// Delete Customer
	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	@PutMapping("/update/user")
//	public Employee updateEmployee(@RequestBody Employee employee)
//	{
//		return employeeService.updateEmployee(employee);
//	}

	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Endpoint to get user by name
	@GetMapping("/name/{firstName}")
	public ResponseEntity<User> getUserByName(@PathVariable String firstName) {
		User user = userService.getUserByName(firstName);
		return ResponseEntity.ok(user);
	}

	// Endpoint to get users sorted by name

	@GetMapping("/sorted-by-name")
	public ResponseEntity<List<User>> getUsersSortedByName() {
		List<User> sortedUsers = userService.getUsersSortedByName();
		return ResponseEntity.ok(sortedUsers);
	}

	// Endpoint for login

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		try {
			User user = userService.login(loginRequest.getEmailId(), loginRequest.getPassword());
			return ResponseEntity.ok("Welcome, " + user.getFirstName() + "!");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	/*
	 * // Endpoint for login
	 * 
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody
	 * LoginRequest loginRequest) { try { Employee employee =
	 * employeeService.login(loginRequest.getEmailId(),
	 * loginRequest.getEmployeePassword()); return ResponseEntity.ok("Welcome, " +
	 * employee.getEmployeeName() + "!"); } catch (RuntimeException e) { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); } }
	 */

}
