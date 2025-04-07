package org.example.projekt2_gruppe1_onlywish.model;

import java.math.BigDecimal;

public class Wish {
    int id;
    String name;
    Wishlist wishlist;
    BigDecimal price;
    String description;
    String imageUrl;
    String productlink;


    public Wish(int id, String name, Wishlist wishlist, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.wishlist = wishlist;
        this.description = description;
        this.price = price;
    }

    public Wish() {

    }

    public Wish(String name, BigDecimal price, Wishlist wishlist, String description, String imageUrl, String productlink) {
        this.name = name;
        this.price = price;
        this.wishlist = wishlist;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productlink = productlink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getUrl() {
        return imageUrl;
    }

    public void addAttribute(Wish wish) {
    }
}
