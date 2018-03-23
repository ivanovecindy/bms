package com.mapper;

import com.entity.Message;

import java.util.List;

public interface MessageMapper {

	void insertMessage(Message message);

	List<Message> listPageMessage(Message message);
  
	List<Message> getlistMessage(Message message);

	Message getMessageInfoByid(String id);

	void deleteMessage(String id);



	void updateMessage(Message message);


}
