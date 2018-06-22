package com.ilich.service;

import com.ilich.model.Photo;
import com.ilich.repository.PhotoRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private PhotoRepository photoRepository
            = AppContext.getContext().getBean(PhotoRepository.class);
    private final static Logger logger = Logger.getLogger(PhotoServiceImpl.class);

    @Override
    public String addPhoto(Photo photo) {
        String result = "";
        try {
            photoRepository.save(photo);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String removePhoto(long idPhoto) {
        String result = "";
        try {
            photoRepository.deletePhotoById(idPhoto);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public Photo getPhoto(Long idPhoto) {
        return photoRepository.getAllById(idPhoto);
    }

    @Override
    public List<Long> getPhotosByUserId(int idUser) {
        return photoRepository.getIdPhotosByUserId(idUser);
    }
}