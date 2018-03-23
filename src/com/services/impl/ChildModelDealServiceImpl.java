package com.services.impl;

import com.controller.BaseController;
import com.entity.ChildModelDeal;
import com.entity.ChildModelDealFj;
import com.entity.ModelDeal;
import com.mapper.ChildModelDealFjMapper;
import com.mapper.ChildModelDealMapper;
import com.mapper.ModelDealMapper;
import com.mapper.ModelMapper;
import com.services.IChildModelDealService;
import com.services.IModelDealService;
import com.sun.xml.internal.ws.api.message.Attachment;
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


@Service("childModelDealService")
@Transactional(readOnly = true)
public class ChildModelDealServiceImpl implements IChildModelDealService {

	@Autowired
	private ChildModelDealMapper childModelDealMapper;
	@Autowired
	private ChildModelDealFjMapper childModelDealFjMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void saveChildModelDeal(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		ChildModelDeal childModelDeal = new ChildModelDeal();
		BaseController baseController = new BaseController();
		baseController.requetToObject(request, childModelDeal);
		childModelDeal.setCreateDate(new Date());
		Map <String ,Object>map = baseController.getQueryMap(request);
		 if (StringUtils.isNotBlank(id)) {
		  childModelDealMapper.updateChildModelDeal(childModelDeal);
		} else {
			id =   CreateNewKey.createId();
			childModelDeal.setId(id);
			childModelDealMapper.insertChildModelDeal(childModelDeal);
		}
		if (!map.isEmpty()) {
			for(String key:map.keySet()){
				if (key.contains("fj_id_")) {
					String id_=map.get(key).toString();
					ChildModelDealFj childModelDealFj = 	childModelDealFjMapper.getChildModelDealFjInfoByid(id_);
					String cout_=key.split("_")[2];
					if(map.containsKey("isfjView_"+cout_)){
						childModelDealFj.setIsView(map.get("isfjView_"+cout_)+"");
					}
					if(map.containsKey("xswz_"+cout_)){
						childModelDealFj.setXswz(map.get("xswz_" + cout_) + "");
					}
					childModelDealFjMapper.updateChildModelDealFj(childModelDealFj);
				}
			}
		}
//多个附件上传
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			String key = entity.getKey();
			String  fjid=null;
			ChildModelDealFj childModelDealFj = new ChildModelDealFj();
			childModelDealFj.setModeDealId(id);
			 String ctxPath   =request.getSession().getServletContext().getRealPath(File.separator)
					+ File.separator + "fileUpload" + File.separator;
			if (StringUtils.isNotBlank(childModelDeal.getFjdz())) {
				ctxPath = childModelDeal.getFjdz();
			}
			File file = new File(ctxPath);
			if (!file.exists()) {
				FileOperateUtil.mkDir(file);
			}
			MultipartFile mf = entity.getValue();
			childModelDealFj.setTitle(	mf.getOriginalFilename().substring(0,mf.getOriginalFilename().lastIndexOf(".")));
			fileName = DateUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS") + "_" + mf.getOriginalFilename();
			File uploadFile = new File(ctxPath + fileName);
			if (StringUtils.isNotBlank(fileName)) {
				if ("jpg".equals(mf.getContentType())||"JPG".equals(mf.getContentType())) {
					new com.util.ImageSizer().imageCompress(mf.getInputStream(), uploadFile, "jpg", null);
				} else {
					FileCopyUtils.copy(mf.getBytes(), uploadFile);
				}
				childModelDealFj.setFjdz(ctxPath);
				childModelDealFj.setFjmc(fileName);

				childModelDealFj.setCreateDate(new Date());
				if (key.indexOf("_")!=-1){
					String fj_h_ ="fj_h_"+key.split("_")[1];
					fjid = request.getParameter(fj_h_);
					childModelDealFj.setIsView(request.getParameter("isfjView_" + key.split("_")[1]));
					childModelDealFj.setXswz(request.getParameter("xswz_" + key.split("_")[1]));
					if (StringUtils.isBlank(fjid)){
						childModelDealFj.setId(CreateNewKey.createId());
						childModelDealFjMapper.insertChildModelDealFj(childModelDealFj);
					}else {
						childModelDealFj.setId(fjid);
						childModelDealFjMapper.updateChildModelDealFj(childModelDealFj);
					}
				}


			}
		}

	}



	@Override
	public List<ChildModelDeal> listPageChildModelDeal(ChildModelDeal childModelDeal) {
		return childModelDealMapper.listPageChildModelDeal(childModelDeal);
	}

	@Override
	public List<ChildModelDeal> getlistChildModelDeal(ChildModelDeal childModelDeal) {
		return childModelDealMapper.getlistChildModelDeal(childModelDeal);
	}

	@Override
	public ChildModelDeal getChildModelDealInfoByid(String id) {
		return childModelDealMapper.getChildModelDealInfoByid(id);
	}

	@Override
	public void deleteChildModelDeals(String modeDealId) {
		ChildModelDealFj childModelDealFj = new ChildModelDealFj();
		childModelDealFj.setModeDealId(modeDealId);
		List<ChildModelDealFj> list = childModelDealFjMapper.getlistChildModelDealFj(childModelDealFj);
		if (list!=null&&list.size()>0){
			for(ChildModelDealFj cfj : list){
				new File(cfj.getFjdz()+File.separator+ cfj.getFjmc()).delete();
				childModelDealFjMapper.deleteChildModelDealFj(cfj.getId());
			}

		}
		childModelDealMapper.deleteChildModelDeals(modeDealId);
	}

	@Override
	public List<ChildModelDealFj> getlistChildModelDealFj(ChildModelDealFj childModelDealFj) {
		return childModelDealFjMapper.getlistChildModelDealFj(childModelDealFj);
	}

	@Override
	public ChildModelDealFj getChildModelDealFjInfoByid(String id) {
		return childModelDealFjMapper.getChildModelDealFjInfoByid(id);
	}

	@Override
	public void deleteChildModelDealFj(String id) {
		ChildModelDealFj childModelDealFj = childModelDealFjMapper.getChildModelDealFjInfoByid(id);
		if (childModelDealFj!=null){
			new File(childModelDealFj.getFjdz()+File.separator+ childModelDealFj.getFjmc()).delete();
		}
		childModelDealFjMapper.deleteChildModelDealFj(id);
	}

	@Override
	public void updateChildModelDeal(ChildModelDeal modelDeal) {
		childModelDealMapper.updateChildModelDeal(modelDeal);
	}


}
