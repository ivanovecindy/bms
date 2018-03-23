package com.services.impl;

import com.controller.BaseController;
import com.entity.Model;
import com.mapper.ModelMapper;
import com.services.IModelService;
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


@Service("modelService")
@Transactional(readOnly = true)
public class ModelServiceImpl implements IModelService {

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<Model> listPageModel(Model model) {
        return modelMapper.listPageModel(model);
    }

    @Override
    public List<Model> getlistModel(Model model) {
        return modelMapper.getlistModel(model);
    }

    @Override
    public Model getModelInfoByid(String id) {
        return modelMapper.getModelInfoByid(id);
    }

    @Override
    public void deleteModel(String id) {
        modelMapper.deleteModel(id);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void saveModel(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        Model model = new Model();
        BaseController baseController = new BaseController();
        baseController.requetToObject(request, model);
        model.setCreateDate(new Date());

        if (StringUtils.isNotBlank(id)) {
            modelMapper.updateModel(model);
        } else {
            id = new CreateNewKey().createId();
            model.setId(id);
            modelMapper.insertModel(model);
        }
    }
}
