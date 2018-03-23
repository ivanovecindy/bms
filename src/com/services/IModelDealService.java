package com.services;

import com.entity.ModelDeal;
import com.entity.ChildModelDeal;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2016/10/10.
 */
public interface IModelDealService {

    List<ModelDeal> listPageModelDeal(ModelDeal modelDeal);

    List<ModelDeal> getlistModelDeal(ModelDeal modelDeal);


    ModelDeal getModelDealInfoByid(String id);

    void deleteModelDeal(String id);

    void saveModelDeal(HttpServletRequest request) throws Exception;



    List<ModelDeal> getModelDeals(ModelDeal modelDeal);
}
