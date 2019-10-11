package com.example.springbootmonolith.controller;


import com.example.springbootmonolith.Config.JwtUtil;
import com.example.springbootmonolith.Controller.UserController;
import com.example.springbootmonolith.Service.UserService;
import com.example.springbootmonolith.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    /**Main entry point for server-side Spring MVC test support.**/
    //inputting mock MVC for UserController testing//
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private User user;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    //testing helloworld method//
    public void helloWorld_ReturnsString_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!!"));
    }

    @Test
    public void login_Success() throws Exception{
        //testing log-in success: currently fails//
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("Ted","password"));

        when(userService.login(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void signUp_Success() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("Ted","password"));

        when(userService.createUser(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteUser_Success() throws Exception{

    }

    private static String createUserInJson (String name, String password) {
        return "{ \"name\": \"" + name + "\", " +
                "\"password\":\"" + password + "\"}";
    }
}
