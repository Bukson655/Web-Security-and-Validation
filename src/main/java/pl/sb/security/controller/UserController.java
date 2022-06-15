package pl.sb.security.controller;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sb.security.model.dto.UserDto;
import pl.sb.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user-panel")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String panel(Model model, @CurrentSecurityContext SecurityContext securityContext) {
        UserDto user = userService.findUserByUsername(securityContext.getAuthentication().getName());
        model.addAttribute("user", user);
        return "user-panel";
    }

    @PostMapping()
    public String edit(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-panel";
        } else {
            userService.edit(userDto);
            return "redirect:/user-panel?success";
        }
    }

}