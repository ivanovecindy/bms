package com.controller;


import com.entity.Model;
import com.entity.ModelDeal;
import com.entity.ChildModelDeal;
import com.services.IModelDealService;
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
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/modelDeal")
public class ModelDealController extends BaseController {

    @Autowired
    private IModelDealService modelDealService;

    /**
     * 显示用户信息列表
     *
     * @return
     */
    @RequestMapping(value = "/modelList", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView list(HttpServletRequest request, ModelDeal modelDeal)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        super.requetToObject(request, modelDeal);
        String code = this.getRequest().getParameter("mcode");
        if(StringUtils.isNotBlank(code)){
            modelDeal.setModeId(code);
        }
        List<ModelDeal> list = this.modelDealService.listPageModelDeal(modelDeal);
        if(list!=null&&list.size()>0){
            for (ModelDeal model:list){
                if(StringUtils.isNotBlank(model.getMs())){
                    model.setMs(com.util.StringUtils.getTextFromTHML(model.getMs()));
                }
            }
        }
        mv.addObject("modelDealList", list);
        mv.addObject("modelDeal", modelDeal);
        mv.addObject("mcode", request.getParameter("mcode"));
        mv.setViewName("system/modelDeallist");
        super.setNavigation();
        return mv;
    }

    /**
     * 保存菜单信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveModeDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> saveModeDeal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Model menu = new Model();
        super.requetToObject(request, menu);
        String id = menu.getId();
        modelDealService.saveModelDeal(request);
        map.put("mesg", true);
        map.put("id", id);
        return map;
    }

    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/updateOrModelDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> updateOrModelDeal(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelDeal modelDeal = new ModelDeal();
        super.requetToObject(request, modelDeal);
        map.put("modelDeal", "");
        List<ModelDeal> list = modelDealService.getlistModelDeal(modelDeal);
        if (list != null && list.size() > 0) {
            modelDeal = list.get(0);
            map.put("modelDeal", modelDeal);
        }

        return map;
    }
    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/modelDealView", method = {RequestMethod.POST, RequestMethod.GET})
    public
    ModelAndView modelDealView(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        ModelDeal modelDeal = new ModelDeal();
        super.requetToObject(request, modelDeal);
        mv.addObject("modelDeal", "");
        List<ModelDeal> list = modelDealService.getlistModelDeal(modelDeal);
        if (list != null && list.size() > 0) {
            modelDeal = list.get(0);
        }
        mv.addObject("modelDeal", modelDeal);
        mv.setViewName("system/modelDealView");
        return mv;
    }
    /**
     * 删除
     */
    @RequestMapping(value = "/deleteModelDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> deleteModelDeal(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        if (StringUtils.isNotBlank(ids)) {
            for (String id : ids.split(",")) {
                modelDealService.deleteModelDeal(id);
            }
        }
        map.put("mesg", true);
        return map;
    }

    /**
     * 预览图片
     */
    @RequestMapping(value = "/previewPic", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView previewPic(HttpServletRequest request) {
        String id = request.getParameter("id");
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isNotBlank(id)) {
            ModelDeal modelDeal = modelDealService.getModelDealInfoByid(id);
            if (modelDeal != null) {
                //通过base64转码图片在页面显示图片
                String pic = "没有相关证件";

                if (StringUtils.isNotBlank(modelDeal.getFjdz()) && StringUtils.isNotBlank(modelDeal.getFjmc()) && new File(modelDeal.getFjdz() + File.separator + modelDeal.getFjmc()).exists()) {
                    pic = "<image src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(modelDeal.getFjdz() + File.separator + modelDeal.getFjmc()) + "\">";
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
     * 下载文件
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView download(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            ModelDeal modelDeal = modelDealService.getModelDealInfoByid(id);
            File file = null;
            String path = null;
            if (modelDeal != null) {
                //通过base64转码图片在页面显示图片
                path = modelDeal.getFjdz() + File.separator + modelDeal.getFjmc();
                file = new File(path);
            }

            if (file != null) {
                //下载
                BufferedOutputStream bos = null;
                BufferedInputStream bis = null;
                try {
                    long fileLength = file.length();
                    String agent = request.getHeader("User-Agent");
                    response.setContentType("application/x-download");
                    request.setCharacterEncoding("UTF-8");
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    boolean isMsie = (agent != null && agent.indexOf("MSIE") != -1);
                    if (isMsie) {
                        response.addHeader("Content-Disposition", "MemberAttch;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                        ; //设置文件下载类型
                    } else {
                        response.addHeader("Content-Disposition", "MemberAttch;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                        ; //设置文件下载类型
                    }
                    if (file.exists()) {//文件是否存在
                        bis = new BufferedInputStream(new FileInputStream(file));
                        bos = new BufferedOutputStream(response.getOutputStream());
                        int bytesRead = 0;
                        byte[] buffer = new byte[2048];
                        while (-1 != (bytesRead = bis.read(buffer, 0, buffer.length))) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                } catch (IOException e) {
                    response.reset();
                } finally {
                    try {
                        if (bos != null) {
                            bos.close();
                        }
                        if (bis != null) {
                            bis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
                true));
    }


}
