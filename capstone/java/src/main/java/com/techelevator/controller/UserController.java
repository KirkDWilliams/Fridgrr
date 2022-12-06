package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private UserDao userDao;

    @GetMapping("/all")
    public List<User> getAllUsers(Principal principal){
        return userDao.findAll();
    }
    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable("userId") int userId, Principal principal){
        return userDao.getUserById(userId);
    }
    @GetMapping("/username")
    public User findByUsername(@RequestBody @Valid String username, Principal principal){
        return userDao.findByUsername(username);
    }
    @GetMapping("")
    public int findIdByUsername(@RequestBody @Valid String username, Principal principal){
        return userDao.findIdByUsername(username);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createUser(@RequestBody @Valid String username, String password, String role, Principal principal){
        return userDao.create(username, password, role);
    }
    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteUser(@RequestBody @Valid int userid, Principal principal){
        return userDao.deleteUser(userid);
    }


}
