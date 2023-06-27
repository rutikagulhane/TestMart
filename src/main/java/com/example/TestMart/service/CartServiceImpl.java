package com.example.TestMart.service;

import com.example.TestMart.contract.CartService;
import com.example.TestMart.entities.Cart;
import com.example.TestMart.entities.Carts;
import com.example.TestMart.entities.Product;
import com.example.TestMart.entities.User;
import com.example.TestMart.exceptions.CartNotFoundException;
import com.example.TestMart.exceptions.UserNotFoundException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private String Base_URL="https://dummyjson.com/carts";
    Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    RestTemplate template;

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;

    public CartServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<Cart> getAllCarts() {
        Carts carts = template.getForObject(Base_URL, Carts.class);
        logger.info("Getting ALl Carts ");
        assert carts != null;
        List<Cart> cartList = carts.getCarts();
        cartList.sort(new CartComparator());
        logger.info("List of carts "+cartList);
        return cartList;
    }

    @Override
    public Object getCart(Integer cartId) {
        return null;
    }

    @Override
    public List getUserCarts(Integer userId) {
        return null;
    }

    public Cart getCartWithHighestTotal() {

        List<Cart> allCarts = this.getAllCarts();
        logger.info("Cart with highest total"+allCarts.get(allCarts.size()-1));
        return allCarts.get(allCarts.size()-1);


    }

    public Cart getCartWithLowestTotal() {
        List<Cart> allCarts = this.getAllCarts();
        logger.info("Cart with lowest total"+allCarts.get(0));
        return allCarts.get(0);

    }

    public List<Product> getProductsWithEnrichedInformation(Integer userId) {

        List<Cart> carts = this.getallCartsforUser(userId);
        logger.info("Number of Carts for user "+userId +" is: "+carts.size());

        // 2 checks required 1)if user is present 2)if ait has any carts
        User user = userService.getUser(userId);
        if(user!=null){
        if(carts.size()>0) {
            logger.info("First Cart for "+userId +" is: "+carts.get(0));
            this.enrichtheProducts(carts.get(0));
            return carts.get(0).getProducts();
        }
        else {
            throw  new CartNotFoundException("No Carts for User Id::"+userId);
        }}
        else throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

    }

    private synchronized Cart enrichtheProducts(Cart cart) {

        List<Product> originalProducts = cart.getProducts();
        System.out.println("ORIGINAL PRODUCTS"+originalProducts);
        System.out.println(originalProducts.size());
        logger.info("Original Cart "+cart);

        int productsInCart=cart.getTotalProducts();
        for(int i=0;i<productsInCart;i++) {
            List<String> images = productService.getProduct(originalProducts.get(i).getId()).getImages();
           // System.out.println("IMAGES" + images);


            originalProducts.get(i).setImages(images);
            cart.setProducts(originalProducts);
        }
        System.out.println("UPDATED PRODUCTS"+originalProducts);
        logger.info("Enriched Cart "+cart);
        return cart;

    }

    private List<Cart> getallCartsforUser(Integer userId) {
        Carts carts = template.getForObject(Base_URL+"/user/{userId}", Carts.class,userId);

        if(carts!=null) {
            List<Cart> cartList = carts.getCarts();
            cartList.sort(new CartComparator());
            return cartList;
        }
        else throw new CartNotFoundException("No Carts found for user :"+userId);

    }
}
