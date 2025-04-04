package org.example.projekt2_gruppe1_onlywish.model;

import java.math.BigDecimal;

public class Wish {
    int id;
    String name;
    Wishlist wishlist;
    String description;
    BigDecimal price;
    String url;
    String image;

    public Wish(int id, String name, Wishlist wishlist, String description, BigDecimal price, String url, String image) {
        this.id = id;
        this.name = name;
        this.wishlist = wishlist;
        this.description = description;
        this.price = price;
        this.url = url;
        this.image = image;
    }

    public Wish(String name, Wishlist wishlist, String description, BigDecimal price) {
        this.name = name;
        this.wishlist = wishlist;
        this.description = description;
        this.price = price;
    }

    public Wish(int id, String name, Wishlist wishlist, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.wishlist = wishlist;
        this.description = description;
        this.price = price;
    }

    public Wish() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return url;
    }

    public String getImage() {
        return image;
    }

    public void addAttribute(Wish wish) {
    }
}
