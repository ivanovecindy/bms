package com.services;

import com.entity.ChildModelDeal;
import com.entity.ChildModelDealFj;
import com.entity.ModelDeal;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lenovo on 2016/10/10.
 */
public interface IChildModelDealService {
    void saveChildModelDeal(HttpServletRequest request) throws Exception;

    List<ChildModelDeal> listPageChildModelDeal(ChildModelDeal childModelDeal);

    List<ChildModelDeal> getlistChildModelDeal(ChildModelDeal childModelDeal);

    ChildModelDeal getChildModelDealInfoByid(String id);


    void deleteChildModelDeals(String modeDealId);

    void updateChildModelDeal(ChildModelDeal modelDeal);

    List<ChildModelDealFj> getlistChildModelDealFj(ChildModelDealFj childModelDealFj);

    ChildModelDealFj getChildModelDealFjInfoByid(String id);

    void deleteChildModelDealFj(String id);
}
