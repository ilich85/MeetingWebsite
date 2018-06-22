package com.ilich.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "characteristics")
public class Characteristic {

    private int id;
    private String aboutMe;
    private Integer growth;
    private Integer weight;
    private int userId;

    public Characteristic() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "about_me")
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Column(name = "growth")
    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Characteristic that = (Characteristic) object;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (!aboutMe.equals(that.aboutMe)) return false;
        if (!growth.equals(that.growth)) return false;
        return weight.equals(that.weight);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + aboutMe.hashCode();
        result = 31 * result + growth.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "id=" + id +
                ", aboutMe='" + aboutMe + '\'' +
                ", growth=" + growth +
                ", weight=" + weight +
                ", userId=" + userId +
                '}';
    }
}