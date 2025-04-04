package org.example.projekt2_gruppe1_onlywish.controller;


import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class Usercontroller {

@Autowired
   private UserRepository userRepository;

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userRepository.createUser(user);
        return "User created successfully";
    }
    @GetMapping("/getByEmail")
    public User getUserByEmail(@RequestParam String email) {
        return userRepository.getUserbyemail(email);
    }

    @GetMapping ("/getALL")
    public void getAllUsers() {
        userRepository.getAllUsers();
    }

    @GetMapping ("/getById")
    public User getUserbyId(@RequestParam int id){
        return userRepository.getUserbyId(id);

    }

}
