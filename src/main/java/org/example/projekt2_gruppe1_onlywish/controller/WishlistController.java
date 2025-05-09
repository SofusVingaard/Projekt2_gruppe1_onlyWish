package org.example.projekt2_gruppe1_onlywish.controller;


import jakarta.servlet.http.HttpSession;
import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.example.projekt2_gruppe1_onlywish.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistRepository wishlistRepository;



    @GetMapping("/create")
    public String showCreateForm(HttpSession session) {
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/users/login";
        }
        return "wishlist";
    }

    @PostMapping("/create")
    public String createWishlist(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            HttpSession session) {

            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null) {
            return "redirect:/login";
            }
            Wishlist wishlist = new Wishlist(currentUser.getId(), name, description);
            wishlistRepository.saveWishlist(wishlist);
            return "redirect:/wishlist/my-wishlists";
    }

    @GetMapping("/my-wishlists")
    public String showUserWishlists(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/users/login";
        }
        List<Wishlist> wishlists = wishlistRepository.getWishlistsByUserId(currentUser.getId());
        model.addAttribute("wishlists", wishlists);
        return "wishlist";
    }


@GetMapping("/showWishlist")
    public String showWishlist(@RequestParam("name") String name,
                               @RequestParam("userid") int userId, Model model) {
    Wishlist wishlist = wishlistRepository.getWishlistbyName(name, userId);
    model.addAttribute(wishlist);
    return "wishlist";

}

    @PostMapping("/deleteWishlist")
    public String deleteWishlist(@RequestParam("id") int id, HttpSession session) {
        Object currentUserId =  session.getAttribute("currentUser");
        if (currentUserId != null) {
            wishlistRepository.deleteWishlist(id);
        }

    return "redirect:/wishlist/my-wishlists";
    }

    @GetMapping("/createwish")
    public String showCreateWishForm(@RequestParam("wishlistId") int wishlistId, Model model) {
        Wishlist wishlist = wishlistRepository.getWishlistById(wishlistId);
        if (wishlist == null) {
            return "redirect:/wishlist/my-wishlists";
        }
        model.addAttribute("wishlist", wishlist);
        return "createWish";
    }


}
