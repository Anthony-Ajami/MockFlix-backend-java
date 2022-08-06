package en.mockflix.controllers;

import en.mockflix.entities.User;
import en.mockflix.exceptions.UserNotFoundException;
import en.mockflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user is found with id: " + id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUserPassword(@PathVariable(value = "id") Long id
            , @RequestBody User user) throws UserNotFoundException {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user is found with id: " + id));
        user1.setPassword(user.getPassword());
        userRepository.save(user1);

        // this below may not work
        userRepository.delete(user);

        return ResponseEntity.ok().body(user1);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user is found with id: " + id));
        userRepository.delete(user);
        return ResponseEntity.ok().body(user);
    }


}
