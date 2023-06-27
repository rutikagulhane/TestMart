package com.example.TestMart.service;

import com.example.TestMart.contract.ProductService;
import com.example.TestMart.entities.Product;
import com.example.TestMart.entities.Products;
import com.example.TestMart.exceptions.ProductNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private String BASE_URL="https://dummyjson.com";
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RestTemplate template;

//    @Override
//    public String toString() {
//        return "ProductServiceImpl{" +
//                "BASE_URL='" + BASE_URL + '\'' +
//                ", template=" + template +
//                ", webclient=" + webclient +
//                '}';
//    }

    public ProductServiceImpl() {
    }
  public ProductServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Autowired
    Webclient webclient;

    @Override
    public List<Product> getAllProducts() {

        Products products = template.getForObject(BASE_URL+"/products", Products.class);
        List<Product> productList = products.getProducts();
        logger.info("retrieved list of Products "+productList);
        System.out.println(productList.size());
        return productList;


    }
    @Override
    public List getAllProducts(int limit, int skip, String... fields) {
        return null;
    }

    @Override
    public Product getProduct(Integer productId) throws HttpClientErrorException{

        Product product = template.getForObject(BASE_URL+"/products/{productId}", Product.class, productId);
        if(product!=null){
        logger.info("retrieved Product "+product);
        System.out.println("PRODUCT BY PRODUCT ID:::"+product);
        return product;}
        else throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

    }

    @Override
    public List searchProducts(String query) {
        return null;
    }

    @Override
    public List getCategories() {
        return null;
    }

    @Override
    public List getProductsByCategory(String categoryName) {
        return null;
    }

    public List<Product> getProductTitlesByWorseRating(double rating) {
        logger.info("Getting Product below rating "+rating);
        List<Product> productList = this.getAllProducts().stream().filter(s -> s.getRating() <= rating).collect(Collectors.toList());
        if(!productList.isEmpty()) {
            return productList;
        }
        else {
          throw new ProductNotAvailableException("No Product with rating less than rating"+rating+" found");
           // throw new RuntimeException("Product Not Found");
        }


    }
}
