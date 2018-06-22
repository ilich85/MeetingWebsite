package com.ilich.controller;

import com.ilich.model.Dialog;
import com.ilich.model.DialogInfo;
import com.ilich.model.Message;
import com.ilich.service.DialogService;
import com.ilich.service.MessageService;
import com.ilich.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestScope
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DialogService dialogService;
    private final UserFromCookies userFromCookies = new UserFromCookies();
    private int idUser;

    @RequestMapping(value = "/dialogs", method = RequestMethod.GET)
    public String messages(HttpServletRequest request) {
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        if (idUser == 0) {
            return "redirect:/login";
        }
        return "redirect:/user_" + idUser + "/dialogs";
    }

    @RequestMapping(value = "/user_{id}/dialogs", method = RequestMethod.GET)
    public ModelAndView dialogsForUser(HttpServletRequest request, @PathVariable int id) {
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        ModelAndView modelAndView = new ModelAndView();
        if (idUser == 0 || id != idUser) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        List<DialogInfo> dialogs = new ArrayList<>();
        List<Dialog> dialogsList = dialogService.listDialogs(idUser);
        for (Dialog d : dialogsList) {
            DialogInfo dialogInfo = new DialogInfo();
            if (d.getFirstUser() == idUser) {
                dialogInfo.setIdUser(d.getSecondUser());
                dialogInfo.setInterlocutorName(userInfoService.getNameById(d.getSecondUser()));
                dialogInfo.setPhotoId(userInfoService.getPhotoById(d.getSecondUser()));
            } else {
                dialogInfo.setIdUser(d.getFirstUser());
                dialogInfo.setInterlocutorName(userInfoService.getNameById(d.getFirstUser()));
                dialogInfo.setPhotoId(userInfoService.getPhotoById(d.getFirstUser()));
            }
            dialogInfo.setDialogId(d.getId());
            dialogInfo.setLastMessage(messageService.lastMessageInDialog(d.getId()).getText());
            dialogs.add(dialogInfo);
        }
        modelAndView.setViewName("dialogs");
        modelAndView.addObject("dialogs", dialogs);
        return modelAndView;
    }

    @RequestMapping(value = "/user_{userId}/dialog_{id}", method = RequestMethod.GET)
    public ModelAndView messagesInDialog(HttpServletRequest request, @PathVariable int userId,
                                         @PathVariable long id) {
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        ModelAndView modelAndView = new ModelAndView();
        if (idUser == 0 || userId != idUser) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        int interlocutor = 0;
        List<Integer> users = dialogService.getUsersById(id);
        for (int user : users) {
            if (user != userId) {
                interlocutor = user;
            }
        }
        String interlocutorName = userInfoService.getNameById(interlocutor);
        Long interlocutorPhoto = userInfoService.getPhotoById(interlocutor);
        modelAndView.setViewName("messages");
        List<Message> messages = messageService.messagesByDialogId(id);
        modelAndView.addObject("message", new Message());
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("currentUser", idUser);
        modelAndView.addObject("interlocutor", interlocutor);
        modelAndView.addObject("interlocutorName", interlocutorName);
        modelAndView.addObject("photoId", interlocutorPhoto);
        return modelAndView;
    }

    @RequestMapping(value = "/send-message", method = RequestMethod.POST)
    public String sendMessage(@ModelAttribute Message message, HttpServletRequest request) {
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        int idToUser = Integer.parseInt(request.getParameter("user"));
        if (idUser == 0) {
            return "redirect:/index";
        }
        setMessage(message, idUser, idToUser);
        return "redirect:/user_" + idToUser;
    }

    @RequestMapping(value = "/add-message", method = RequestMethod.POST)
    public String addMessage(@ModelAttribute Message message, HttpServletRequest request) {
        int idToUser = Integer.parseInt(request.getParameter("user"));
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        long idDialog = setMessage(message, idUser, idToUser);
        return "redirect:/messages-by-dialog/" + idDialog;
    }

    private long setMessage(Message message, int fromUser, int toUser) {
        Dialog dialog = new Dialog();
        dialog.setFirstUser(fromUser);
        dialog.setSecondUser(toUser);
        long idDialog = dialogService.saveAndReturnId(dialog);
        message.setDialogId(idDialog);
        message.setAuthorId(fromUser);
        message.setAuthorName(userInfoService.getNameById(fromUser));
        message.setDate(new SimpleDateFormat().format(new Date()));
        messageService.addMessage(message);
        return idDialog;
    }

    @RequestMapping(value = "/messages-by-dialog/{id}", method = RequestMethod.GET)
    public String messagesByDialog(HttpServletRequest request, @PathVariable long id) {
        idUser = userFromCookies.getCurrentUserId(request.getCookies());
        if (idUser == 0) {
            return "redirect:/index";
        }
        return "redirect:/user_" + idUser + "/dialog_" + id;
    }
}