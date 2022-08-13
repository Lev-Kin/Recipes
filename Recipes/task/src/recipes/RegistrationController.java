package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.security.User;
import recipes.security.UserService;

import javax.validation.Valid;

@RestController
@Validated
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/register")
    void register(@RequestBody @Valid User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already authenticated");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userService.save(user);
    }

}

