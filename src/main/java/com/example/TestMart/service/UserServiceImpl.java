package com.example.TestMart.service;

import com.example.TestMart.contract.UserService;
import com.example.TestMart.entities.Product;
import com.example.TestMart.entities.Products;
import com.example.TestMart.entities.User;
import com.example.TestMart.entities.Users;
import com.example.TestMart.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private String BASE_URL="https://dummyjson.com/users";
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RestTemplate template;

    public UserServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List getAllUsers() {

        Users users = template.getForObject(BASE_URL, Users.class);
        List<User> userList = users.getUsers();
        logger.info("retrieved list of Users "+userList);
        System.out.println(userList);
        return userList;

    }

    @Override
    public User getUser(Integer userId) throws HttpClientErrorException {

                User user = template.getForObject(BASE_URL + "/{userId}", User.class, userId);
                return user;


    }

    @Override
    public List searchUsers(String query) {
        return null;
    }
}
