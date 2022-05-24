package web.dao;

import web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import static web.config.MyConstantString.*;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findUserById(Long id);

    User findUserByName(String username);

    @Modifying
    @Query(value = SQL_INSERT_INTO_TABLE_ROLES, nativeQuery = true)
    void saveRole(@Param(ID) Long id, @Param(NAME) String name);

    void removeUsersById(Long id);
}