package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;





@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/home", "/login" )
            .access("hasAnyRole('USER', 'ADMIN')")
            // anyone can access the login and home pages although the home page will not display any data
            .antMatchers("/login", "/home").access("permitAll")

            .and()
            .formLogin()
            .loginPage("/login")
            // after successful login you will be redirected to the homepage
            .defaultSuccessUrl("/home", true)


            .and()
            .logout()
            .logoutSuccessUrl("/")
            // logout will take you back to the login page
            // end::enableLogout[]

            // Make H2-Console non-secured; for debug purposes
            // tag::csrfIgnore[]
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**")
            // end::csrfIgnore[]

            // Allow pages to be loaded in frames from the same origin; needed for H2-Console
            // tag::frameOptionsSameOrigin[]
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()
    // end::frameOptionsSameOrigin[]

    //tag::authorizeRequests[]
    //tag::customLoginPage[]
    ;
  }

  // Password encocoder used for TESTING ONLY should use BCrypt for prod
  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }


  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());


  }

}
