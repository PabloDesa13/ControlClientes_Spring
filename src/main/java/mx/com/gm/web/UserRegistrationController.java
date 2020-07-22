package mx.com.gm.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.com.gm.domain.UserRegistrationDto;
import mx.com.gm.domain.Usuario;
import mx.com.gm.servicio.UsuarioService;


@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    private UsuarioService userService;

    @ModelAttribute("usuario")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("usuario") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        Usuario existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "register";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }

}
