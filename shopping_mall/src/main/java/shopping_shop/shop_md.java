package shopping_shop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_admin.mj_set_dao;

@Repository("shop_md")
public class shop_md {
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	public List<mj_set_dao> select_shopname_all() {
		List<mj_set_dao> result = new ArrayList<mj_set_dao>(); 
		result = tm2.selectList("shop.select_shopname_all");
		
		return result;
	}
}
