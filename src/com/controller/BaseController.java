package com.controller;

import com.entity.User;
import com.services.IModelService;
import com.util.DateTimeConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;


@Controller
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private IModelService modelService;
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}

	/**
	 * 得到session中的用户
	 * 
	 * @return
	 */
	public User getUserFromSession() {
		HttpSession session = this.getRequest().getSession();
		User user = (User) session.getAttribute(com.util.Const.SESSION_USER);
		return user;
	}


	// 将相关数据放入model
	protected void initResult(Model model, List<Object> list,
			Map<String, Object> map) {
		model.addAttribute("list", list);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			model.addAttribute(m.getKey().toString(), m.getValue());
		}
	}





	/**
	 * 将requst中的属性自动封装给相应的对象
	 * 
	 * @param request
	 * @param object
	 * @throws Exception
	 */
	public void requetToObject(HttpServletRequest request, Object object)
			throws Exception {
		Map<String, Object> queryMap = this.getQueryMap(request);
		ConvertUtils.register(new DateTimeConverter(), java.util.Date.class); // 在封装之前
																				// 注册转换器
		BeanUtils.populate(object, queryMap);// 方法的作用：用来将一些 key-value 的值（例如
												// hashmap）映射到 bean 中的属性。
	}
	/**
	 * 将requst中的属性自动封装给相应的对象
	 * @param object
	 * @throws Exception
	 */
	public void mapToObject(LinkedHashMap<String, Object> resultMap, Object object)
			throws Exception { 
		ConvertUtils.register(new DateTimeConverter(), java.util.Date.class); // 在封装之前
																				// 注册转换器
		BeanUtils.populate(object, resultMap);// 方法的作用：用来将一些 key-value 的值（例如
												// hashmap）映射到 bean 中的属性。
	}
	/**
	 * 将requst中的属性自动封装给相应的对象
	 * 
	 * @param object
	 * @throws Exception
	 */
	public void mapToObject(Map<String, String> resultMap, Object object)
			throws Exception { 
		ConvertUtils.register(new DateTimeConverter(), java.util.Date.class); // 在封装之前
																				// 注册转换器
		BeanUtils.populate(object, resultMap);// 方法的作用：用来将一些 key-value 的值（例如
												// hashmap）映射到 bean 中的属性。
	}
	/**
	 * 得到request中的请求参数
	 * 
	 * @param request
	 * @return
	 */
	public Map getQueryMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			if (StringUtils.isNotBlank(request.getParameter(paraName))) {
				map.put(paraName, request.getParameter(paraName).trim());
			}
		}

		return map;
	}

	/**
	 * 下载
	 * 
	 * @param file
	 * @param title
	 * @param response
	 * @throws Exception
	 */
	public void responseDownload(File file, String title,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (file != null) {
			// 下载
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			try {
				long fileLength = file.length();
				response.setContentType("application/x-download");
				request.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Length", String.valueOf(fileLength));
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(title.getBytes(), "ISO8859-1")
								+ ".xls"); // 设置文件下载类型
				if (file.exists()) {// 文件是否存在
					bis = new BufferedInputStream(new FileInputStream(file));
					bos = new BufferedOutputStream(response.getOutputStream());
					int bytesRead = 0;
					byte[] buffer = new byte[2048];
					while (-1 != (bytesRead = bis
							.read(buffer, 0, buffer.length))) {
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
			file.delete();
		}
	}

	public void downFile(HttpServletRequest request, HttpServletResponse response, File file) {
		if (file!=null) {
			//下载
			BufferedOutputStream bos =null;
			BufferedInputStream bis = null;
			try{
				long fileLength = file.length();
				String agent = request.getHeader("User-Agent");
				response.setContentType("application/x-download");
				request.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Length", String.valueOf(fileLength));
				boolean isMsie = (agent!=null&&agent.indexOf("MSIE")!=-1);
				if (isMsie) {
					response.addHeader("Content-Disposition","MemberAttch;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));; //设置文件下载类型
				}else {
					response.addHeader("Content-Disposition","MemberAttch;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));; //设置文件下载类型
				}
				if(file.exists()){//文件是否存在
					bis = new BufferedInputStream(new FileInputStream(file));
					bos = new BufferedOutputStream(response.getOutputStream());
					int bytesRead = 0;
					byte[] buffer = new byte[2048];
					while (-1 != (bytesRead = bis.read(buffer, 0, buffer.length))) {
						bos.write(buffer, 0, bytesRead);
					}
				}
			}catch(IOException e){
				response.reset();
			}finally {
				try {
					if (bos != null) {
						bos.close();
					}
					if (bis != null) {
						bis.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void responseJson(String json, HttpServletResponse response)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");

		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	public void responseText(String json, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 获取用户IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void responseDownload(File file,String title,HttpServletResponse response)throws Exception{
		//下载   
        BufferedOutputStream bos =null;
	    BufferedInputStream bis = null;
	    try{  
		  response.setContentType("application/x-download");
		  getRequest().setCharacterEncoding("UTF-8"); 
          response.addHeader("Content-Disposition","attachment;filename="+new String(title.getBytes(),"ISO8859-1")+".xls"); //设置文件下载类型
	    if(file.exists()){//文件是否存在
		    bis = new BufferedInputStream(new FileInputStream(file));
		    bos = new BufferedOutputStream(response.getOutputStream());
		    int bytesRead = 0;
	        byte[] buffer = new byte[4096];
	        while((bytesRead = bis.read(buffer)) != -1) {
	              bos.write(buffer, 0, bytesRead);//将文件发送到客户端
	        }
	    }
	}catch(IOException e){
		   response.reset();
	}finally {
       try {
           if (bos != null) {
               bos.close();
           }
           if (bis != null) {
               bis.close();
           }
       }
       catch (IOException e) {
           e.printStackTrace();
       }
   }
	    file.delete();
	}
	
	/**
	 * 
	 * 根据返回的int类型，转为为boolean
	 * @param count 多少行受影响
	 * @return flag
	 * @author Hank (jiangbo@firstelite.net)
	 * @since 2015年9月21日
	 */
	public  boolean getBoolean(Integer count) 
	{

		boolean flag = false;
		if (count == 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
	/**
	 * 根据模块code得到相对应的导航名称
	 */
	public void setNavigation() {
		try {
			String code = this.getRequest().getParameter("mcode");
			List<com.entity.Model> list= new  ArrayList<com.entity.Model>();
			recursiveNav(code, list);
			Collections.reverse(list);
			this.getRequest().setAttribute("modeList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void recursiveNav(String code,List<com.entity.Model> list) {
		try {
			if (StringUtils.isNotBlank(code)) {
				com.entity.Model model = new com.entity.Model();
				model.setId(code);
				List<com.entity.Model>  lists =  modelService.getlistModel(model);
				if(lists!=null&&lists.size()>0){
					model = lists.get(0);
					list.add(model);
					if (!"00000".equals(model.getpId())) {
						this.recursiveNav(model.getpId(), list);
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 显示信息发布信息列表
	 * @return
	 */
	@RequestMapping(value="/gotoJsp", method =  {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView gotoJsp(HttpServletRequest request)throws Exception{
		ModelAndView mv = new ModelAndView();
		String jsp = request.getParameter("jsp_");
		mv.setViewName(jsp);
		//this.setNavigation();//根据模块code得到相对应的导航名称
		return mv;
	}
}