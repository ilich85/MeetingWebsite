package com.ilich.service;

import com.ilich.model.Message;
import com.ilich.repository.MessageRepository;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository
            = AppContext.getContext().getBean(MessageRepository.class);
    private final static Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Override
    public String addMessage(Message message) {
        String result = "";
        try {
            messageRepository.save(message);
            result = "success";
        } catch (HibernateException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public List<Message> messagesByDialogId(long idDialog) {
        List<Object[]> messages = messageRepository.getMessagesByDialogId(idDialog);
        List<Message> messageList = new ArrayList<>();
        if (messages.size() > 0) {
            for (Object[] o : messages) {
                Message message = new Message();
                message.setText((String) o[0]);
                message.setDate((String) o[1]);
                message.setAuthorId((Integer) o[2]);
                message.setAuthorName((String) o[3]);
                messageList.add(message);
            }
        }
        return messageList;
    }

    @Override
    public Message lastMessageInDialog(long idDialog) {
        List<Object[]> messages = messageRepository.getLastMessageByDialogId(idDialog);
        Message message = new Message();
        Object[] o = messages.get(messages.size() - 1);
        message.setId((Long) o[0]);
        message.setText((String) o[1]);
        message.setDate((String) o[2]);
        message.setAuthorId((Integer) o[3]);
        message.setAuthorName((String) o[4]);
        message.setDialogId((Long) o[5]);
        return message;
    }
}