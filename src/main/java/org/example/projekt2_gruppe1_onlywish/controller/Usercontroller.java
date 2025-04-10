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
        return "redirect:/users/createuser";  // Corrected path
    }


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/createuser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }


    @PostMapping("/createuser")
    public String createUser
            (@RequestParam("name")String name,
             @RequestParam ("age") int age,
             @RequestParam ("email") String email,
             @RequestParam ("password") String password) {
        User user = new User(name,age,email,password);
        userRepository.createUser(user);
        return "redirect:/users/profile";
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

    /*@PostMapping("/login")
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
    }*/
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        // Get the FULL user from database
        User user = userRepository.findByEmail(email); // You'll need to implement this

        if (user != null && user.getPassword().equals(password)) {
            // Store the complete user object in session
            session.setAttribute("currentUser", user);
            return "redirect:/users/profile";
        } else {
            return "redirect:/users/login?error";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/users/login";  // Redirect to login if no user is found
        }
        model.addAttribute("user", user);
        return "profile";  // Render the profile page
    }


    @GetMapping ("/mywishlist")
    public String getMyWishlist(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/users/login";
        } else{
            return "redirect:/wishlist";
        }
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userRepository.updateUser(user);
        return "redirect:/users/profile";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        User user = userRepository.getUserbyId(id);
        userRepository.deleteUser(id);
        return "redirect:/index";
    }

    @GetMapping("/profileheader")
    public String getProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("user", user);
        if(user == null) {
            return "redirect:/users/login";
        }
        return "profile";
    }
}
