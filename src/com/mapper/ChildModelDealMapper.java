package com.mapper;

import com.entity.ChildModelDeal;
import com.entity.ModelDeal;

import java.util.List;

public interface ChildModelDealMapper {

	void insertChildModelDeal(ChildModelDeal childModelDeal);

	List<ChildModelDeal> listPageChildModelDeal(ChildModelDeal childModelDeal);
  
	List<ChildModelDeal> getlistChildModelDeal(ChildModelDeal childModelDeal);

	ChildModelDeal getChildModelDealInfoByid(String id);


	void deleteChildModelDeals(String id);

	void updateChildModelDeal(ChildModelDeal modelDeal);


}
