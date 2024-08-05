package shopping_admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("search_select", "0");
	    m.put("adm_id", adm_id);
	    
		all = tm2.selectList("shop.category_selectall",m);
		
		return all;
	}
	
	public List<cate_dao> category_selectall2(String adm_id,String search_select,String search_word) {
		List<cate_dao> all = new ArrayList<cate_dao>();

	    //mapper에 인자값은 단 한개의 값만 적용할 수 있음 
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", search_select);
	    m.put("search_word", search_word);
		
		all = tm2.selectList("shop.category_selectall",m);
		
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
