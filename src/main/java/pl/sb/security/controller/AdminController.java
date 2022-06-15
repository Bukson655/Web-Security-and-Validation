package pl.sb.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sb.security.model.Role;
import pl.sb.security.model.dto.UserDto;
import pl.sb.security.service.UserRoleService;
import pl.sb.security.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public AdminController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/panel")
    public String panel(Model model) {
        List<UserDto> admins = userService.findAllWithRole(Role.ROLE_ADMIN);
        List<UserDto> regularUsers = userService.findAllWithRole(Role.ROLE_USER);
        regularUsers.removeAll(admins);
        model.addAttribute("admins", admins);
        model.addAttribute("regularUsers", regularUsers);
        return "admin-panel";
    }

    @GetMapping("/degrade/{userId}")
    public String degrade(@PathVariable Long userId) {
        userRoleService.degradeAdmin(userId);
        return "redirect:/admin/panel";
    }

    @GetMapping("/promote/{userId}")
    public String promote(@PathVariable Long userId) {
        userRoleService.promoteToAdmin(userId);
        return "redirect:/admin/panel";
    }

}