package org.example.projekt2_gruppe1_onlywish.controller;

import jakarta.servlet.http.HttpSession;
import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.example.projekt2_gruppe1_onlywish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/wish")
public class WishController {

    @Autowired
    WishRepository wishRepo;

    //@Autowired
    //WishService wishService;

    @GetMapping("/getcreatewish")
    public String createWish(){
        return "createWish";
    }

    @PostMapping("/saveCreateWish")
    public String postCreateWish(
        @RequestParam("name") String name,
        @RequestParam("wishlistId") int wishlistId,
        @RequestParam(required = false ) BigDecimal price,
        @RequestParam(required = false) String description,
        @RequestParam (required = false) String imageUrl,
        @RequestParam (required = false) String productlink,
        HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }


        Wish wish = new Wish(name, price, wishlistId, description, imageUrl, productlink);
        wishRepo.save(wish);
        return "redirect:/wishlist/showWishlist?wishlistId=" + wishlistId;    }

    @GetMapping("/getUpdateWish")
    public String updateWish(@RequestParam("id")int id){
        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);
        return "updateWish";
    }

    @PostMapping("/saveUpdateWish")
    public String postSaveUpdate(
            @RequestParam("ID") int id,
            @RequestParam("Name") String name,
            @RequestParam("Wishlist") int wishlist,
            @RequestParam("Price") BigDecimal price,
            @RequestParam("Description") String description){

        Wish wish = new Wish(id, name, wishlist, description, price);
        return "redirect/";
    }

    @GetMapping("/showWish")
    public String showWIsh(@RequestParam("ID")int id, String name){

        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);

        return "showWish";
    }

    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam("id")int id){

        wishRepo.delete(id);

        return "redirect:/wishlist/showWishlist?wishlistId=" + id;
    }

    @PostMapping("/reserveWish")
    public String reserveWish(@RequestParam("wishId") int wishId) {
        wishRepo.reserveWish(wishId);
        return "redirect:/wishlist";
    }
    @PostMapping("/unReserveWish")
    public String toggleReservation(@RequestParam("wishId") int wishId) {
        wishRepo.unreserveWish(wishId);
        return "redirect:/wish/showWish?id=" + wishId;
    }

}
