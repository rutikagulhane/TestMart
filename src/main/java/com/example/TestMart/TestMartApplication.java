package com.example.TestMart;

import com.example.TestMart.app.AbstractTestMartAppFeatures;
import com.example.TestMart.service.CartServiceImpl;
import com.example.TestMart.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TestMartApplication {


	public static void main(String[] args) {

		SpringApplication.run(TestMartApplication.class, args);
		RestTemplate template=new RestTemplate();
		ProductServiceImpl service=new ProductServiceImpl(template);
		AbstractTestMartAppFeatures features=new AbstractTestMartAppFeatures(service);
		//testing getProductTitlesByWorseRating
		System.out.println("ProductTitlesByWorseRating::::");
		features.getProductTitlesByWorseRating(4);
		System.out.println("PRODUCT BY ID ::: "+features.getProductbyId(4));
		//testing getCartWithHighestTotal
		CartServiceImpl cartService=new CartServiceImpl(template);
		AbstractTestMartAppFeatures cartfeature=new AbstractTestMartAppFeatures(cartService);
		System.out.println("HIGHEST ::: "+cartfeature.getCartWithHighestTotal());

		//testing getCartWithLOWESTtTotal

		System.out.println("LOWEST ::: "+cartfeature.getCartWithLowestTotal());

	}
		@Bean
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}


}
