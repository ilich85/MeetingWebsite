package com.ilich.repository;

import com.ilich.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE UserInfo ui SET ui.name = ?1, ui.age = ?2, ui.gender = ?3, ui.city = ?4 WHERE ui.userId = ?5")
    void updateUserInfoById(String name, int age, String gender, String city, int userId);

    UserInfo findAllByUserId(int idUser);

    @Query("SELECT ui.name FROM UserInfo ui WHERE ui.userId = ?1")
    String getNameByUserId(int idUser);

    @Query("SELECT ui.mainPhotoId FROM UserInfo ui WHERE ui.userId = ?1")
    Long getMainPhotoIdByUserId(int idUser);

    @Modifying
    @Transactional
    @Query("UPDATE UserInfo ui SET ui.mainPhotoId = ?1 WHERE ui.userId = ?2")
    void setMainPhoto(long idPhoto, int idUser);
}