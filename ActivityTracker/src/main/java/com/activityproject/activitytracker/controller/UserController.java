

package com.activityproject.activitytracker.controller;


import java.util.List;

/**
 * Interface for UserController. Contains methods for handling user related operations
 */
import com.activityproject.activitytracker.model.User;
import com.activityproject.activitytracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * RESTful API for User management
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    /**
     * User service for accessing user data
     */
    private final UserService userService;

    /**
     * Get a list of all users
     *
     * @return list of all users
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        log.info("endpoint getsUsers is called");
        return userService.getUsers();
    }

    /**
     * Save a new user
     *
     * @param user the user to be saved
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        log.info("endpoint saveUser is called");
        return userService.saveUser(user);
    }


    @GetMapping("me")
    public User getMine(){
        log.info("endpoint getMine is called");
        return userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}