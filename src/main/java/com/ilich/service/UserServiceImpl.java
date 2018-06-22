package com.ilich.service;

import com.ilich.model.User;
import com.ilich.repository.UserRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository
            = AppContext.getContext().getBean(UserRepository.class);
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public String register(User user) {
        String result = "";
        if (userRepository.findIdByUsername(user.getUsername()) == null) {
            try {
                userRepository.save(user);
                result = "success";
                logger.info("user " + user.getUsername() + " registered");
            } catch (HibernateException e) {
                logger.error(e);
            }
        } else {
            result = "user exists";
        }
        return result;
    }

    @Override
    public String checkUser(User user) {
        String result;
        User u = userRepository.findAllByUsername(user.getUsername());
        if (u == null) {
            result = "user not found";
        } else if (u.getPassword().equals(user.getPassword())) {
            result = String.valueOf(u.getId());
        } else {
            result = "wrong password";
        }
        return result;
    }

    @Override
    public String getPasswordById(int idUser) {
        String result = "";
        try {
            result = userRepository.findPasswordById(idUser);
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String changePassword(String newPassword, int idUser) {
        String result = "";
        try {
            userRepository.updatePasswordById(newPassword, idUser);
            result = "success";
            logger.info("user with id " + idUser + " change password");
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public String removeAccount(int idUser) {
        String result = "";
        try {
            userRepository.deleteById(idUser);
            result = "success";
            logger.info("user with id " + idUser + " was removed");
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }
}