package com.navershop.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("userselect")
public class user_select {
	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	public ArrayList<Object> search_id(String uname,String uemail) { //1명의 정보, string으로 return해도 됨
		ArrayList<Object> onedata = new ArrayList<Object>();
		Map<String, String> keycode = new HashMap<String, String>();
		keycode.put("part", "1");
		keycode.put("uname", uname);
		keycode.put("uemail", uemail);
		
		user_dao dao = tm.selectOne("Shopping_mall.search",keycode);

		
		return onedata;
	}
}
