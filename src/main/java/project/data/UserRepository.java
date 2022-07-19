package project.data;

import org.springframework.data.repository.CrudRepository;
import project.User;


public interface UserRepository extends CrudRepository<User, Long> {

    // Finds user by username
    User findByUsername(String username);

}
