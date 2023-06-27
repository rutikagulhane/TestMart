package com.example.TestMart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private int height;
    private double weight;
    private String eyeColor;



}
