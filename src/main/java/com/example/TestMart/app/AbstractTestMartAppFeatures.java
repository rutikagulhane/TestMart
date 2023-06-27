package com.example.TestMart.app;

import com.example.TestMart.entities.Cart;
import com.example.TestMart.entities.Product;
import com.example.TestMart.service.CartServiceImpl;
import com.example.TestMart.service.ProductServiceImpl;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

// Note: Convert this class to concrete class and add implementation (missing body) to all methods. You will remove the word
// `abstract` everywhere. This class is only kept `abstract` for the sake of interview exercise.
@Service
@NoArgsConstructor
public class AbstractTestMartAppFeatures {


    @Autowired
    ProductServiceImpl service;
    @Autowired
    CartServiceImpl cartService;

    public AbstractTestMartAppFeatures(ProductServiceImpl service) {
        this.service = service;
    }

    public AbstractTestMartAppFeatures(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    /**
     * Prints the titles of all products that have a rating less than or equal to the provided criteria.
     * @param rating The rating threshold.
     */
    public void getProductTitlesByWorseRating(double rating){
        System.out.println(service.getProductTitlesByWorseRating(rating));
    }

    /**
     * Returns the cart with the highest total value.
     * @returns The cart with the highest total value.
     */
    public Cart getCartWithHighestTotal(){
     return cartService.getCartWithHighestTotal();
    }

    /**
     * Returns the cart with the lowest total value.
     * @returns The cart with the lowest total value.
     */
    public  Cart getCartWithLowestTotal()
    {
        return cartService.getCartWithLowestTotal();
    }

    /**
     * Enriches the product information in a user's cart by adding product images.
     * The current product information in a cart has limited fields.
     * This method adds the `images` field for each product in a given user's cart.
     * Note: This method only applies to the first element from the `carts[]` JSON response.
     * @param userId The ID of the user whose cart's product information will be enriched.
     * @returns A list of products with enriched information in the user's cart.
     */
    public  List<Product> addProductImagesToUserCart(Integer userId) throws JSONException {
        return cartService.getProductsWithEnrichedInformation(userId);
    }

    public Product getProductbyId(int i) {
        return service.getProduct(i);
    }
}