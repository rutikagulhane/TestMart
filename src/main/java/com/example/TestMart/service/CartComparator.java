package com.example.TestMart.service;

import com.example.TestMart.entities.Cart;

import java.util.Comparator;

public class CartComparator implements Comparator<Cart> {
    @Override
    public int compare(Cart o1, Cart o2) {
        return (int) (o1.getTotal()- o2.getTotal());
    }
}
