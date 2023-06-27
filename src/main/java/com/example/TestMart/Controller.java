package com.example.TestMart;

import com.example.TestMart.entities.Cart;
import com.example.TestMart.entities.Product;
import com.example.TestMart.exceptions.CartNotFoundException;
import com.example.TestMart.exceptions.UserNotFoundException;
import com.example.TestMart.service.CartServiceImpl;
import com.example.TestMart.exceptions.ProductNotAvailableException;
import com.example.TestMart.service.ProductServiceImpl;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    private CartServiceImpl cartService;


@GetMapping("/getprodcutsbyworstrating/{rating}")

public ResponseEntity<List<Product>> getProductTitlesByWorseRating(@PathVariable double rating) throws ProductNotAvailableException
    {

            List<Product> productTitlesByWorseRating = productService.getProductTitlesByWorseRating(rating);
            return new ResponseEntity<>(productTitlesByWorseRating,HttpStatus.OK);


    }

    @GetMapping("/getproductbyid/{id}")

    public ResponseEntity<Product>getProductTitlesByWorseRating(@PathVariable int id) throws ProductNotAvailableException, HttpClientErrorException, JSONException {
try {
    Product product = productService.getProduct(id);
   return new ResponseEntity<>(product, HttpStatus.OK);

}
catch (HttpClientErrorException e)
{
    JSONObject response=new JSONObject();
    response.put("message",e.getMessage());
    return new ResponseEntity(response.toString(),HttpStatus.NOT_FOUND);

}


    }


    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }


    @GetMapping("/getCartWithHighestTotal")
    public ResponseEntity<Cart> getCartWithHighestTotal()
    {
        Cart cartWithHighestTotal = this.cartService.getCartWithHighestTotal();
        return new ResponseEntity<>(cartWithHighestTotal,HttpStatus.OK);

    }

    @GetMapping("/getCartWithLowestTotal")
    public ResponseEntity<Cart> getCartWithLowestTotal()
    {
        Cart cartWithLowestTotal = this.cartService.getCartWithLowestTotal();
        return new ResponseEntity<>(cartWithLowestTotal,HttpStatus.OK);

    }

    @SneakyThrows
    @GetMapping("/addProductImagesToUserCart/{userId}")
    public ResponseEntity<List<Product>> getCartWithLowestTotal(@PathVariable int userId) throws CartNotFoundException, UserNotFoundException, HttpClientErrorException
    {
        try {
            List<Product> ProductsWithEnrichedInformation = this.cartService.getProductsWithEnrichedInformation(userId);
            return new ResponseEntity<>(ProductsWithEnrichedInformation, HttpStatus.OK);
        }
        catch (HttpClientErrorException e)
        {
            JSONObject response=new JSONObject();
            response.put("message",e.getMessage());
            return new ResponseEntity(response.toString(),HttpStatus.NOT_FOUND);

        }
    }

}
