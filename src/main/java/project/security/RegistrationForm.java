package project.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.User;

@Data

// These will be all the user details they would fill out if the registration page was implemented saved as a new user in the database
public class RegistrationForm {

  private String username;
  private String password;
  private String fullname;

  private boolean isadmin;

  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        username, passwordEncoder.encode(password),
        fullname, isadmin);
  }
  
}
