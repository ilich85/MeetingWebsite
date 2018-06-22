package com.ilich.service;

import com.ilich.model.Dialog;

import java.util.List;

public interface DialogService {

    Long getDialog(int firstUser, int secondUser);

    String removeDialog(long idDialog);

    List<Dialog> listDialogs(int idUser);

    Long saveAndReturnId(Dialog dialog);

    List<Integer> getUsersById(long idDialog);
}