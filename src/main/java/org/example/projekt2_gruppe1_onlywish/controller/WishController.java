package org.example.projekt2_gruppe1_onlywish.controller;

import jakarta.servlet.http.HttpSession;
import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.example.projekt2_gruppe1_onlywish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
@RequestMapping("/wish")
public class WishController {

    @Autowired
    WishRepository wishRepo;


    @GetMapping("/getcreatewish")
    public String createWish(@RequestParam(required = false) String wishlistId, Model model) {
        if (wishlistId == null || wishlistId.isBlank()) {
            return "redirect:/wishlist/my-wishlists";
        }

        try {
            int id = Integer.parseInt(wishlistId);
            model.addAttribute("wishlistId", id);
            return "createWish";
        } catch (NumberFormatException e) {
            return "redirect:/wishlist/my-wishlists";
        }
    }


    @PostMapping("/saveCreateWish")
    public String postCreateWish(
            @RequestParam(value = "name") String name,
            @RequestParam("wishlistId") int wishlistId,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String productlink,
            HttpSession session) {
            System.out.println("Received name: " + name);
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null) {
            return "redirect:/login";
            }
            Wish wish = new Wish(name, price, wishlistId, description, imageUrl, productlink);
            wishRepo.save(wish);
            return "redirect:/wish/getcreatewish?wishlistId=" + wishlistId;
    }

    @GetMapping("/getUpdateWish")
        public String updateWish(@RequestParam("id") int id) {
        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);
        return "redirect:/wishlist";
    }

    @PostMapping("/saveUpdateWish")
        public String postSaveUpdate(
            @RequestParam("ID") int id,
            @RequestParam("Name") String name,
            @RequestParam("Wishlist") int wishlist,
            @RequestParam("Price") BigDecimal price,
            @RequestParam("Description") String description) {

            Wish wish = new Wish(id, name, wishlist, description, price);
            return "redirect:/wishlist";
    }

    @GetMapping("/showWish")
    public String showWIsh(@RequestParam("ID") int id) {

        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);

        return "showWish";
    }

    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam("id") int id,
                             @RequestParam("wishlistId") int wishlistId,
                             HttpSession session,
                             Model model) {

        Object currentUserId = session.getAttribute("currentUser");
        if (currentUserId != null) {
            wishRepo.delete(id);
        }

        return "redirect:/wish/wisheslist?wishlistId=" + wishlistId;
    }


    @GetMapping("wisheslist")
    public String getWishesByWishlist(@RequestParam("wishlistId") int wishlistId, Model model) {
        ArrayList<Wish> wishes = wishRepo.findByWishlistId(wishlistId);
        model.addAttribute("wishes", wishes);
        return "seeWishesInList";

    }
}