package com.ilich.service;

import com.ilich.model.Photo;

import java.util.List;

public interface PhotoService {

    String addPhoto(Photo photo);

    String removePhoto(long idPhoto);

    Photo getPhoto(Long idPhoto);

    List<Long> getPhotosByUserId(int idUser);
}