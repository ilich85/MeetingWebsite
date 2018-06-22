package com.ilich.service;

import com.ilich.model.Dialog;
import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DialogServiceImplTest {

    private UserService userService;
    private DialogService dialogService;
    private User user;
    private Dialog dialog;

    @Before
    public void setUp() throws Exception {
        dialogService = new DialogServiceImpl();
        userService = new UserServiceImpl();
    }

    @Test
    public void addDialogIfNotExists() throws Exception {
        user = new User();
        user.setUsername("user21");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user22");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        Long expectResult = dialogService.saveAndReturnId(dialog);
        Long actualResult = dialogService.getDialog(second, first);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void addDialogIfExists() throws Exception {
        user = new User();
        user.setUsername("user23");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user24");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        Long expectResult = dialogService.saveAndReturnId(dialog);
        dialog = new Dialog();
        dialog.setFirstUser(second);
        dialog.setSecondUser(first);
        Long actualResult = dialogService.saveAndReturnId(dialog);
        assertEquals(expectResult, actualResult);

    }

    @Test
    public void getDialog() throws Exception {
        user = new User();
        user.setUsername("user25");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user26");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        long id = dialogService.saveAndReturnId(dialog);
        long actualResult = dialogService.getDialog(second, first);
        assertEquals(id, actualResult);
    }

    @Test
    public void removeDialog() throws Exception {
        user = new User();
        user.setUsername("user27");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user28");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        long id = dialogService.saveAndReturnId(dialog);
        String expectResult = "success";
        String actualResult = dialogService.removeDialog(id);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void listDialogs() throws Exception {
        user = new User();
        user.setUsername("user29");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user30");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        user = new User();
        user.setUsername("user31");
        user.setPassword("pass");
        userService.register(user);
        int third = user.getId();
        user = new User();
        user.setUsername("user32");
        user.setPassword("pass");
        userService.register(user);
        int fourth = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        dialogService.saveAndReturnId(dialog);
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(third);
        dialogService.saveAndReturnId(dialog);
        dialog = new Dialog();
        dialog.setFirstUser(second);
        dialog.setSecondUser(third);
        dialogService.saveAndReturnId(dialog);
        dialog = new Dialog();
        dialog.setFirstUser(fourth);
        dialog.setSecondUser(first);
        dialogService.saveAndReturnId(dialog);
        dialog = new Dialog();
        dialog.setFirstUser(second);
        dialog.setSecondUser(fourth);
        dialogService.saveAndReturnId(dialog);
        long expectResult = 3;
        long actualResult = dialogService.listDialogs(first).size();
        assertEquals(expectResult, actualResult);
    }
}