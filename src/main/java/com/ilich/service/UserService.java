package com.ilich.service;

import com.ilich.model.User;

public interface UserService {

    String register(User user);

    String checkUser(User user);

    String getPasswordById(int idUser);

    String changePassword(String newPassword, int idUser);

    String removeAccount(int idUser);
}