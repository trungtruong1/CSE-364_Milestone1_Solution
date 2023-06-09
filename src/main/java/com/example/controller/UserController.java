package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ObjectIdException;
import com.example.exception.ParameterErrorNumberException;
import com.example.exception.ParameterErrorStringException;
import com.example.model.User;
import com.example.service.UserService;
import com.mongodb.client.result.UpdateResult;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(ObjectIdException.class)
    public ResponseEntity<String> handleObjectIdException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Something wrong when saving the user");
    }

    @ExceptionHandler(ParameterErrorNumberException.class)
    public ResponseEntity<String> handleParameterErrorNumber() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("User id does not exist!");
    }

    @ExceptionHandler(ParameterErrorStringException.class)
    public ResponseEntity<String> handleParameterErrorString() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                             .body("Parameter is not a number!");
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        String new_id = userService.save(user);
        Optional<User> new_user = userService.getUserById(new_id);
        if(new_user == null) {
            throw new ObjectIdException("Something wrong when saving the user!");
        } 
        return ResponseEntity.ok(new_user.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        if(id.length() == 0) {
            throw new ParameterErrorStringException("Parameter is not a number!");
        }
        try {
            int user_id = Integer.parseInt(id);
            Optional<User> result = userService.getUserById(user_id);
            if(result.isPresent()) {
                return ResponseEntity.ok(result.get());
            }
            throw new ParameterErrorNumberException("User id does not exist!");
        } catch(NumberFormatException e) {
            throw new ParameterErrorStringException("Parameter is not a number!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if(id.length() == 0) {
            throw new ParameterErrorStringException("Parameter is not a number!");
        }
        try {
            int user_id = Integer.parseInt(id);
            UpdateResult result = userService.updateUserById(user_id, user);
            if(result.getMatchedCount() == 0) {
                throw new ParameterErrorNumberException("User id does not exist!");
            }
            if(!result.wasAcknowledged()) {
                throw new ObjectIdException("Something wrong when saving the user!");
            }
            return ResponseEntity.ok(userService.getUserById(user_id).get());
        } catch(NumberFormatException e) {
            throw new ParameterErrorStringException("Parameter is not a number!");
        }
    }
}