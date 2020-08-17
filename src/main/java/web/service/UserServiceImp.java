package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(String userName) {
        User user = null;
        try {
            user = userDao.getUser(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = null;
        try {
            userList = (List<User>) userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            userDao.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(User user) {
        try {
            userDao.editUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
