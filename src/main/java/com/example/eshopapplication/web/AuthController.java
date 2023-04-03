package com.example.eshopapplication.web;

import com.example.eshopapplication.dto.UserDto;
import com.example.eshopapplication.entity.User;
import com.example.eshopapplication.service.ShoppingCartService;
import com.example.eshopapplication.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }
    @PostMapping("/login")
    public String loginPost(Model model){
        model.addAttribute("body_content","login");
        model.addAttribute("headerAndFooter",false);
        return "master-template";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("body_content","login");
            model.addAttribute("headerAndFooter",false);
            return "master-template";
        }
        return "redirect:/";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            UserDto user = new UserDto();
            model.addAttribute("user", user);
            model.addAttribute("body_content","register");
            model.addAttribute("headerAndFooter",false);
            return "master-template";
        }
        return "redirect:/";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", "6969", "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect:/login";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", auth.getAuthorities());
        model.addAttribute("users", users);
        model.addAttribute("body_content","users");
        return "master-template";
    }
    @GetMapping("/accessDenied")
    public String getAccessDenied(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("[ADMIN]"))) {
            System.out.println("ADMIN E");
        }
        if (auth != null)
            auth.getAuthorities().forEach(System.out::println);
        model.addAttribute("body_content","access-denied");
        return "master-template";
    }
}
