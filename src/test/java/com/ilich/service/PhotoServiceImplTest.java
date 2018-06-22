package com.ilich.service;

import com.ilich.model.Photo;
import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class PhotoServiceImplTest {
    private UserService userService;
    private PhotoService photoService;
    private byte[] image;
    private Photo photo;
    private User user;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        photoService = new PhotoServiceImpl();
        image = Files.readAllBytes(new File("images/1.jpg").toPath());
    }

    @Test
    public void addPhoto() throws Exception {
        user = new User();
        user.setUsername("photoAdd");
        user.setPassword("pass");
        userService.register(user);
        int id = user.getId();
        photo = new Photo();
        photo.setUserId(id);
        photo.setPhoto(image);
        String expectResult = "success";
        String actualResult = photoService.addPhoto(photo);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void removePhoto() throws Exception {
        user = new User();
        user.setUsername("photoRemove");
        user.setPassword("password");
        userService.register(user);
        int id = user.getId();
        photo = new Photo();
        photo.setUserId(id);
        photo.setPhoto(image);
        photoService.addPhoto(photo);
        String expectResult = "success";
        String actualResult = photoService.removePhoto(photo.getId());
        assertEquals(expectResult, actualResult);
    }
}
