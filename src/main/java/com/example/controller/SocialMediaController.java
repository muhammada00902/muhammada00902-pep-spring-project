package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Account registerUser(@RequestBody Account account) {
        return accountService.createNewAccount(account);
    }

    @PostMapping("/login")
    public Account loginUser(@RequestBody Account account) {
        return accountService.loginAccount(account);
    }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageService.createNewMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{message_id}")
    public Message getMessage(@PathVariable int message_id) {
        return messageService.getMessageById(message_id);
    }
    
    @DeleteMapping("/messages/{message_id}")
    public Integer deleteMessage(@PathVariable int message_id) {
        return messageService.deleteMessageById(message_id);
    }

    @PatchMapping("/messages/{message_id}")
    public Integer updateMessage(@PathVariable int message_id, @RequestBody Message newMessage) {
        return messageService.updateMessageById(message_id, newMessage);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public Object getAllMessagesOfUser(@PathVariable int account_id) {
        return messageService.getMessagesByPostedBy(account_id);
    }

}