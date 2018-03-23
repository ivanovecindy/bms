package com.mapper;

import com.entity.ModelDeal;

import java.util.List;
import java.util.Map;

public interface ModelDealMapper {

	void insertModelDeal(ModelDeal modelDeal);

	List<ModelDeal> listPageModelDeal(ModelDeal modelDeal);
  
	List<ModelDeal> getlistModelDeal(ModelDeal modelDeal);

	List<ModelDeal> getModelDeals(ModelDeal modelDeal);

	ModelDeal getModelDealInfoByid(String id);

	void deleteModelDeal(String id);



	void updateModelDeal(ModelDeal modelDeal);


}
