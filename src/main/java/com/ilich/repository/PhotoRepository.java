package com.ilich.repository;

import com.ilich.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Modifying
    @Transactional
    void deletePhotoById(long photoId);

    Photo getAllById(long idPhoto);

    @Query("SELECT p.id FROM Photo p WHERE p.userId = ?1 ORDER BY p.id DESC")
    List<Long> getIdPhotosByUserId(int idUser);
}