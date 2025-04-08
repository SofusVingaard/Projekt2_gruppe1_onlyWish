package org.example.projekt2_gruppe1_onlywish.controller;

import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.example.projekt2_gruppe1_onlywish.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "redirect:/";
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
        return "redirect:/wishlist";
    }

    @GetMapping("/getUpdateWish")
    public String updateWish(@RequestParam("id")int id){
        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);
        return "redirect:/wishlist";
    }

    @PostMapping("/saveUpdateWish")
    public String postSaveUpdate(
            @RequestParam("ID") int id,
            @RequestParam("Name") String name,
            @RequestParam("Wishlist") Wishlist wishlist,
            @RequestParam("Price") BigDecimal price,
            @RequestParam("Description") String description){

        Wish wish = new Wish(id, name, wishlist, description, price);
        return "redirect:/wishlist";
    }
/*
    @GetMapping("/showWish")
    public String showWIsh(@RequestParam("ID")int id, String name){

        Wish wish = wishRepo.getAllWish(id);
        wish.addAttribute(wish);

        return "showWish";
    }
*/
    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam("id")int id){

        wishRepo.delete(id);

        return "redirect:/wishlist";
    }

    @PostMapping("/reserveWish")
    public String reserveWish(@RequestParam("wishId") int wishId) {
        wishRepo.reserveWish(wishId);
        return "redirect:/wishlist";
    }
    @PostMapping("/unReserveWish")
    public String toggleReservation(@RequestParam("wishId") int wishId) {
        wishRepo.unreserveWish(wishId);
        return "redirect:/wishlist" + wishId;
    }

    @PostMapping("/contribute")
    public String contributeToWish(@RequestParam("wishId") int wishId,
                                   @RequestParam("userId") int userId,
                                   @RequestParam("amount") BigDecimal amount) {

        wishRepo.addContribution(wishId, userId, amount);
        return "redirect:/wishlist" + wishId;
    }
    @GetMapping("/showWish")
    public String showWish(@RequestParam("ID") int id, Model model) {
        Wish wish = wishRepo.getWishById(id);
        BigDecimal collected = wishRepo.getTotalContributions(id);
        BigDecimal missing = wishRepo.getAmountMissing(id);

        model.addAttribute("wish", wish);
        model.addAttribute("collected", collected);
        model.addAttribute("missing", missing);

        return "redirect:/wishlist";
    }
}
