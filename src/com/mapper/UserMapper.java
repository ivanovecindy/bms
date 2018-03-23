package com.mapper;

import com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper  {

	void insertUser(User user);

	List<User> listPageUser(User user);
  
	List<User> getlistUser(User user);


	User getUserInfoByid(String id);

	void deleteUser(String id);

	void updateUser(User user);

	/**
	 * 将SQL做为条件查询
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> sqlSel(Map<String,Object> map);
	/**
	 * 将SQL做为条件修改
	 * @param map
	 */
	void  sqlUp(Map<String,Object> map);

}
