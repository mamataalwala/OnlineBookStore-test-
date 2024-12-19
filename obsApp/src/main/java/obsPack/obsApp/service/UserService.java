package obsPack.obsApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import obsPack.obsApp.dao.UserDao;
import obsPack.obsApp.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User saveUser(User user) {
		return userDao.save(user);
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userDao.findAll().forEach(users::add);
		return users;
	}

	public User getUsers(Integer userId) {
		return userDao.findById(userId).orElseThrow();
	}

//	
//	public void deleteUser(Long userId)
//	{
//		userDao.deleteById(userId);
//	}
//	

	public void deleteUser(Integer userId) {
//		userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		userDao.deleteById(userId);
	}

	public User updateUser(User user) {
		userDao.findById(user.getUserId()).orElseThrow();
		return userDao.save(user);
	}

//	 Search userby name
	public User getUserByName(String firstName) {
		return userDao.findByFirstName(firstName)
				.orElseThrow(() -> new RuntimeException("User not found with name: " + firstName));
	}

	// Method to get users sorted by name
	public List<User> getUsersSortedByName() {
		return userDao.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}

	// Method to handle login
	public User login(String emailId, String Password) {
		return userDao.findByEmailIdAndPassword(emailId, Password)
				.orElseThrow(() -> new RuntimeException("Invalid email or password!"));
	}

	/*
	 * // Method to handle login public Employee login(String emailId, String
	 * employeePassword) { return
	 * employeeDao.findByEmailIdAndEmployeePassword(emailId, employeePassword)
	 * .orElseThrow(() -> new RuntimeException("Invalid email or password!")); }
	 */
}
