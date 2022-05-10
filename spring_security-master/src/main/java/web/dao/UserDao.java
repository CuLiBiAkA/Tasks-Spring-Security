package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.User;

public interface UserDao extends CrudRepository<User, Long> {

    User findUserById(Long id);
}
