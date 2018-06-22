package com.ilich.repository;

import com.ilich.model.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Characteristic c SET c.aboutMe = ?1, c.growth = ?2, c.weight = ?3 WHERE c.userId = ?4")
    void updateCharacteristicById(String aboutMe, Integer growth, Integer weight, int idUser);

    Characteristic getAllByUserId(int idUser);
}