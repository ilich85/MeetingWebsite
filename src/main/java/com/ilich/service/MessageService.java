package com.ilich.service;

import com.ilich.model.Message;

import java.util.List;

public interface MessageService {

    String addMessage(Message message);

    List<Message> messagesByDialogId(long idDialog);

    Message lastMessageInDialog(long idDialog);
}