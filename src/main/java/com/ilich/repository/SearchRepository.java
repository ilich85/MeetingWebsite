package com.ilich.repository;

import com.ilich.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<UserInfo, Integer> {

    @Query(value = "SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.city = ?1 AND ui.gender = ?2 AND ui.age BETWEEN ?3 AND ?4")
    List<Object[]> usersByAllFields(String city, String gender, int minAge, int maxAge);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age BETWEEN ?1 AND ?2")
    List<Object[]> usersByAge(int minAge, int maxAge);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.city = ?1")
    List<Object[]> usersByCity(String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.gender = ?1")
    List<Object[]> usersByGender(String gender);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age >= ?1")
    List<Object[]> usersByMinAge(int minAge);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age >= ?1 AND ui.city = ?2")
    List<Object[]> usersByMinAgeAndCity(int minAge, String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age >= ?1 AND ui.gender = ?2")
    List<Object[]> usersByMinAgeAndGender(int minAge, String gender);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age >= ?1 AND ui.gender = ?2 AND ui.city = ?3")
    List<Object[]> usersByMinAgeAndGenderAndCity(int minAge, String gender, String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age <= ?1")
    List<Object[]> usersByMaxAge(int maxAge);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age <= ?1 AND ui.city = ?2")
    List<Object[]> usersByMaxAgeAndCity(int maxAge, String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age <= ?1 AND ui.gender = ?2")
    List<Object[]> usersByMaxAgeAndGender(int maxAge, String gender);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age <= ?1 AND ui.gender = ?2 AND ui.city = ?3")
    List<Object[]> usersByMaxAgeAndGenderAndCity(int maxAge, String gender, String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age BETWEEN ?1 AND ?2 AND ui.city = ?3")
    List<Object[]> usersByAgeAndCity(int minAge, int maxAge, String city);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.age BETWEEN ?1 AND ?2 AND ui.gender = ?3")
    List<Object[]> usersByAgeAndGender(int minAge, int maxAge, String gender);

    @Query("SELECT ui.userId, ui.name, ui.city, ui.age, ui.mainPhotoId FROM UserInfo ui " +
            "WHERE ui.city = ?1 AND ui.gender = ?2")
    List<Object[]> usersByCityAndGender(String city, String gender);
}