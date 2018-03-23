package com.services.impl;

import com.controller.BaseController;
import com.entity.Message;
import com.entity.ModelDeal;
import com.mapper.MessageMapper;
import com.mapper.ModelDealMapper;
import com.mapper.ModelMapper;
import com.services.IMessageService;
import com.services.IModelDealService;
import com.util.CreateNewKey;
import com.util.DateUtil;
import com.util.FileOperateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("messageService")
@Transactional(readOnly = true)
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private MessageMapper messageMapper;


	@Override
	public void insertMessage(Message message) {
		messageMapper.insertMessage(message);
	}

	@Override
	public List<Message> listPageMessage(Message message) {
		return messageMapper.listPageMessage(message);
	}

	@Override
	public List<Message> getlistMessage(Message message) {
		return messageMapper.getlistMessage(message);
	}

	@Override
	public Message getMessageInfoByid(String id) {
		return messageMapper.getMessageInfoByid(id);
	}

	@Override
	public void deleteMessage(String id) {
		messageMapper.deleteMessage(id);
	}

	@Override
	public void updateMessage(Message message) {
		messageMapper.updateMessage(message);
	}
}
