package com.ilich.service;

import com.ilich.model.UserInfo;
import com.ilich.repository.UserInfoRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoRepository userInfoRepository
            = AppContext.getContext().getBean(UserInfoRepository.class);
    private final static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfo getInfo(int idUser) {
        return userInfoRepository.findAllByUserId(idUser);
    }

    @Override
    public Long getPhotoById(int idUser) {
        return userInfoRepository.getMainPhotoIdByUserId(idUser);
    }

    @Override
    public String getNameById(int idUser) {
        return userInfoRepository.getNameByUserId(idUser);
    }

    @Override
    public String setInfo(UserInfo info) {
        String result = "";
        try {
            userInfoRepository.save(info);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String updateInfo(UserInfo info) {
        String result = "";
        try {
            userInfoRepository.updateUserInfoById(info.getName(),
                    info.getAge(), info.getGender(), info.getCity(), info.getUserId());
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String setMainPhoto(long idPhoto, int idUser) {
        String result = "";
        try {
            userInfoRepository.setMainPhoto(idPhoto, idUser);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }
}