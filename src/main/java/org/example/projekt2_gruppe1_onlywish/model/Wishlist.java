package org.example.projekt2_gruppe1_onlywish.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    int id;
    int userId;
    String name;
    String shareLink;
    String description;
    List<Wish> wishes;

    public Wishlist( int userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
    }



    public Wishlist(int id, int userId, String name, String shareLink) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.shareLink = shareLink;
    }

    public Wishlist() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
