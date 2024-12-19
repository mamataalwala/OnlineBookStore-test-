package obsPack.obsApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import obsPack.obsApp.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
//	Optional<User> findByUserIdAndPassword(long userId, String password);

// Method to find employee by email and password
	Optional<User> findByEmailIdAndPassword(String emailId, String Password);

	// Optional<Employee> findByEmailId(String emailId);

// Method to find USer by name
	Optional<User> findByFirstName(String firstName);

	// Method to find all employees and sort by name
	List<User> findAll(Sort sort);

}
