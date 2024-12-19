package obsPack.obsApp.dio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotEmpty(message = "Email must not be empty!")
	@Email(message = "Email is not valid!")
	private String emailId;
	
	@NotEmpty(message = "Password must not be empty!")
	@Size(min = 8, message = "Password length must be 8 and contain uppercase, lowercase, and digits")
	private String Password;

    // Getters and Setters
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

    

}
