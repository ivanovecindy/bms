package com.services.impl;

import com.controller.BaseController;
import com.entity.ModelDeal;
import com.entity.ChildModelDeal;
import com.mapper.ChildModelDealMapper;
import com.mapper.ModelDealMapper;
import com.mapper.ModelMapper;
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


@Service("modelDealService")
@Transactional(readOnly = true)
public class ModelDealServiceImpl implements IModelDealService {

    @Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ModelDealMapper modelDealMapper;

	@Override
	public List<ModelDeal> listPageModelDeal(ModelDeal modelDeal) {
		return modelDealMapper.listPageModelDeal(modelDeal);
	}

	@Override
	public List<ModelDeal> getlistModelDeal(ModelDeal modelDeal) {
		return modelDealMapper.getlistModelDeal(modelDeal);
	}

	@Override
	public ModelDeal getModelDealInfoByid(String id) {
		return modelDealMapper.getModelDealInfoByid(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void deleteModelDeal(String id) {

		ModelDeal modelDeal= modelDealMapper.getModelDealInfoByid(id);
        if (modelDeal!=null){
			new File(modelDeal.getFjdz()+File.separator+ modelDeal.getFjmc()).delete();
		}
		modelDealMapper.deleteModelDeal(id);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public void saveModelDeal(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		ModelDeal modelDeal = new ModelDeal();
		BaseController baseController = new BaseController();
		baseController.requetToObject(request, modelDeal);
		if(StringUtils.isNotBlank(modelDeal.getModeId())){
			modelDeal.setModName(modelMapper.getModelInfoByid(modelDeal.getModeId()).getModName());
		}
		modelDeal.setCreateDate(new Date());
        //多个附件上传

		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			  	String ctxPath   =request.getSession().getServletContext().getRealPath(File.separator)
						+ File.separator + "fileUpload" + File.separator;
				if (StringUtils.isNotBlank(modelDeal.getFjdz())) {
					ctxPath = modelDeal.getFjdz();
				}
				File file = new File(ctxPath);
				if (!file.exists()) {
					FileOperateUtil.mkDir(file);
				}
				MultipartFile mf = entity.getValue();
				fileName = DateUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS") + "_" + mf.getOriginalFilename();
				File uploadFile = new File(ctxPath + fileName);
				if (StringUtils.isNotBlank(fileName)) {
					if ("jpg".equals(mf.getContentType())) {
						new com.util.ImageSizer().imageCompress(mf.getInputStream(), uploadFile, "jpg", null);
					} else {
						FileCopyUtils.copy(mf.getBytes(), uploadFile);
					}
					modelDeal.setFjdz(ctxPath);
					modelDeal.setFjmc(fileName);
				break;
			}
		}
		if (StringUtils.isNotBlank(id)) {
			baseController.requetToObject(request, modelDeal);
			ModelDeal modelDeal_temp  = modelDealMapper.getModelDealInfoByid(id);
			modelDeal.setFjdz(modelDeal_temp.getFjdz());
			modelDeal.setFjmc(modelDeal_temp.getFjmc());
			modelDealMapper.updateModelDeal(modelDeal);
		} else {
			id = new CreateNewKey().createId();
			modelDeal.setId(id);
			modelDealMapper.insertModelDeal(modelDeal);
		}

	}



	@Override
	public List<ModelDeal> getModelDeals(ModelDeal modelDeal) {
		return modelDealMapper.getModelDeals(modelDeal);
	}


}
