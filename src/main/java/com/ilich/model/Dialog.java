package com.ilich.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "dialogs")
public class Dialog {

    private long id;
    private int firstUser;
    private int secondUser;

    public Dialog() {
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

    @Column(name = "first_user_id", nullable = false)
    public int getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(int firstUser) {
        this.firstUser = firstUser;
    }

    @Column(name = "second_user_id", nullable = false)
    public int getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(int secondUser) {
        this.secondUser = secondUser;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Dialog dialog = (Dialog) object;

        if (id != dialog.id) return false;
        if (firstUser != dialog.firstUser) return false;
        return secondUser == dialog.secondUser;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstUser;
        result = 31 * result + secondUser;
        return result;
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "id=" + id +
                ", firstUser=" + firstUser +
                ", secondUser=" + secondUser +
                '}';
    }
}