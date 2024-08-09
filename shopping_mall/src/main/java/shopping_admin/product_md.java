package shopping_admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	public List<cate_dao> category_select_cate(String adm_id){
		List<cate_dao> all = new ArrayList<cate_dao>();
		all = tm2.selectList("shop.category_select_cate",adm_id);
		
		return all;
	}
	
	public List<cate_dao> category_selectall(String adm_id,int startpg,int pageno) {
		List<cate_dao> all = new ArrayList<cate_dao>();
		
		Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", "0");
	    m.put("startpg", startpg);
	    m.put("pageno", pageno);
	    
		all = tm2.selectList("shop.category_selectall",m);
		
		return all;
	}
	
	
	public List<cate_dao> category_selectall2(String adm_id,String search_select,String search_word,int startpg,int pageno) {
		List<cate_dao> all = new ArrayList<cate_dao>();

	    //mapper에 인자값은 단 한개의 값만 적용할 수 있음 
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", search_select);
	    m.put("search_word", search_word);
	    m.put("startpg", startpg);
	    m.put("pageno", pageno);

		all = tm2.selectList("shop.category_selectall",m);
		
		return all;
	}
	
	public int category_del(String[] cateck,String adm_id) {
		int result = 0;
	    int count = 0;
	    int w=0;
	    while(w<cateck.length) {
	    	cate_dao all = tm2.selectOne("shop.category_select_catename",cateck[w]);
	    	int callback = this.category_del_prdck(adm_id,all.getCate_name());
	    	count += callback;
	    	
	        w++;
	    }
	    
	    if(count == 0) {
	    	int i=0;
	    	while(i<cateck.length) {
	    		result += tm2.delete("shop.category_del",cateck[i]);
	    		i++;
	    	}
	    }else {
	    	result = -1;
	    }

	    return result;
	}
	
	public int category_del_prdck(String adm_id,String cate_name) {
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("cate_name", cate_name);
		
		int result =  tm2.selectOne("shop.category_del_prdck",m);
		
		return result;
	}
	
	public cate_dao category_selectone(String no) {
		cate_dao result = tm2.selectOne("shop.category_selectone",no);
		
		return result;
	}
	
	public int category_selectcount(String adm_id) {
		int result = tm2.selectOne("shop.category_selectcount",adm_id);
		
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
	public int prdlist_insert(prd_dao dao,ArrayList<String> filesave,String action) {
		
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
		kc.put("p_desc", dao.getP_desc());
		kc.put("p_no", String.valueOf(dao.getP_no()));

		int result = 0;
		if(action == "insert") {
			kc.put("p_ori_img", filesave.get(0));
			kc.put("p_thumb_img", filesave.get(1));
			result = tm2.insert("shop.prdlist_insert",kc);
		}else if(action == "update") {
			result = tm2.update("shop.product_update",kc);
		}
		
		return result;
	}
	
	public ArrayList<String> prd_filesave(HttpServletRequest req,MultipartFile files[]) throws Exception {
		String url = req.getServletContext().getRealPath("/prdimg_upload/");
		
		ArrayList<String> arr_result = new ArrayList<String>(); 
		String ori = "";
		String fake = "";
		
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
		
		arr_result.add(ori);
		arr_result.add(fake);
		
		return arr_result;
	}
	
	public ArrayList<String> file_modify(HttpServletRequest req,
			String del_p_thumb_img[], String old_p_ori_img[],
			String old_p_thumb_img[], MultipartFile files[]) throws Exception{
		ArrayList<String> del_ti = new ArrayList<String>();
		ArrayList<String> newimg = new ArrayList<String>();
		ArrayList<String> ori_ti = new ArrayList<String>();
		
		int len = del_p_thumb_img.length; 
		int w=0;
		while(w<len) {
			if(del_p_thumb_img[w] != "") {
				del_ti.add(del_p_thumb_img[w]);
			}
			
			if(old_p_ori_img[w] != "") {
				newimg.add(old_p_ori_img[w]);
			}
			
			if(old_p_thumb_img[w] != "") {
				ori_ti.add(old_p_thumb_img[w]);
			}else {
				ori_ti.add("");
			}
			
			if(files[w].getOriginalFilename() !="") {
				newimg.add(files[w].getOriginalFilename());	
			}
			w++;
		}

		//새로 db에 저장할 파일명 string ,으로
		int a=0;
		String newimg_result="";
		while(a<newimg.size()) {
			if(a==0) {
				newimg_result += newimg.get(a);
			}else {
				newimg_result += ","+newimg.get(a);
			}
			a++;
		}
		
		int ot=0;
		String orithumimg_result="";
		ArrayList<String> newfile = this.prd_filesave(req, files);
		int count=0;
		
//		while(ot<ori_ti.size()) {
//			if(ori_ti.get(ot)=="") {
//				ori_ti.set(ot,newfile.get(1).split(",")[count]);
//				
//				count++;
//			}
//			
//			if(ot==0) {
//				orithumimg_result += ori_ti.get(ot);
//			}else {
//				orithumimg_result += ","+ori_ti.get(ot);
//			}
//			ot++;
//		}
//		System.out.println(Arrays.asList(files));
		System.out.println(orithumimg_result);
		
		//새로 업로드된 파일 random모듈 돌려서 db에 저장,실제파일 저장할거
		
		
		ArrayList<String> real_result = new ArrayList<String>();
		real_result.add(newimg_result);
		
		
		real_result.add(newfile.get(1));

		//real파일 삭제 모듈로 보낼거
		int b=0;
		String delfile_result="";
		while(b<del_ti.size()) {
			if(b==0) {
				delfile_result += del_ti.get(b);
			}else {
				delfile_result += ","+del_ti.get(b);
			}
			b++;
		}
		
		return real_result;
	}
	
	public List<prd_dao> product_selectall(String adm_id,int startpg,int pageno) {
		List<prd_dao> all = new ArrayList<prd_dao>();
		
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", "0");
	    m.put("startpg", startpg);
	    m.put("pageno", pageno);
	    
		all = tm2.selectList("shop.product_selectall",m);
		
		return all;
	}
	
	public List<prd_dao> product_selectall2(String adm_id,String search_select,String search_word,int startpg,int pageno) {
		List<prd_dao> all = new ArrayList<prd_dao>();

	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("search_select", search_select);
	    m.put("search_word", search_word);
	    m.put("startpg", startpg);
	    m.put("pageno", pageno);
		
		all = tm2.selectList("shop.product_selectall",m);
		
		return all;
	}
	
	public void prd_file_ck_del(HttpServletRequest req,String[] oneck) {
		String wurl = req.getServletContext().getRealPath("/prdimg_upload/");
		int w=0;
		while(w<oneck.length) {
			prd_dao selectdata = this.product_selectone(oneck[w]);
			int ww=0;
			while(ww<selectdata.getP_thumb_img().split(",").length) { 
				String filenm = selectdata.getP_thumb_img().split(",")[ww];
				File fe = new File(wurl+filenm);
				fe.delete();
				
				ww++;
			}
			
			w++;
		}
	}
	
	public int product_del(String[] prdck) {
	    int result = 0;
	    int w=0;
	    while(w<prdck.length) {
	        result += tm2.delete("shop.product_del",prdck[w]);	
	        w++;
	    }

	    return result;
	}
	
	public prd_dao product_selectone(String no) {
		prd_dao result = tm2.selectOne("shop.product_selectone",no);
		
		return result;
	}
	
	public int product_selectcount(String adm_id) {
		int result = tm2.selectOne("shop.product_selectcount",adm_id);
		
		return result;
	}
	
	public int product_update(prd_dao prddao) {
		int result = tm2.update("shop.product_update",prddao);
		
		return result;
	}
	
	
	
}
