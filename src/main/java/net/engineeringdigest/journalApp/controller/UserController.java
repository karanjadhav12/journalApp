package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.dto.UserDTO;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update and Delete Users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    @Operation(summary = "Update user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
//        User user = userService.mapDTOToEntity(userDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }

    @DeleteMapping
    @Operation(summary = "Delete user")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Greetings")
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherService.getWeather("New_York");
        String greetings = "";
        if (response != null) {
            greetings = ", Temp is : " + response.getCurrent().getTemperature() + " degree Celsius";
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greetings, HttpStatus.OK);
    }

}
