package web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;

@Controller
@ComponentScan("web")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(
            value = "login",
            method = RequestMethod.GET)
    public String getLoginPage(ModelMap modelMap) {
        return "login";
    }

    @RequestMapping(
            value = "admin",
            method = RequestMethod.GET)
    public String getAdminPage(ModelMap modelMap) {
        List<User> userList = userService.getUsers();
        modelMap.addAttribute("users", userList);
        return "admin";
    }

    @RequestMapping(
            value = "admin",
            method = RequestMethod.POST)
    public String adminPagePost(ModelMap modelMap,
                                @RequestParam("name") String name,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("email") String email,
                                @RequestParam("password") String passsword,
                                @RequestParam(value = "admin", required = false, defaultValue = "") String admin,
                                @RequestParam(value = "user", required = false, defaultValue = "") String user) {

        Set<Role> roleSet = new HashSet<>();
        if (user.equals("ROLE_USER")) {
            roleSet.add(roleService.getOne(1L));
        }

        if (admin.equals("ROLE_ADMIN")) {
            roleSet.add(roleService.getOne(2L));
        }

        userService.addUser(new User(name, lastName, email, passsword, roleSet));
        return "redirect:/admin";
    }

    @RequestMapping(
            value = "delete",
            method = RequestMethod.GET)
    public String _deletePagePost(ModelMap modelMap, @RequestParam("name") String name) {
        userService.deleteUser(userService.getUser(name));
        return "redirect:/admin";
    }

    @RequestMapping(
            value = "delete",
            method = RequestMethod.POST)
    public String deletePagePost(ModelMap modelMap, @RequestParam("name") String name) {
        userService.deleteUser(userService.getUser(name));
        return "redirect:/admin";
    }

}
