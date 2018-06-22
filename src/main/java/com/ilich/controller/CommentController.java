package com.ilich.controller;

import com.ilich.model.Comment;
import com.ilich.service.CommentService;
import com.ilich.service.UserInfoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestScope
public class CommentController {
    private final CommentService commentService;
    @Autowired
    private UserInfoService userInfoService;
    private final UserFromCookies userFromCookies = new UserFromCookies();

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{id}")
    public String commentsByPhotoId(@PathVariable Long id, HttpServletRequest request,
                                    HttpServletResponse response) {
        if (userFromCookies.getCurrentUserId(request.getCookies()) == 0) {
            return "redirect:/index";
        }
        JSONObject jsonObject = new JSONObject();
        for (Comment comment : commentService.commentsList(id)) {
            jsonObject.put(comment.getId(), serializableComment(comment));
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/plain; charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            jsonObject.writeJSONString(writer);
        } catch (IOException ignored) {
        }
        return "";
    }

    private JSONObject serializableComment(Comment comment) {
        JSONObject commentJson = new JSONObject();
        int idUser = comment.getUserId();
        commentJson.put("date", comment.getDate());
        commentJson.put("text", comment.getText());
        commentJson.put("userId", idUser);
        commentJson.put("name", userInfoService.getNameById(idUser));
        commentJson.put("photo", userInfoService.getPhotoById(idUser));
        return commentJson;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-comment/{id}")
    public String addComment(HttpServletRequest request, @PathVariable Long id) {
        int currentUser = userFromCookies.getCurrentUserId(request.getCookies());
        if (currentUser == 0) {
            return "redirect:/index";
        }
        int idUser = Integer.parseInt(request.getParameter("userId"));
        String text = request.getParameter("text");
        if (text.trim().equals("")) {
            return null;
        }
        Comment comment = new Comment();
        comment.setText(text);
        comment.setDate(new SimpleDateFormat().format(new Date()));
        comment.setPhotoId(id);
        comment.setUserId(currentUser);
        commentService.addComment(comment);
        return "redirect:/user_" + idUser + "/photos";
    }
}