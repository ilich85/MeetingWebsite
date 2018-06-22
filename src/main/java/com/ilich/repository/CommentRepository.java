package com.ilich.repository;

import com.ilich.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.text = ?1, c.date = ?2 WHERE c.id = ?3")
    void updateComment(String text, String date, long commentId);

    @Modifying
    @Transactional
    void deleteCommentById(long commentId);

    List<Comment> findAllByPhotoId(long idPhoto);
}