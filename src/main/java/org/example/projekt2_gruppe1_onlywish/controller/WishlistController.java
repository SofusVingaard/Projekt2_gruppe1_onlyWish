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



@GetMapping("/getCreateWishlist")
    public String getCreateWishlist() {
    return "createWishlist";
}

@PostMapping("/saveCreateWishlist")
    public String saveCreateWishlist(@RequestParam ("userid") int userId,
                                     @RequestParam ("name") String name,
                                     @RequestParam ( value = "description", required = false) String description) {
    Wishlist wishlist = new Wishlist( userId, name, description);

    wishlistRepository.saveWishlist(wishlist);
    return "redirect:/";

}

@GetMapping("/getUpdateWishlist")
    public String getUpdateWishlist(@RequestParam("name") String name,
                                    @RequestParam("userid") int userId, Model model) {
    Wishlist wishlist = wishlistRepository.getWishlistbyName(name, userId);
    model.addAttribute("wishlist", wishlist);
    return "updateWishlist";
}

@GetMapping("/showWishlist")
    public String showWishlist(@RequestParam("name") String name,
                               @RequestParam("userid") int userId, Model model) {
    Wishlist wishlist = wishlistRepository.getWishlistbyName(name, userId);
    model.addAttribute(wishlist);
return "wishlist";

}

@PostMapping("/deleteWishlist")
    public String deleteWishlist(@RequestParam("name") String name,
                                 @RequestParam("userid") int userId) {
    wishlistRepository.deleteWishlist(name, userId);

    return "redirect:/";
}

}
