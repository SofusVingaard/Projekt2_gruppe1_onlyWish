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
            (HttpSession session,
             @RequestParam("name")String name,
             @RequestParam ("age") int age,
             @RequestParam ("email") String email,
             @RequestParam ("password") String password,
             RedirectAttributes redirectAttributes) {

        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "E-mailen er allerede i brug.");
            return "redirect:/users/createuser";
        }

        User user = new User(name,age,email,password);
        userRepository.createUser(user);

        User createdUser = userRepository.findByEmail(email);

        if (createdUser != null) {
            session.setAttribute("currentUser", createdUser);
        }
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
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = userRepository.findByEmail(email); // You'll need to implement this

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("currentUser", user);
            return "redirect:/users/profile";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Email eller kode er forkert/bruger eksistere ikke! >:(");
            return "redirect:/users/login";
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
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        return "profile";
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
