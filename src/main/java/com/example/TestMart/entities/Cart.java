package com.example.TestMart.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart implements Comparator<Cart>{
        //could use product as composition here
    private int id;
    private List<Product> products;
    private double total;
    private double discountedTotal;
    private int userid;
    private int totalProducts;
    private int totalQuantity;



    @Override
    public int compare(Cart o1, Cart o2) {
        return (int) (o1.getTotal()-o2.getTotal());
    }
}
