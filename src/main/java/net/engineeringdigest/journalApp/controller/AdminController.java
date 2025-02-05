package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.dto.UserDTO;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    @Operation(summary = "Get all users ")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    @Operation(summary = "Create Admin user")
    public ResponseEntity<User> createAdminUser(@RequestBody User user) {
        User newUser = userService.saveAdmin(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/make-admin")
    @Operation(summary = "Make exisitng user ADMIN")
    public ResponseEntity<User> promoteToAdmin(@RequestBody User user){
        User admin = userService.makeAdmin(user.getUsername());
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("clear-app-cache")
    @Operation(summary = "Clears Application Cache")
    public void clearAppCache() {
        appCache.init();
    }

}