package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public void addUser(User user);

    public void editUser(User user);

    public void deleteUser(User user);

    public User getUser (String userName);
}
