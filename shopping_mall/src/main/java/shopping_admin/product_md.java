package shopping_admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("prd_md")
public class product_md{
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	public int category_insert(cate_dao catedao) {
		int result = tm2.insert("shop.category_insert",catedao);
		
		return result;
	}
	
	public List<cate_dao> category_selectall(String adm_id) {
		List<cate_dao> all = new ArrayList<cate_dao>();
		all = tm2.selectList("shop.category_selectall",adm_id);
		
		return all;
	}
	
	public int category_del(String[] cateck) {
	    int result = 0;
	    int w=0;
	    while(w<cateck.length) {
	        result += tm2.delete("shop.category_del",cateck[w]);	
	        w++;
	    }

	    return result;
	}
	
	public cate_dao category_selectone(String no) {
		cate_dao result = tm2.selectOne("shop.category_selectone",no);
		
		return result;
	}
	
	public int category_update(cate_dao catedao) {
		int result = tm2.update("shop.category_update",catedao);
		
		return result;
	}
	
}
