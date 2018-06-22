package com.ilich.service;

import com.ilich.model.Comment;
import com.ilich.model.Photo;
import com.ilich.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CommentServiceImplTest {
    private UserService userService;
    private PhotoService photoService;
    private CommentService commentService;
    private User user;
    private byte[] image;
    private Photo photo;
    private String date = new SimpleDateFormat().format(new Date());

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
        photoService = new PhotoServiceImpl();
        commentService = new CommentServiceImpl();
        image = Files.readAllBytes(new File("images/1.jpg").toPath());
    }

    @Test
    public void addComment() throws Exception {
        user = new User();
        user.setUsername("commentAdd");
        user.setPassword("pass");
        userService.register(user);
        photo = new Photo();
        photo.setUserId(user.getId());
        photo.setPhoto(image);
        photoService.addPhoto(photo);
        Comment comment = new Comment();
        comment.setText("comment");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        String expectResult = "success";
        String actualResult = commentService.addComment(comment);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void updateComment() throws Exception {
        user = new User();
        user.setUsername("commentUpdate");
        user.setPassword("pass");
        userService.register(user);
        photo = new Photo();
        photo.setUserId(user.getId());
        photo.setPhoto(image);
        photoService.addPhoto(photo);
        Comment comment = new Comment();
        comment.setText("comment");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        comment.setText("new comment");
        comment.setDate(date);
        String expectResult = "success";
        String actualResult = commentService.updateComment(comment);
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void removeComment() throws Exception {
        user = new User();
        user.setUsername("commentRemove");
        user.setPassword("pass");
        userService.register(user);
        photo = new Photo();
        photo.setUserId(user.getId());
        photo.setPhoto(image);
        photoService.addPhoto(photo);
        Comment comment = new Comment();
        comment.setText("comment");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        String expectResult = "success";
        String actualResult = commentService.removeComment(comment.getId());
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void commentsList() throws Exception {
        user = new User();
        user.setUsername("commentsList");
        user.setPassword("pass");
        userService.register(user);
        photo = new Photo();
        photo.setUserId(user.getId());
        photo.setPhoto(image);
        photoService.addPhoto(photo);
        Comment comment = new Comment();
        comment.setText("comment1");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        comment = new Comment();
        comment.setText("comment2");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        comment = new Comment();
        comment.setText("comment3");
        comment.setDate(date);
        comment.setPhotoId(photo.getId());
        comment.setUserId(user.getId());
        commentService.addComment(comment);
        int expectResult = 3;
        int actualResult = commentService.commentsList(photo.getId()).size();
        assertEquals(expectResult, actualResult);
    }
}