package org.example.projekt2_gruppe1_onlywish.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    int id;
    String name;
    int age;
    String email;
    String password;
    List<Wishlist> wishlists;
    String image;

    //Constructors
    public User(int id, String name, int age, String email, String password, String image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        wishlists = new ArrayList<>();
        this.image=image;
    }
    public User(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.wishlists = wishlists;
        image=null;
    }
    public User(int id,String name, int age, String email, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.wishlists = wishlists;
        image=null;
    }
    public User(){
    }

    //Getters and setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}
