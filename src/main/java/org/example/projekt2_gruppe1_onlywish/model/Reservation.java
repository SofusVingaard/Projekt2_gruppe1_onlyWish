package org.example.projekt2_gruppe1_onlywish.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Reservation {
    int id;
    Wish wish;
    User reservedBy;
    LocalDateTime reservedAt;
}
