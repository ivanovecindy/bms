package com.controller;


import com.entity.Message;
import com.services.IMessageService;
import com.util.CreateNewKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

	@Autowired
	private IMessageService messageService;

	/**
	 * 显示用户信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/messageList", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView messageList(HttpServletRequest request, Message message)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		super.requetToObject(request, message);
		List<Message> list = this.messageService.listPageMessage(message);
		mv.addObject("messageList", list);
		mv.addObject("message", message);
		super.setNavigation();
		mv.setViewName("system/messageList");
		return mv;
	}

	/**
	 * 保存菜单信息
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveMessage", method =  {RequestMethod.POST,RequestMethod.GET})
	public  @ResponseBody
	Map<String, Object> saveMessage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		Message message = new Message();
		super.requetToObject(request, message);
		message.setCreateDate(new Date());
		String id = message.getId();
	    if (StringUtils.isNotBlank(id)){
			messageService.updateMessage(message);
		}else {
			message.setId(CreateNewKey.createId());
			messageService.insertMessage(message);
		}
		map.put("mesg", true);
		return map;
	}
	/**
	 * 修改或者查看
	 * @return
	 */
	@RequestMapping(value="/updateOrMessage", method =  {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	Map<String, Object> updateOrMessage(HttpServletRequest request)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		Message message = new Message();
		super.requetToObject(request, message);
		List<Message>  list = messageService.getlistMessage(message);
		if (list!=null&&list.size()>0) {
			message = list.get(0);
		}
		map.put("message", message);
		return map;
	}
	/**
	 *查看
	 * @return
	 */
	@RequestMapping(value="/viewMessage", method =  {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView viewMessage(HttpServletRequest request)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView();
		Message message = new Message();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			message = messageService.getMessageInfoByid(id);
		}
		mv.addObject("message",message);
		mv.setViewName("system/messageView");
		return mv;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteMessage", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object>  deleteMessage(HttpServletRequest request){
		Map<String, Object> map=  new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				messageService.deleteMessage(id);
		}
		map.put("mesg", true);
		return map;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

 
}
