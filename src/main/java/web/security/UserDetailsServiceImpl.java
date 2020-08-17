package web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }
    //public UserService userService = new UserServiceImp();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
