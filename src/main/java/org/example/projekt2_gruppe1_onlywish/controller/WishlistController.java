package org.example.projekt2_gruppe1_onlywish.controller;


import org.example.projekt2_gruppe1_onlywish.model.User;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.example.projekt2_gruppe1_onlywish.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlistController {

@Autowired
WishlistRepository wishlistRepository;


//Bruges til at gå til html siden til at createWishlist
@GetMapping("/getCreateWishlist")
    public String getCreateWishlist() {
    return "createWishlist";
}

@PostMapping("/saveCreateWishlist")
    public String saveCreateWishlist(@RequestParam ("userid") int userid,
                                     @RequestParam ("name") String name,
                                     @RequestParam ("required = false") String description) {
    Wishlist wishlist = new Wishlist( userid, name, description);

    wishlistRepository.saveWishlist(wishlist);
    return "redirect:/";

}

@GetMapping("/getUpdateWishlist")
    public String getUpdateWishlist(@RequestParam("name") String name, int userId, Model model) {
    Wishlist wishlist = wishlistRepository.getWishlistbyName(name, userId);
    model.addAttribute("wishlist", wishlist);
    return "updateWishlist";
}


}
