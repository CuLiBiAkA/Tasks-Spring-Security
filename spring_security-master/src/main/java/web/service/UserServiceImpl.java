package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static web.config.MyConstantString.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getListUsers() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.removeUsersById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public void setUserRole(User user, boolean flagSetRoleUser) {
        Set<Role> roleSet = new HashSet<>();
        if (flagSetRoleUser) {
            roleSet.add(new Role(1L, ROLE_ADMIN));
        } else {
            roleSet.add(new Role(2L, ROLE_USER));
        }
        user.setRoles(roleSet);
    }

    @Override
    public String getPasswordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void saveRole(Role role) {
        userDao.saveRole(role.getId(), role.getName());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByName(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
