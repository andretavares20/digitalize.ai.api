package br.com.digitalizeai.api.application.adapters.controllers.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalizeai.api.domain.User;
import br.com.digitalizeai.api.domain.ports.interfaces.UserServicePort;

@RestController
@RequestMapping("/client/users")
public class UserClientController {

    private final UserServicePort userServicePort;

    public UserClientController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServicePort.createUser(user);
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam String username) {
        boolean isAvailable = userServicePort.findByUsername(username).isEmpty();
        return ResponseEntity.ok(isAvailable);
    }

    @GetMapping("/check-email")
    public boolean checkEmail(@RequestParam String email) {
        return userServicePort.isEmailAvailable(email);
    }

    @GetMapping("/check-telephone")
    public boolean checkTelephone(@RequestParam String telephone) {
        return userServicePort.isTelephoneAvailable(telephone);
    }
}
