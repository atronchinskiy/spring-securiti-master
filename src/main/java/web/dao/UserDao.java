package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public List<?> getAllUsers() throws SQLException;

    public void addUser(User user) throws SQLException;

    public void deleteUser(User user) throws SQLException;

    public void editUser(User user) throws SQLException;

//    public void clearAll() throws SQLException;

    public User getUser(String userName) throws SQLException;
}
