package com.ilich.repository;

import com.ilich.model.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {

    @Query("SELECT d.id FROM Dialog d WHERE d.firstUser = ?1 AND d.secondUser = ?2 " +
            "OR d.firstUser = ?2 AND d.secondUser= ?1")
    Long getDialogByUsers(int firstUser, int secondUser);

    @Query("SELECT d.id, d.firstUser, d.secondUser FROM Dialog d WHERE d.firstUser = ?1 OR d.secondUser = ?1")
    List<Object[]> getDialogsForUser(int idUser);

    @Modifying
    @Transactional
    void deleteDialogById(long idDialog);

    @Query("SELECT d.firstUser, d.secondUser FROM Dialog d WHERE d.id = ?1")
    List<Integer[]> getUsersById(long idDialog);
}