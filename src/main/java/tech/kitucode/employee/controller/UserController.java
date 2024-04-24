package tech.kitucode.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.employee.model.User;
import tech.kitucode.employee.service.UserService;
import tech.kitucode.employee.service.dto.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users/{idNumber}")
    public ResponseEntity<User> findByIdNumber(@PathVariable String idNumber) {
        User user = userService.findByIdNumber(idNumber);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{idNumber}")
    public ResponseEntity delete(@PathVariable String idNumber) {
        Map<String, Object> response = new HashMap<>();
        if (userService.delete(idNumber)) {
            response.put("status", 200);
            response.put("desc", "user deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
