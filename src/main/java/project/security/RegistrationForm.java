package project.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.User;

@Data
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
