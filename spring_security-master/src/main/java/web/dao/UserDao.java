package web.dao;

import web.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findUserById(Long id);

    User findUserByName(String username);

    @Modifying
    @Query(value = "insert into role_table (id, name) values (?, ?)", nativeQuery = true)
    void saveRole(@Param("id") Long id, @Param("name") String name);

    void removeUsersById(Long id);
}
