package com.ilich.service;

import com.ilich.model.Gender;
import com.ilich.model.Photo;
import com.ilich.model.User;
import com.ilich.model.UserInfo;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class UserInfoServiceImplTest {
    private UserService userService;
    private UserInfoService userInfoService;
    private PhotoService photoService;
    private User user;
    private UserInfo userInfo;


    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        userInfoService = new UserInfoServiceImpl();
        photoService = new PhotoServiceImpl();
    }

    @Test
    public void getInfo() throws Exception {
        user = new User();
        user.setUsername("Igor");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        userInfo = new UserInfo();
        userInfo.setAge(25);
        userInfo.setName("Игорь");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setCity("Донецк");
        userInfo.setUserId(idUser);
        userInfoService.setInfo(userInfo);
        UserInfo actualResult = userInfoService.getInfo(idUser);
        assertEquals(userInfo, actualResult);
    }

    @Test
    public void setInfo() throws Exception {
        user = new User();
        user.setUsername("Ann");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        userInfo = new UserInfo();
        userInfo.setAge(35);
        userInfo.setName("Анна");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setCity("Киев");
        userInfo.setUserId(idUser);
        String expectResult = "success";
        String actualResult = userInfoService.setInfo(userInfo);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void updateInfo() throws Exception {
        user = new User();
        user.setUsername("Oleg");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        userInfo = new UserInfo();
        userInfo.setAge(25);
        userInfo.setName("Олег");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setCity("Полтава");
        userInfo.setUserId(idUser);
        userInfoService.setInfo(userInfo);
        userInfo.setCity("Харьков");
        String expectResult = "success";
        String actualResult = userInfoService.updateInfo(userInfo);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void setMainPhoto() throws Exception {
        user = new User();
        user.setUsername("mainPhoto");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        Photo photo = new Photo();
        photo.setUserId(idUser);
        photo.setPhoto(Files.readAllBytes(new File("images/1.jpg").toPath()));
        userInfo = new UserInfo();
        userInfo.setAge(22);
        userInfo.setName("Андрей");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setCity("Луганск");
        userInfo.setUserId(idUser);
        photoService.addPhoto(photo);
        userInfo.setMainPhotoId(photo.getId());
        userInfoService.setInfo(userInfo);
        byte[] newPhoto = Files.readAllBytes(new File("images/2.jpg").toPath());
        photo = new Photo();
        photo.setUserId(idUser);
        photo.setPhoto(newPhoto);
        photoService.addPhoto(photo);
        String expectResult = "success";
        String actualResult = userInfoService.setMainPhoto(photo.getId(), idUser);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void getNameById() throws Exception {
        user = new User();
        user.setUsername("nameById");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        userInfo = new UserInfo();
        userInfo.setAge(21);
        userInfo.setName("Алексей");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setCity("Николаев");
        userInfo.setUserId(idUser);
        userInfoService.setInfo(userInfo);
        String expectResult = "Алексей";
        String actualResult = userInfoService.getNameById(idUser);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void getPhotoById() throws Exception {
        user = new User();
        user.setUsername("photoById");
        user.setPassword("pass");
        userService.register(user);
        int idUser = user.getId();
        Photo photo = new Photo();
        photo.setUserId(idUser);
        photo.setPhoto(Files.readAllBytes(new File("images/1.jpg").toPath()));
        photoService.addPhoto(photo);
        userInfo = new UserInfo();
        userInfo.setAge(32);
        userInfo.setName("Илья");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setCity("Ростов");
        userInfo.setUserId(idUser);
        userInfo.setMainPhotoId(photo.getId());
        userInfoService.setInfo(userInfo);
        long actualResult = userInfoService.getPhotoById(idUser);
        assertEquals(photo.getId(), actualResult);
    }
}