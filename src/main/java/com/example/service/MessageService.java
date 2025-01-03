package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import com.example.exception.InvalidMessageException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    public Integer deleteMessageById(int id) {
        if (this.getMessageById(id) == null) {
            return null;
        }

        messageRepository.deleteById(id);
        return 1;
    }

    public Integer updateMessageById(int id, Message newMessage) throws InvalidMessageException {
        String newText = newMessage.getMessageText();
        
        boolean messageExists = messageRepository.existsById(id);
        
        if (messageExists && messageTextIsValid(newText)) {
            Message currentMessage = this.getMessageById(id);
            currentMessage.setMessageText(newText);
            messageRepository.save(currentMessage);
            return 1;

        } else {
            throw new InvalidMessageException();
        }

    }

    public Message createNewMessage(Message message) {
        String text = message.getMessageText();
        int userId = message.getPostedBy();

        // must belong to actual user
        boolean belongsToActualUser = messageRepository.existsByPostedBy(userId);
        
        boolean validMessage = belongsToActualUser && messageTextIsValid(text);

        if (validMessage) {
            return messageRepository.save(message);
        } else {
            throw new InvalidMessageException();
        }
        
    }
    
    public List<Message> getMessagesByPostedBy(int postedBy) {
        return messageRepository.findAllByPostedBy(postedBy);
    }


    // helpers
    public boolean messageTextIsValid(String text) {
        boolean textNotBlank = text != null && text.length() > 0;
        boolean textNotOver255Chars = text.length() < 256;
        return textNotBlank && textNotOver255Chars;
    }

}
