package com.ilich.service;

import com.ilich.model.Characteristic;

public interface CharacteristicService {

    Characteristic getCharacteristic(int idUser);

    String addCharacteristic(Characteristic characteristic);

    String updateCharacteristic(Characteristic characteristic);
}