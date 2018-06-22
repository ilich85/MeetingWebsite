package com.ilich.repository;

import com.ilich.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m.text, m.date, m.authorId, m.authorName FROM Message m WHERE " +
            "m.dialogId = ?1 ORDER BY m.date")
    List<Object[]> getMessagesByDialogId(long idDialog);

    @Query("SELECT m.id, m.text, m.date, m.authorId, m.authorName, m.dialogId FROM Message m WHERE " +
            "m.dialogId = ?1 ORDER BY m.date")
    List<Object[]> getLastMessageByDialogId(long idDialog);
}