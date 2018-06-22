package com.ilich.service;

import com.ilich.model.Dialog;
import com.ilich.model.Message;
import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MessageServiceImplTest {
    private MessageService messageService;
    private DialogService dialogService;
    private UserService userService;
    private User user;
    private Dialog dialog;
    private Message message;
    private String date;

    @Before
    public void setUp() throws Exception {
        messageService = new MessageServiceImpl();
        dialogService = new DialogServiceImpl();
        userService = new UserServiceImpl();
        date = new SimpleDateFormat().format(new Date());
    }

    @Test
    public void addMessage() throws Exception {
        user = new User();
        user.setUsername("user41");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user42");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        long idDialog = dialogService.saveAndReturnId(dialog);
        message = new Message();
        message.setText("message1");
        message.setDate(date);
        message.setAuthorId(first);
        message.setAuthorName("name1");
        message.setDialogId(idDialog);
        String expectResult = "success";
        String actualResult = messageService.addMessage(message);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void messagesByDialogId() throws Exception {
        user = new User();
        user.setUsername("user43");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user44");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        long idDialog = dialogService.saveAndReturnId(dialog);
        message = new Message();
        message.setText("message1");
        message.setDate(date);
        message.setAuthorId(first);
        message.setAuthorName("name2");
        message.setDialogId(idDialog);
        messageService.addMessage(message);
        message = new Message();
        message.setText("message2");
        message.setDate(date);
        message.setAuthorId(second);
        message.setAuthorName("name3");
        message.setDialogId(idDialog);
        messageService.addMessage(message);
        int expectResult = 2;
        int actualResult = messageService.messagesByDialogId(idDialog).size();
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void lastMessageInDialog() throws Exception {
        user = new User();
        user.setUsername("user45");
        user.setPassword("pass");
        userService.register(user);
        int first = user.getId();
        user = new User();
        user.setUsername("user46");
        user.setPassword("pass");
        userService.register(user);
        int second = user.getId();
        dialog = new Dialog();
        dialog.setFirstUser(first);
        dialog.setSecondUser(second);
        long idDialog = dialogService.saveAndReturnId(dialog);
        message = new Message();
        message.setText("message11");
        message.setDate(date);
        message.setAuthorId(first);
        message.setAuthorName("name4");
        message.setDialogId(idDialog);
        messageService.addMessage(message);
        message = new Message();
        message.setText("message12");
        message.setDate(date);
        message.setAuthorId(second);
        message.setAuthorName("name5");
        message.setDialogId(idDialog);
        messageService.addMessage(message);
        Message actualResult = messageService.lastMessageInDialog(idDialog);
        assertEquals(message, actualResult);
    }
}
