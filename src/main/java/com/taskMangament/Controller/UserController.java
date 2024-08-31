package com.taskMangament.Controller;

import com.taskMangament.Entity.User;
import com.taskMangament.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/getProfileData")
    public ResponseEntity<User> getUserData(Authentication authentication) {
        Optional<User> user = userService.findByEmail(authentication.getName());
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateProfileData")
    public ResponseEntity<User> updateUserProfile(Authentication authentication, @Valid @RequestBody User userDetails) {
        Optional<User> user = userService.findByEmail(authentication.getName());
        if (user.isPresent()) {
            User userUpdate = user.get();
            userUpdate.setUsername(userDetails.getUsername());
            userUpdate.setEmail(userDetails.getEmail());
            userUpdate.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userService.updateUser(userUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
