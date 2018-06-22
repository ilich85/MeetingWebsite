package com.ilich.service;

import com.ilich.model.Characteristic;
import com.ilich.repository.CharacteristicRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private CharacteristicRepository characteristicRepository
            = AppContext.getContext().getBean(CharacteristicRepository.class);
    private final static Logger logger = Logger.getLogger(CharacteristicServiceImpl.class);

    @Override
    public Characteristic getCharacteristic(int idUser) {
        Characteristic characteristic = null;
        try {
            characteristic = characteristicRepository.getAllByUserId(idUser);
        } catch (HibernateException e) {
            logger.error(e);
        }
        return characteristic;
    }

    @Override
    public String addCharacteristic(Characteristic characteristic) {
        String result = "";
        try {
            characteristicRepository.save(characteristic);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String updateCharacteristic(Characteristic c) {
        String result = "";
        try {
            characteristicRepository.updateCharacteristicById(c.getAboutMe(), c.getGrowth(), c.getWeight(), c.getUserId());
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }
}