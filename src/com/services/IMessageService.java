package com.services;

import com.entity.Message;

import java.util.List;

/**
 * Created by lenovo on 2016/10/10.
 */
public interface IMessageService {

    void insertMessage(Message message);

    List<Message> listPageMessage(Message message);

    List<Message> getlistMessage(Message message);

    Message getMessageInfoByid(String id);

    void deleteMessage(String id);



    void updateMessage(Message message);

}
