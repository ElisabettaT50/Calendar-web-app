package calendar.example.calendar.controllers;

import calendar.example.calendar.entities.User;
import calendar.example.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/post")
    public ResponseEntity<User> postUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.addUser(user));
    }
    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getFromId(@PathVariable Long id){
        Optional<User> userOptional = userService.getUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id , @RequestBody User user){
        Optional<User> userOptional = userService.updateUser(id,user);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteFromId(@PathVariable Long id){
        Optional<User> userOptional = userService.deleteUserFromId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    @PutMapping("/addcalendar/{id}")
    public ResponseEntity<User> putCalendar(@PathVariable Long id,@RequestParam Long calendar){
        Optional<User> userOptional = userService.addCalendarToUser(id, calendar);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
}