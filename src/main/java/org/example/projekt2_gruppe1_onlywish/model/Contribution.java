package org.example.projekt2_gruppe1_onlywish.model;

import java.math.BigDecimal;

public class Contribution {

    int id;
    int wishId;
    int userId;
    BigDecimal amount;

    public Contribution() {}

    public Contribution(int wishId, int userId, BigDecimal amount) {
        this.wishId = wishId;
        this.userId = userId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
