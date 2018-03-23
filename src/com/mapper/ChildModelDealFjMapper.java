package com.mapper;

import com.entity.ChildModelDealFj;

import java.util.List;

public interface ChildModelDealFjMapper {

	void insertChildModelDealFj(ChildModelDealFj childModelDealFj);

	List<ChildModelDealFj> listPageChildModelDealFj(ChildModelDealFj childModelDealFj);
  
	List<ChildModelDealFj> getlistChildModelDealFj(ChildModelDealFj childModelDealFj);

	ChildModelDealFj getChildModelDealFjInfoByid(String id);

	void deleteChildModelDealFj(String id);

	void updateChildModelDealFj(ChildModelDealFj childModelDealFj);


}
