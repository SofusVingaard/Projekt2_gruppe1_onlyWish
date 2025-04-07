package org.example.projekt2_gruppe1_onlywish.controller;


import jakarta.servlet.http.HttpSession;
import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class Usercontroller {

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        return "index";
    }

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

    @GetMapping ("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        User user = new User(email,password);
        boolean succesfulLogin = userRepository.login(user);


        if (succesfulLogin) {
            session.setAttribute("currentUser", user);
            return "redirect:profile";
        } else {
            return "redirect:login";
        }
    }



    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login?logout";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        return "profile"; // your profile page name
    }

    @GetMapping ("/mywishlist")
    public String getMyWishlist(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        } else{
            return "redirect:/mywishlist";
        }
    }
}
