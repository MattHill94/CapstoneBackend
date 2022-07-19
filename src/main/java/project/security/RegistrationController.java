package project.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.data.UserRepository;



// This Class is not currently implemented into the project


@Controller
// Api end point for regustering
@RequestMapping("/register")
public class RegistrationController {


  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
          UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  // should return registration.html currently not implemented
  public String registerForm() {
    return "registration";
  }
  
  @PostMapping
  // after processing the registration request will be redirected to the login page
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }

}
