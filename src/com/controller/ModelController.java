package com.controller;


import com.alibaba.fastjson.JSON;
import com.entity.Model;
import com.services.IModelService;
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
@RequestMapping(value = "/model")
public class ModelController extends BaseController {

	@Autowired
	private IModelService modelService;

	/**
	 * 显示用户信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modelList", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView list(HttpServletRequest request, Model model)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		super.requetToObject(request, model);
		List<Model> list = this.modelService.listPageModel(model);
		mv.addObject("modelList", list);
		mv.addObject("model", model);
		super.setNavigation();
		mv.setViewName("system/menu");
		return mv;
	}
	/**
	 * 修改或者查看某个菜单信息
	 * @return
	 */
	@RequestMapping(value="/menutreeList", method =  {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> menutreeList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		Model model = new Model();
		model.setCxtj(" order by xh asc");
		List<Model>  list = modelService.getlistModel(model);
		if (list!=null&&list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setParent(list.get(i).getpId());
			}
		}
		map.put("menuList", JSON.toJSON(list).toString());
		return map;
	}
	/**
	 * 保存菜单信息
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveMenu", method =  {RequestMethod.POST,RequestMethod.GET})
	public  @ResponseBody
	Map<String, Object> saveMenu(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		Model menu = new Model();
		super.requetToObject(request, menu);
		String id = menu.getId();
		 modelService.saveModel(request);
		map.put("mesg", true);
		map.put("id", id);
		return map;
	}
	/**
	 * 修改或者查看
	 * @return
	 */
	@RequestMapping(value="/updateOrModel", method =  {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	Map<String, Object> updateOrModel(HttpServletRequest request)throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		Model model = new Model();
		super.requetToObject(request, model);
		List<Model>  list = modelService.getlistModel(model);
		if (list!=null&&list.size()>0) {
			model = list.get(0);
		}
		map.put("model", model);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteModel", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object>  deleteModel(HttpServletRequest request){
		Map<String, Object> map=  new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		if(StringUtils.isNotBlank(ids)){
			for (String id :ids.split(",")) {
				modelService.deleteModel(id);
			}
		}
		map.put("mesg", true);
		return map;
	}
	/**
	 * 根据传入的父节点的值得到相应子节点构建下拉列表数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMenuSel", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object>  getMenuSel(HttpServletRequest request,Model menu) throws Exception{
		Map<String, Object>  map=  new HashMap<String, Object>();
		super.requetToObject(request, menu);
		List<Model>  list = modelService.getlistModel(menu);
		map.put("menuList", list);
		return map;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

 
}
