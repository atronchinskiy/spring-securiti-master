package web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImp;

@Controller
@ComponentScan("web")
public class UserController {
    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    public UserController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userPageGet(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("user", user);
        return "user";
    }
}
