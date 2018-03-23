package com.services.impl;

import com.entity.Model;
import com.entity.User;
import com.mapper.ModelMapper;
import com.mapper.UserMapper;
import com.services.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public List<User> listPageUser(User user) {
		return userMapper.listPageUser(user);
	}

	@Override
	public List<User> getlistUser(User user) {
		return userMapper.getlistUser(user);
	}

	@Override
	public User getUserInfoByid(String userId) {
		return userMapper.getUserInfoByid(userId);
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	/**
	 * 用户获得模块
	 *
	 * @return
	 */
	public Map<String, Object> getMenu(User user, String parentId) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Model> list = null;
		Model m = new Model();
		m.setpId(parentId);
		m.setIsView("");
		list = modelMapper.getlistModel(m);
		StringBuilder sb = new StringBuilder();
		sb.append("{\"menus\": [");
		if (list!=null&&list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Model model = list.get(i);
				if (i > 0) {
					sb.append(",");
				}
				sb.append("{\"menuid\":\"" + model.getId() + "\",");
				sb.append("\"menuname\":\"" + model.getModName() + "\",");
				if (StringUtils.isNotBlank(model.getUrl())) {
					sb.append("\"url\":\"" + model.getUrl() + "\",");
				} else {
					sb.append("\"url\":\"\",");
				}
				List<Model> list_child = null;
				Model model_c = new Model();
				model_c.setpId(model.getId());
				model_c.setIsView("");
				model_c.setCxtj(" order by xh asc");
				 list_child = modelMapper.getlistModel(model_c);
				if (list_child.size() > 0) {
					sb.append("\"menus\": [");
					for (int j = 0; j < list_child.size(); j++) {
						Model menu2 = list_child.get(j);
						if (j > 0) {
							sb.append(",");
						}
						sb.append("{\"menuid\":\"" + menu2.getId() + "\",");
						sb.append("\"menuname\":\"" + menu2.getModName()
								+ "\",");
						if (StringUtils.isNotBlank(menu2.getUrl())) {
							sb.append("\"url\":\"" + menu2.getUrl() + "\"}");
						} else {
							sb.append("\"url\":\"\"}");
						}
					}
					sb.append("]}");
				} else {
					sb.append("\"menus\":").append("\"\"");
					sb.append("}");
				}
			}
		}
		sb.append("]}");
		map.put("menu_", sb.toString());
		return map;
	}
}
