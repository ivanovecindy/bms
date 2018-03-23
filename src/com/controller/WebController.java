package com.controller;


import com.entity.ChildModelDealFj;
import com.entity.Model;
import com.entity.ModelDeal;
import com.entity.ChildModelDeal;
import com.services.IChildModelDealService;
import com.services.IModelDealService;
import com.services.IModelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.xpath.operations.Mod;
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
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/web")
public class WebController extends BaseController {

	@Autowired
	private IModelService modelService;

	@Autowired
	private IModelDealService modelDealService;
	@Autowired
	private IChildModelDealService childModelDealService;

	/**
	 * 由一级导航得到二级导航
	 */
	@RequestMapping(value="/initQycp", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object> initQycp(HttpServletRequest request)throws  Exception{
		Map<String, Object> map=  new HashMap<String, Object>();
		ChildModelDeal childModelDeal = new ChildModelDeal();
		super.requetToObject(request, childModelDeal);
		map.put("list_fj", "");
		List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(childModelDeal);
		if (list != null && list.size() > 0) {
			List<ChildModelDealFj> listfj = new ArrayList<ChildModelDealFj>();
			for(ChildModelDeal childModelDeal1:list){
				ChildModelDealFj childModelDealFj = new ChildModelDealFj();
				childModelDealFj.setModeDealId(childModelDeal1.getId());
				List<ChildModelDealFj> listfj_ = childModelDealService.getlistChildModelDealFj(childModelDealFj);
				if (listfj_ != null && listfj_.size() > 0) {
					for(ChildModelDealFj cdf : listfj_){
						//通过base64转码图片在页面显示图片

						if (StringUtils.isNotBlank(cdf.getFjdz()) && StringUtils.isNotBlank(cdf.getFjmc()) && new File(cdf.getFjdz() + File.separator + cdf.getFjmc()).exists()) {
							String pic = "data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(cdf.getFjdz() + File.separator + cdf.getFjmc()) ;
							cdf.setCxtj(pic);
							cdf.setTitle(list.get(0).getTitle());
						}
					}
					listfj.addAll(listfj_);
				}
			}
			map.put("list_fj",listfj);
		}

		return map;
	}

	/**
	 * 得到前台页面导航
	 */
	@RequestMapping(value="/getQtDh", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object> getQtDh(HttpServletRequest request){
		Map<String, Object> map=  new HashMap<String, Object>();
		map.put("mode_list", "");
		Model model = new Model();
		model.setpId("-1");
		model.setModeType("1");
		model.setIsView("1");
		model.setCxtj("ORDER BY xh asc");
		List<Model> list = modelService.getlistModel(model);
		if(list!=null){
			for (Model model1 : list){
				Model model_temp = new Model();
				model_temp.setpId(model1.getId());
				model_temp.setCxtj("ORDER BY xh asc");
				List<Model> list_model = modelService.getlistModel(model_temp);
				if (list_model!=null&&list_model.size()>0){
					model1.setList_model(list_model);
				}
			}
			map.put("mode_list", list);
		}

		return map;
	}
	/**
	 * 得到前台设备图片
	 */
	@RequestMapping(value="/getSbtp", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object> getSbtp(HttpServletRequest request)throws  Exception{
		Map<String, Object> map=  new HashMap<String, Object>();
		map.put("mode_list", "");
		ChildModelDeal childModelDeal = new ChildModelDeal();
		super.requetToObject(request, childModelDeal);
		childModelDeal.setIsView("1");
		childModelDeal.setCxtj(" ORDER BY xh asc");
		List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(childModelDeal);
		if(list!=null){
		 	map.put("mode_list", list);
		}

		return map;
	}
	/**
	 * 由一级导航得到二级导航
	 */
	@RequestMapping(value="/getEjdh", method =  {RequestMethod.POST,RequestMethod.GET})
	public   @ResponseBody
	Map<String, Object> getEjdh(HttpServletRequest request)throws  Exception{
		Map<String, Object> map=  new HashMap<String, Object>();
		map.put("mode_list", "");
		Model model = new Model();
		if(StringUtils.isNotBlank(request.getParameter("modeDealId"))){
			model.setpId(request.getParameter("modeDealId"));
		}
		model.setIsView("1");
		model.setCxtj(" ORDER BY xh asc");
		List<Model> list = modelService.getlistModel(model);
		if(list!=null){
			map.put("mode_list", list);
		}

		return map;
	}



	/**
	 * 显示用户信息列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/getContext", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getContext(HttpServletRequest request, ModelDeal modelDeal)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		super.requetToObject(request, modelDeal);
		List<ModelDeal> list = this.modelDealService.getModelDeals(modelDeal);
		if (list!=null&&list.size()>0){
			modelDeal = list.get(0);
		}
		mv.addObject("modelDeal", modelDeal);
		mv.setViewName("foreground/for_home");
		return mv;
	}
	/**
	 * 预览图片
	 */
	@RequestMapping(value = "/previewPic", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView previewPic(HttpServletRequest request) {
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			ChildModelDealFj childModelDealFj =   childModelDealService.getChildModelDealFjInfoByid(id);
			if (childModelDealFj != null) {
				//通过base64转码图片在页面显示图片
				String pic = "没有相关证件";

				if (StringUtils.isNotBlank(childModelDealFj.getFjdz()) && StringUtils.isNotBlank(childModelDealFj.getFjmc()) && new File(childModelDealFj.getFjdz() + File.separator + childModelDealFj.getFjmc()).exists()) {
					pic = "<image width=\"165\" height=\"165\" src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(childModelDealFj.getFjdz() + File.separator + childModelDealFj.getFjmc()) + "\">";
				}

				mv.addObject("pic", pic);
			}
		} else {
			mv.addObject("pic", "");
		}
		mv.setViewName("system/fj_pic");
		return mv;
	}
	/**
	 * 修改或者查看
	 *
	 * @return
	 */
	@RequestMapping(value = "/getGenty", method = {RequestMethod.POST, RequestMethod.GET})
	public 	ModelAndView getGenty(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView();
		ChildModelDeal ChildModelDeal = new ChildModelDeal();
		if(StringUtils.isNotBlank(request.getParameter("id"))){
			ChildModelDeal.setModeDealId(request.getParameter("id"));
		}
		mv.addObject("childModelDeal", "");
		List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(ChildModelDeal);
		if (list != null && list.size() > 0) {
			mv.addObject("childModelDeal", list.get(0));
		}
		mv.setViewName("foreground/genty");
		return mv;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

 
}
