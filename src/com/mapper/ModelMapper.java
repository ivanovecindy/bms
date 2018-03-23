package com.mapper;

import com.entity.Model;

import java.util.List;

public interface ModelMapper {

    void insertModel(Model model);

    List<Model> listPageModel(Model model);

    List<Model> getlistModel(Model model);


    Model getModelInfoByid(String id);

    void deleteModel(String id);

    void updateModel(Model model);


}
