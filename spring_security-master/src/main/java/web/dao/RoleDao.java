package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.Role;

public interface RoleDao extends CrudRepository<Role, Long> {
}
