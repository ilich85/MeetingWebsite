package com.ilich.service;

import com.ilich.model.UserInfo;

public interface UserInfoService {

    UserInfo getInfo(int idUser);

    Long getPhotoById(int idUser);

    String getNameById(int idUser);

    String setInfo(UserInfo info);

    String updateInfo(UserInfo info);

    String setMainPhoto(long idPhoto, int idUser);
}