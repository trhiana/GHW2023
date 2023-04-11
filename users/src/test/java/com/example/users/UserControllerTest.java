package com.example.users;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @Test
//    public void getAllUsers() throws Exception {
//        User user = new User("u043", "Rowena");
//        List<User> allUsers = Arrays.asList(user);
//        when(userService.getAllUsers()).thenReturn(allUsers);
//
//        RequestBuilder requestBuilder  = MockMvcRequestBuilders.get("/")
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse());
//        String expected = "[{id: \"u043\", firstName: \"Rowena\"}]";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//    }

    @Test
    public void getUserByFirstName() throws Exception {
        User user = new User("u054", "Rudiger");

        List<User> allUsers = Arrays.asList(user);
        when(userService.getUserByFirstName("Rudiger")).thenReturn(allUsers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/find/Rudiger")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{firstName: \"Rudiger\", firstName:\"Rudiger\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getUserByFirstNameException() throws Exception {
        String exceptionParam = "Rowena";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/find/exception/{exception_id}", exceptionParam)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals("User doesn't exist", result.getResolvedException()
                        .getMessage()));
    }

    @Test
    @WithAnonymousUser
    public void getAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
    }

}