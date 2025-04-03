package org.example.projekt2_gruppe1_onlywish.config;


import org.example.projekt2_gruppe1_onlywish.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitData {

    private ArrayList<User> userList= new ArrayList<>();

    public InitData() {

        // Populate the list with dummy data
        userList.add(new User(1, "Karla", 23, "CookedUser@gmail.com", "Testing123"));
        userList.add(new User(2, "Charlie", 33, "CharlieBrown@gmail.com", "Snoopy6969"));


public ArrayList<User> getUserList(){
        return userList;
    }
    }


}
