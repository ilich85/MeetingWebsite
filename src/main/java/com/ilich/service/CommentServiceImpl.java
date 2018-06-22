package com.ilich.service;

import com.ilich.model.Comment;
import com.ilich.repository.CommentRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository
            = AppContext.getContext().getBean(CommentRepository.class);
    private final static Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Override
    public String addComment(Comment comment) {
        String result = "";
        try {
            commentRepository.save(comment);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String updateComment(Comment comment) {
        String result = "";
        try {
            commentRepository.updateComment(comment.getText(), comment.getDate(), comment.getId());
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String removeComment(long idComment) {
        String result = "";
        try {
            commentRepository.deleteCommentById(idComment);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Comment> commentsList(long idPhoto) {
        return commentRepository.findAllByPhotoId(idPhoto);
    }
}