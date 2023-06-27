package com.example.TestMart.entities;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> users=new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
