package com.controller;

import com.entity.ChildModelDeal;
import com.entity.ChildModelDealFj;
import com.services.IChildModelDealService;
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
@RequestMapping(value = "/childModelDeal")
public class ChildModelDealController extends BaseController {

    @Autowired
    private IChildModelDealService childModelDealService;

    /**
     * 显示用户信息列表
     *
     * @return
     */
    @RequestMapping(value = "/childModelDealList", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView list(HttpServletRequest request, ChildModelDeal ChildModelDeal)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isNotBlank(request.getParameter("mcode"))){
            ChildModelDeal.setModeDealId(request.getParameter("mcode"));
        }
        super.requetToObject(request, ChildModelDeal);

        List<ChildModelDeal> list = this.childModelDealService.listPageChildModelDeal(ChildModelDeal);
        if(list!=null&&list.size()>0){
            for (ChildModelDeal model:list){
                if(StringUtils.isNotBlank(model.getMs())){
                    model.setMs(com.util.StringUtils.getTextFromTHML(model.getMs()));
                }
            }
        }
        mv.addObject("childModelDealList", list);
        mv.addObject("childModelDeal", ChildModelDeal);
        mv.addObject("mcode", request.getParameter("mcode"));
        mv.setViewName("system/childModelDeallist");
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
        childModelDealService.saveChildModelDeal(request);
        map.put("mesg", true);
        return map;
    }

    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/updateOrChildModelDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> updateOrChildModelDeal(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ChildModelDeal ChildModelDeal = new ChildModelDeal();
        super.requetToObject(request, ChildModelDeal);
        map.put("childModelDeal", "");
        List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(ChildModelDeal);
        if (list != null && list.size() > 0) {
            map.put("childModelDeal", list.get(0));
            ChildModelDealFj childModelDealFj = new ChildModelDealFj();
            childModelDealFj.setModeDealId(list.get(0).getId());
            List<ChildModelDealFj> listfj = childModelDealService.getlistChildModelDealFj(childModelDealFj);
            if (listfj != null && listfj.size() > 0) {
                for (ChildModelDealFj childModelDealFj2:listfj){
                    String fjmc = childModelDealFj2.getFjmc();
                    fjmc= fjmc.substring(fjmc.lastIndexOf(".")+1);
                    if("jpg".equals(fjmc)){
                        String  pic = "<image     src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(childModelDealFj2.getFjdz() + File.separator + childModelDealFj2.getFjmc()) + "\">";
                        if ("top".equals(childModelDealFj2.getXswz())){
                            childModelDealFj2.setCxtj(pic);
                        }
                        if ("bottom".equals(childModelDealFj2.getXswz())){
                            pic = "<image  width=\"95%\"  src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(childModelDealFj2.getFjdz() + File.separator + childModelDealFj2.getFjmc()) + "\">";
                               childModelDealFj2.setFjdz(pic);
                        }
                    }
                }
                map.put("list_fj", listfj);
            }
        }

        return map;
    }

    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/getChildModelDealFj", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> getChildModelDealFj(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ChildModelDeal ChildModelDeal = new ChildModelDeal();
        super.requetToObject(request, ChildModelDeal);
        map.put("childModelDeal", "");
        List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(ChildModelDeal);
        if (list != null && list.size() > 0) {
            ChildModelDealFj childModelDealFj = new ChildModelDealFj();
            childModelDealFj.setModeDealId(list.get(0).getId());
            map.put("childModelDeal", list.get(0));
            List<ChildModelDealFj> listfj = childModelDealService.getlistChildModelDealFj(childModelDealFj);
            if (listfj != null && listfj.size() > 0) {
                for (ChildModelDealFj childModelDealFj2:listfj){
                    String  pic = "<image  width=\"100%\" height=\"100%\" src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(childModelDealFj2.getFjdz() + File.separator + childModelDealFj2.getFjmc()) + "\">";
                    childModelDealFj2.setCxtj(pic);
                }
                map.put("list_fj", listfj);
            }
        }

        return map;
    }

    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/getChildModelDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> getChildModelDeal(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ChildModelDeal ChildModelDeal = new ChildModelDeal();
        super.requetToObject(request, ChildModelDeal);
        map.put("list_childModelDeal", "");
        List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(ChildModelDeal);
        if (list != null && list.size() > 0) {

            map.put("list_childModelDeal", list);
        }

        return map;
    }
    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/delChildModelDealFj", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> delChildModelDealFj(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            childModelDealService.deleteChildModelDealFj(id);
        }
        map.put("mesg", true);
        return map;
    }
    /**
     * 修改或者查看
     *
     * @return
     */
    @RequestMapping(value = "/childModelDealView", method = {RequestMethod.POST, RequestMethod.GET})
    public
    ModelAndView ChildModelDealView(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        ChildModelDeal ChildModelDeal = new ChildModelDeal();
        super.requetToObject(request, ChildModelDeal);
        List<ChildModelDeal> list = childModelDealService.getlistChildModelDeal(ChildModelDeal);
        if (list != null && list.size() > 0) {
            mv.addObject("childModelDeal", list.get(0));
            ChildModelDealFj childModelDealFj = new ChildModelDealFj();
            childModelDealFj.setModeDealId(list.get(0).getId());
            List<ChildModelDealFj> listfj = childModelDealService.getlistChildModelDealFj(childModelDealFj);
            if (listfj != null && listfj.size() > 0) {
                mv.addObject("list_fj", listfj);
            }
        }
        if(StringUtils.isNotBlank(request.getParameter("For_view"))){
            mv.setViewName("foreground/jszcDeal");
        }else{
            mv.setViewName("system/childModelDealView");
        }

        return mv;
    }
    /**
     * 删除
     */
    @RequestMapping(value = "/deleteChildModelDeal", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> deleteChildModelDeal(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            childModelDealService.deleteChildModelDeals(id);
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
            ChildModelDealFj childModelDealFj =   childModelDealService.getChildModelDealFjInfoByid(id);
            if (childModelDealFj!=null){
                String pic = "没有相关图片";
               if (StringUtils.isNotBlank(childModelDealFj.getFjdz()) && StringUtils.isNotBlank(childModelDealFj.getFjmc()) && new File(childModelDealFj.getFjdz() + File.separator + childModelDealFj.getFjmc()).exists()) {
                    pic = "<image width=\"60%\" height=\"70%\" src=\"data:image/png;base64," + com.util.Base64ToPicture.GetImageStr(childModelDealFj.getFjdz() + File.separator + childModelDealFj.getFjmc()) + "\">";
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
            ChildModelDealFj childModelDealFj =   childModelDealService.getChildModelDealFjInfoByid(id);
            File file = null;
            String path = null;
            if (childModelDealFj != null) {
                //通过base64转码图片在页面显示图片
                path = childModelDealFj.getFjdz() + File.separator + childModelDealFj.getFjmc();
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
