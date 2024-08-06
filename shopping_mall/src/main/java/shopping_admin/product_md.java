package shopping_admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Repository("prd_md")
public class product_md{
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	public int category_insert(cate_dao catedao) {
		int result = tm2.insert("shop.category_insert",catedao);
		
		return result;
	}
	
	public List<cate_dao> category_selectall(String adm_id,String part) {
		List<cate_dao> all = new ArrayList<cate_dao>();
		
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("search_select", part);
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
	
	
	//prd code 중복체크
	public int prdlist_selectone(prd_dao dao) {
		Map<String, String> kc = new HashMap<String, String>();
		kc.put("adm_id", dao.getAdm_id());
		kc.put("p_code", dao.getP_code());

		int result = tm2.selectOne("shop.prdlist_select_idpcode",kc);
		
		return result; 
	}
	
	//prd insert
	public int prdlist_insert(prd_dao dao,ArrayList<String> filesave) {
		
		Map<String, String> kc = new HashMap<String, String>();
		kc.put("adm_id", dao.getAdm_id());
		kc.put("cate_name", dao.getCate_name());
		kc.put("p_code", dao.getP_code());
		kc.put("p_name", dao.getP_name());
		kc.put("p_summary", dao.getP_summary());
		kc.put("p_price", String.valueOf(dao.getP_price()));
		kc.put("p_dc_percent",  String.valueOf(dao.getP_dc_percent()));
		kc.put("p_dc_money", String.valueOf(dao.getP_dc_money()));
		kc.put("p_stock",  String.valueOf(dao.getP_stock()));
		kc.put("p_use", dao.getP_use());
		kc.put("p_soldout", dao.getP_soldout());
		kc.put("p_ori_img", filesave.get(0));
		kc.put("p_thumb_img", filesave.get(1));
		kc.put("p_desc", dao.getP_desc());
		
		int result = tm2.insert("shop.prdlist_insert",kc);
		
		return result;
	}
	
	public ArrayList<String> prd_filesave(HttpServletRequest req,MultipartFile files[]) throws Exception {
		String url = req.getServletContext().getRealPath("/prdimg_upload/");
		
		ArrayList<String> arr_result = new ArrayList<String>(); 
		String ori = "";
		String fake = "";
		
		if( files[0].getSize() > 0 ) {
			int w=0;
			String filenm = new prd_imgsave().rename();
			while(w<files.length) {
				if(files[w].getSize() > 0) {
					int com = files[w].getOriginalFilename().indexOf(".");
					String wd = files[w].getOriginalFilename().substring(com);
					String fn_result = filenm+"_"+(w+1)+wd;
					
					if(w==0) {
						ori += files[w].getOriginalFilename();
						fake += fn_result;
					}else {
						ori += ","+files[w].getOriginalFilename();
						fake += ","+fn_result;
					}
					
					FileCopyUtils.copy(files[w].getBytes(),new File(url+fn_result));					
				}
				w++;
			}
		}
		
		arr_result.add(ori);
		arr_result.add(fake);
		
		return arr_result;
	}
	
	public List<prd_dao> product_selectall(String adm_id) {
		List<prd_dao> all = new ArrayList<prd_dao>();
		
	    Map<String, String> m = new HashMap<String, String>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", "0");
	    
		all = tm2.selectList("shop.product_selectall",m);
		
		return all;
	}
	
	public List<prd_dao> product_selectall2(String adm_id,String search_select,String search_word) {
		List<prd_dao> all = new ArrayList<prd_dao>();

	    Map<String, String> m = new HashMap<String, String>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", search_select);
	    m.put("search_word", search_word);
		
		all = tm2.selectList("shop.product_selectall",m);
		
		return all;
	}
	
	
}
