package web.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    public final UserService userService;

    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User user = null;
        Set<String> rolesList = new HashSet<>();
        try {
            user = userService.getUser(httpServletRequest.getParameter("j_username"));
        } catch (Exception e) {

        }

        if (user != null) {
            for (Role role : user.getRoles()) {
                rolesList.add(role.getRole());
            }
        } else {
            httpServletResponse.sendRedirect("/login");
        }

        if (rolesList.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/user");
        }
    }
}