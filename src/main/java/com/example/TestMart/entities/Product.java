package com.example.TestMart.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable {

    private int id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    private double price;
    private double discountPercentage;
    private double rating;
    private double stock;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnail;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> images;



}
