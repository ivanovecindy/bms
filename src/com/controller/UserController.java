package com.controller;


import com.entity.User;
import com.services.IUserService;
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
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 显示用户信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView list(HttpServletRequest request, User user)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		super.requetToObject(request, user);
		List<User> list = this.userService.listPageUser(user);
		mv.addObject("userList", list);
		mv.addObject("user", user);
		mv.setViewName("system/user");
		return mv;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteUser", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object> deleteUser(HttpServletRequest request){
		Map<String, Object> map=  new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		if(StringUtils.isNotBlank(ids)){
			for (String id :ids.split(",")) {
				userService.deleteUser(id);
			}
		}
		map.put("mesg", true);
		return map;
	}
	/**
	 * 用户修改密码
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateUserPwd", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Map<String, Object> updateUserPwd(HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		super.requetToObject(request, user);
		String pwd = user.getPwd();
		user = userService.getUserInfoByid(user.getId());
		user.setPwd(pwd);
		userService.updateUser(user);
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
