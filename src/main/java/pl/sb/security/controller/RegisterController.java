package pl.sb.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sb.security.model.dto.UserDto;
import pl.sb.security.service.UserService;
import javax.validation.Valid;

@Controller
class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register-page";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        boolean isUsernameTaken = userService.isUsernameIsAlreadyTaken(userDto.getUsername());
        if (bindingResult.hasErrors() || isUsernameTaken) {
            model.addAttribute("isUsernameTaken", isUsernameTaken);
            return "register-page";
        } else {
            boolean isRegistered = userService.register(userDto);
            redirectAttributes.addFlashAttribute("isRegistered", isRegistered);
            return "redirect:/login?success";
        }
    }

}