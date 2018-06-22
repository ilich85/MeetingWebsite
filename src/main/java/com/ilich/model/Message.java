package com.ilich.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    private long id;
    private String text;
    private String date;
    private int authorId;
    private String authorName;
    private long dialogId;

    public Message() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "text", length = 500, nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "author_id", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Column(name = "author_name", nullable = false)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "dialog_id", nullable = false)
    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Message message = (Message) object;

        if (id != message.id) return false;
        if (authorId != message.authorId) return false;
        if (dialogId != message.dialogId) return false;
        if (!text.equals(message.text)) return false;
        if (!date.equals(message.date)) return false;
        return authorName.equals(message.authorName);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + text.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + authorId;
        result = 31 * result + authorName.hashCode();
        result = 31 * result + (int) (dialogId ^ (dialogId >>> 32));
        return result;
    }
}