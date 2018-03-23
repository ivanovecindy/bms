package com.services;

import com.entity.Model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2016/10/10.
 */
public interface  IModelService {

    List<Model> listPageModel(Model model);

    List<Model> getlistModel(Model model);


    Model getModelInfoByid(String id);

    void deleteModel(String id);


    void saveModel(HttpServletRequest request) throws Exception;
}
