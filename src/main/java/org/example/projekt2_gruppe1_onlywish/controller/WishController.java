package org.example.projekt2_gruppe1_onlywish.controller;

import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.example.projekt2_gruppe1_onlywish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class WishController {

    @Autowired
    WishRepository wishRepo;

    //@Autowired
    //WishService wishService;

    @GetMapping("/getCreateWish")
    public String createWish(){
        return "createWish";
    }

    @PostMapping("/saveCreateWish")
    public String postCreateWish(
        @RequestParam("Name") String name,
        @RequestParam("Wishlist") Wishlist wishlist,
        @RequestParam("Price") BigDecimal price,
        @RequestParam("Description") String description,
        @RequestParam ("Imageurl") String imageUrl,
        @RequestParam ("Productlink") String productlink){

        Wish wish = new Wish(name, price, wishlist, description, imageUrl, productlink);
        wishRepo.save(wish);
        return "redirect:/";
    }

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
            @RequestParam("Wishlist") Wishlist wishlist,
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

        return "deleteWish";
    }

    @PostMapping("/reserveWish")
    public String reserveWish(@RequestParam("wishId") int wishId) {
        wishRepo.reserveWish(wishId);
        return "redirect/";
    }
    @PostMapping("/unReserveWish")
    public String toggleReservation(@RequestParam("wishId") int wishId) {
        wishRepo.unreserveWish(wishId);
        return "redirect:/wish/showWish?id=" + wishId;
    }

}
