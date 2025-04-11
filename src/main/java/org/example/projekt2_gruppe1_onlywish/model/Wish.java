package org.example.projekt2_gruppe1_onlywish.model;

import java.math.BigDecimal;

public class Wish {
    int id;
    String name;
    int wishlistId;
    BigDecimal price;
    String description;
    String imageUrl;
    String productlink;


    public Wish(int id, String name, int wishlistId, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.wishlistId = wishlistId;
        this.description = description;
        this.price = price;
    }

    public Wish() {

    }

    public Wish(String name, BigDecimal price, int wishlistId, String description, String imageUrl, String productlink) {
        this.name = name;
        this.price = price;
        this.wishlistId = wishlistId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.productlink = productlink;
    }

    public String getProductlink() {
        return productlink;
    }

    public void setProductlink(String productlink) {
        this.productlink = productlink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addAttribute(Wish wish) {
    }

    public int getWishlistId() {
        return wishlistId;
    }
}
