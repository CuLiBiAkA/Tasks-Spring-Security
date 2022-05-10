package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

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
        return  userDao.findUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
}