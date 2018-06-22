package com.ilich.service;

import com.ilich.model.Characteristic;
import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacteristicServiceImplTest {

    private CharacteristicService characteristicService;
    private UserService userService;
    private Characteristic ch;
    private User user;

    @Before
    public void setUp() throws Exception {
        characteristicService = new CharacteristicServiceImpl();
        userService = new UserServiceImpl();
    }

    @Test
    public void getCharacteristic() throws Exception {
        user = new User();
        user.setUsername("chGet");
        user.setPassword("pass");
        userService.register(user);
        ch = new Characteristic();
        ch.setAboutMe("Характеристика");
        ch.setGrowth(160);
        ch.setWeight(67);
        ch.setUserId(user.getId());
        characteristicService.addCharacteristic(ch);
        Characteristic actualResult = characteristicService.getCharacteristic(user.getId());
        assertEquals(ch, actualResult);
    }

    @Test
    public void addCharacteristic() throws Exception {
        user = new User();
        user.setUsername("chAdd");
        user.setPassword("pass");
        userService.register(user);
        ch = new Characteristic();
        ch.setAboutMe("Информация");
        ch.setGrowth(180);
        ch.setWeight(75);
        ch.setUserId(user.getId());
        String expectResult = "success";
        String actualResult = characteristicService.addCharacteristic(ch);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void updateCharacteristic() throws Exception {
        user = new User();
        user.setUsername("chUpd");
        user.setPassword("pass");
        userService.register(user);
        ch = new Characteristic();
        ch.setAboutMe("Информация");
        ch.setGrowth(180);
        ch.setWeight(75);
        ch.setUserId(user.getId());
        characteristicService.addCharacteristic(ch);
        ch.setAboutMe("Информация");
        ch.setGrowth(170);
        ch.setWeight(87);
        String expectResult = "success";
        String actualResult = characteristicService.updateCharacteristic(ch);
        assertEquals(expectResult, actualResult);
    }
}