package com.ilich.service;

import com.ilich.model.Dialog;
import com.ilich.repository.DialogRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DialogServiceImpl implements DialogService {
    private DialogRepository dialogRepository
            = AppContext.getContext().getBean(DialogRepository.class);
    private final static Logger logger = Logger.getLogger(DialogServiceImpl.class);

    @Override
    public Long getDialog(int firstUser, int secondUser) {
        return dialogRepository.getDialogByUsers(firstUser, secondUser);
    }

    @Override
    public String removeDialog(long idDialog) {
        String result = "";
        try {
            dialogRepository.deleteDialogById(idDialog);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Dialog> listDialogs(int idUser) {
        List<Object[]> result = dialogRepository.getDialogsForUser(idUser);
        List<Dialog> dialogs = new ArrayList<>();
        for (Object[] o : result) {
            Dialog dialog = new Dialog();
            dialog.setId((Long) o[0]);
            dialog.setFirstUser((Integer) o[1]);
            dialog.setSecondUser((Integer) o[2]);
            dialogs.add(dialog);
        }
        return dialogs;
    }

    @Override
    public Long saveAndReturnId(Dialog dialog) {
        Long result = 0L;
        try {
            Long id = dialogRepository.getDialogByUsers(dialog.getFirstUser(), dialog.getSecondUser());
            if (id == null) {
                result = dialogRepository.saveAndFlush(dialog).getId();
            } else {
                result = id;
            }
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Integer> getUsersById(long idDialog) {
        List<Integer[]> usersId = dialogRepository.getUsersById(idDialog);
        List<Integer> users = new ArrayList<>();
        for (Object[] o : usersId) {
            users.add((Integer) o[0]);
            users.add((Integer) o[1]);
        }
        return users;
    }
}