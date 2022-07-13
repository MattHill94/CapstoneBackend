package project.data;

import org.springframework.data.repository.CrudRepository;
import project.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
