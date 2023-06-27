package com.example.TestMart.services;

import com.example.TestMart.entities.Product;
import com.example.TestMart.entities.Products;
import com.example.TestMart.exceptions.ProductNotAvailableException;
import com.example.TestMart.service.ProductServiceImpl;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestProductServiceImpl {

   @Mock
   private RestTemplate restTemplate=Mockito.mock(RestTemplate.class);

    @InjectMocks
    private ProductServiceImpl productService=new ProductServiceImpl(restTemplate);

    @Mock
    private RestTemplate template;

    @InjectMocks
    ProductServiceImpl productService1;
    @Mock
    Product product=new Product();
    @Mock
    Products products=new Products();
    @BeforeEach
    public void init() {
        template = new RestTemplate();
        productService1 = new ProductServiceImpl(template);
    }
    @Test
    public void testGetAllProducts()
    {
       //int sizeMock =30;
      int sizeMock=30;
        template=new RestTemplate();
        ProductServiceImpl productService1=new ProductServiceImpl(template);
        int size1 = productService1.getAllProducts().size();
        Assert.assertEquals(sizeMock,size1);
    }

    @Test
    public void TestgetProductbyId()
    {
        //Mocked data -ARRANGE
        List<String> images=new ArrayList<>();
        images.add("https://i.dummyjson.com/data/products/1/1.jpg");
        images.add("https://i.dummyjson.com/data/products/1/2.jpg");
        images.add("https://i.dummyjson.com/data/products/1/3.jpg");
        images.add("https://i.dummyjson.com/data/products/1/4.jpg");
        images.add("https://i.dummyjson.com/data/products/1/thumbnail.jpg");

        Product product=new Product(1,"iPhone 9","An apple mobile which is nothing like apple",
                549,12.96,4.69,94,"Apple",
                "smartphones","https://i.dummyjson.com/data/products/1/thumbnail.jpg",
                images);

    Mockito.when(restTemplate.getForObject("https://dummyjson.com/products/{productId}", Product.class, 1))
            .thenReturn(product);
    System.out.println("MOCK PRODD"+product);
    template=new RestTemplate();
    productService1=new ProductServiceImpl(template);
    //ACT
    Product actual = productService1.getProduct(1);
    System.out.println("Actual Prod"+actual);
    //verify
    Assert.assertEquals(product,actual);
    }

    @Test
    public void TestgetProductbyIdError()
    {
        productService=new ProductServiceImpl(restTemplate);
        Throwable exception = Assert.assertThrows(HttpClientErrorException.class, () -> productService.getProduct(11111));

      Assert.assertEquals("404 NOT_FOUND",exception.getMessage());

    }

    @Test
    public void TestgetProductTitlesByWorseRating()
    {
        //Arrange
        List<Product> product=List.of(new Product(12,
                "Brown Perfume","Royal_Mirage Sport Brown Perfume for Men & Women - 120ml",
                40.0,15.66,4.0,52.0,"Royal_Mirage",
                "fragrances","https://i.dummyjson.com/data/products/12/thumbnail.jpg",
                List.of("https://i.dummyjson.com/data/products/12/1.jpg",
                        "https://i.dummyjson.com/data/products/12/2.jpg",
                        "https://i.dummyjson.com/data/products/12/3.png",
                        "https://i.dummyjson.com/data/products/12/4.jpg",
                        "https://i.dummyjson.com/data/products/12/thumbnail.jpg")));
       products.setProducts(product);
       Mockito.when(restTemplate.getForObject("https://dummyjson.com/products",Products.class))
                .thenReturn(products);
       System.out.println("PRODUCTSSSSS"+products);


        //act
        template=new RestTemplate();
        productService1=new ProductServiceImpl(template);
        List<Product> productTitlesByWorseRating = productService1.getProductTitlesByWorseRating(4);
        System.out.println("productTitlesByWorseRating"+productTitlesByWorseRating);
        //Assert
        Assert.assertEquals(product,productTitlesByWorseRating);



    }

    @Test
    public void TestgetProductTitlesByWorseRating_whenNotFound()
    {
        //Arrange
        List<Product> product=List.of(new Product(12,
                "Brown Perfume","Royal_Mirage Sport Brown Perfume for Men & Women - 120ml",
                40.0,15.66,4.0,52.0,"Royal_Mirage",
                "fragrances","https://i.dummyjson.com/data/products/12/thumbnail.jpg",
                List.of("https://i.dummyjson.com/data/products/12/1.jpg",
                        "https://i.dummyjson.com/data/products/12/2.jpg",
                        "https://i.dummyjson.com/data/products/12/3.png",
                        "https://i.dummyjson.com/data/products/12/4.jpg",
                        "https://i.dummyjson.com/data/products/12/thumbnail.jpg")));
        products.setProducts(product);
        Mockito.when(restTemplate.getForObject("https://dummyjson.com/products",Products.class))
                .thenReturn(products);
        System.out.println("PRODUCTSSSSS"+products);

        template=new RestTemplate();
        productService=new ProductServiceImpl(restTemplate);
        ProductNotAvailableException exception = Assert.assertThrows(ProductNotAvailableException.class,()->productService.getProductTitlesByWorseRating(3));
        //act

        Assert.assertEquals("No Product with rating less than rating3.0 found",exception.getMessage());

    }
}
