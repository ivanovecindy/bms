package com.controller;


import com.entity.User;
import com.services.IUserService;
import com.util.Const;
import com.util.Tools;
import net.sf.json.JSONObject;
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
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 显示用户信息列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/logining", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView lgoinInit(HttpServletRequest request )
			throws Exception {
		   ModelAndView mv = new ModelAndView();
           HttpSession session = request.getSession();
           if (session.getAttribute(Const.SESSION_USER)!=null){
			   mv.setViewName("system/main");
		   }else {
			   User user = new User();
			   if(StringUtils.isNotBlank(request.getParameter("loginName"))){
				   user.setLoginName(request.getParameter("loginName"));
				   List<User> list = userService.getlistUser(user);
				   if (list!=null&&list.size()>0){
					   user = list.get(0);
					   if(StringUtils.isNotBlank(request.getParameter("pwd"))&&request.getParameter("pwd").equals(user.getPwd())){
						   mv.setViewName("system/main");
						   session.setAttribute(Const.SESSION_USER, user);
					   }else {
						   mv.addObject("mesg", "用户名或者密码错误!");
						   mv.addObject("name",request.getParameter("loginName"));
						   mv.setViewName("system/login");
					   }
				   }
			   }else{
				   mv.setViewName("system/login");
			   }
		   }

		return mv;
	}

	/**
	 * 用户获得模块
	 * @return
	 */
	@RequestMapping(value = "/leftMenu", method =  {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Map<String, Object> getMenu(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object>  map= null;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		String parentId = request.getParameter("parentId");
		map= userService.getMenu(user, parentId);
		return map;
	}
	/**
	 * 用户注销
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		HttpServletRequest request = super.getRequest();
		User user =(User)  request.getSession().getAttribute(Const.SESSION_USER);
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute(Const.SESSION_USER);
		request.getSession().removeAttribute(Const.SESSION_ROLE_RIGHTS);
		request.getSession().removeAttribute(Const.SESSION_USER_RIGHTS);
		request.setAttribute("logout", "logout");
		request.setAttribute("upPwd",request.getParameter("upPwd")!=null?request.getParameter("upPwd"):"");
		mv.setViewName("system/login");
		return mv;
	}
	/**
	 * 显示用户信息列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/main", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView main(HttpServletRequest request )
			throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/main");
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
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

 
}
