package com.ilich.repository;

import com.ilich.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT id FROM User WHERE username = ?1")
    String findIdByUsername(String username);

    User findAllByUsername(String username);

    @Query("SELECT password FROM User WHERE id = ?1")
    String findPasswordById(int idUser);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = ?1 WHERE u.id = ?2")
    void updatePasswordById(String password, int userId);
}