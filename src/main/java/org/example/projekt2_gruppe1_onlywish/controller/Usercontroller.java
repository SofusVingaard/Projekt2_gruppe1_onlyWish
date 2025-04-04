package org.example.projekt2_gruppe1_onlywish.controller;


import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class Usercontroller {

@Autowired
   private UserRepository userRepository;

    @PostMapping("/create")
    public String createUser
    (@RequestParam("name")String name,
    @RequestParam ("Age") int age,
    @RequestParam ("Email") String email,
    @RequestParam ("Password") String password) {
        User user = new User(name,age,email,password);
        userRepository.createUser(user);
        return "redirect:/";
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
