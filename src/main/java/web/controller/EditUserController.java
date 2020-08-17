package web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan("web")
public class EditUserController {

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    public EditUserController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(
            value = "/admin/edit",
            method = RequestMethod.GET)
    public String editPageGet(ModelMap modelMap, @RequestParam("name") String name) {
        User user = userService.getUser(name);
        List<String> roleArr = new ArrayList<>();
        String roleUser = null;
        String roleAdmin = null;
        for (Role r : user.getRoles()) {
            if (r.getRole().equals("ROLE_USER")) {
                roleUser = "ROLE_USER";
            } else if (r.getRole().equals("ROLE_ADMIN")) {
                roleAdmin = "ROLE_ADMIN";
            }
        }
        modelMap.addAttribute("user", user)
                .addAttribute("roleAdmin", roleAdmin)
                .addAttribute("roleUser", roleUser);
        return "edit";
    }

    @RequestMapping(
            value = "/admin/edit",
            method = RequestMethod.POST)
    public String editPagePost(ModelMap modelMap,
                               @RequestParam("id") String id,
                               @RequestParam("name") String name,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String passsword,
                               @RequestParam(value = "admin", required = false, defaultValue = "") String admin,
                               @RequestParam(value = "user", required = false, defaultValue = "") String user) {
        Set<Role> roleSet = new HashSet<>();
        if (user.equals("on")) {
            roleSet.add(roleService.getOne(1L));
        }
        if (admin.equals("on")) {
            roleSet.add(roleService.getOne(2L));
        }
        User updateUser = new User((long) Integer.parseInt(id), name, lastName, email, passsword, roleSet);
        userService.editUser(updateUser);
        return "redirect:/admin";
    }
}