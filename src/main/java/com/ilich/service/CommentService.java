package com.ilich.service;

import com.ilich.model.Comment;

import java.util.List;

public interface CommentService {

    String addComment(Comment comment);

    String updateComment(Comment comment);

    String removeComment(long idComment);

    List<Comment> commentsList(long idPhoto);
}