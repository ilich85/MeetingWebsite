package com.ilich.service;

import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserService userService;
    private User user;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
    }

    @Test
    public void registerIfUserNotExists() throws Exception {
        user = new User();
        user.setUsername("user11");
        user.setPassword("password");
        String expectResult = "success";
        String actualResult = userService.register(user);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void registerIfUserExists() throws Exception {
        user = new User();
        user.setUsername("user12");
        user.setPassword("pass");
        userService.register(user);
        String expectResult = "user exists";
        String actualResult = userService.register(user);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void checkUserIfUserNotExists() throws Exception {
        user = new User();
        user.setUsername("user13");
        user.setPassword("pass");
        String expectResult = "user not found";
        String actualResult = userService.checkUser(user);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void checkUserIfPasswordCorrect() throws Exception {
        user = new User();
        user.setUsername("user14");
        user.setPassword("password");
        userService.register(user);
        String expectResult = String.valueOf(user.getId());
        String actualResult = userService.checkUser(user);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void checkUserIfPasswordNotCorrect() throws Exception {
        user = new User();
        user.setUsername("user15");
        user.setPassword("password");
        userService.register(user);
        user.setPassword("pass");
        String expectResult = "wrong password";
        String actualResult = userService.checkUser(user);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void getPasswordById() throws Exception {
        user = new User();
        user.setUsername("user16");
        user.setPassword("password");
        userService.register(user);
        String expectResult = "password";
        String actualResult = userService.getPasswordById(user.getId());
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void changePassword() throws Exception {
        user = new User();
        user.setUsername("user17");
        user.setPassword("password");
        userService.register(user);
        String expectResult = "success";
        String actualResult = userService.changePassword("pass", user.getId());
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void removeAccount() throws Exception {
        user = new User();
        user.setUsername("user18");
        user.setPassword("password");
        userService.register(user);
        String expectResult = "success";
        String actualResult = userService.removeAccount(user.getId());
        assertEquals(expectResult, actualResult);
    }
}