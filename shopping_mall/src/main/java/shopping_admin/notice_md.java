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

@Repository("noti_md")
public class notice_md {
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	public int notice_insert(notice_dao dao,ArrayList<String> filesave,String action) {
		Map<String, String> kc = new HashMap<String, String>();
		kc.put("n_fixed", dao.getN_fixed());
		kc.put("n_subject", dao.getN_subject());
		kc.put("adm_id", dao.getAdm_id());
		kc.put("n_contents", dao.getN_contents());
		kc.put("n_viewcount", "0");
		
		int result = 0;
		if(action == "insert") {
			kc.put("n_ori_img", filesave.get(0));
			kc.put("n_save_img", filesave.get(1));
			result = tm2.insert("shop.notice_insert",kc);
		}
//		else if(action == "update") {
//			result = tm2.update("shop.product_update",kc);
//		}
		
		
		return result;
	}
	
	public ArrayList<String> noti_filesave(
			HttpServletRequest req,
			MultipartFile files[]) throws Exception {
		String url = req.getServletContext().getRealPath("/noti_file_upload/");
		
		ArrayList<String> arr_result = new ArrayList<String>(); 
		String ori = "";
		String fake = "";
		
		int w=0;
		String filenm = new common_filesave().rename();
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
	
	//data오류시 저장된 파일 다시 삭제
	public void noti_filesave_fail(HttpServletRequest req,String savenm) {
		String wurl = req.getServletContext().getRealPath("/noti_file_upload/");
		String savaarr[] = savenm.split(","); 
		
		int w=0;
		while(w<savaarr.length) {
			String filenm = savaarr[w];
			
			File fe = new File(wurl+filenm);
			fe.delete();
			
			w++;
		}
	}
	
	public int notice_selectcount(String adm_id) {
		Map<String, Object> kc = new HashMap<String, Object>();
		kc.put("adm_id", adm_id);
		kc.put("part", 1);
		int result = tm2.selectOne("shop.notice_selectcount",kc);
		
		return result;
	}
	
	public int notice_selectfixed(String adm_id) {
		Map<String, Object> kc = new HashMap<String, Object>();
		kc.put("adm_id", adm_id);
		kc.put("n_fixed", "Y");
		kc.put("part", 2);
		
		int result = tm2.selectOne("shop.notice_selectcount",kc);
		
		return result;
	}
	
	public List<prd_dao> notice_selectall(String adm_id,int startpg,int pageno) {
		List<prd_dao> all = new ArrayList<prd_dao>();
		
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("adm_id", adm_id);
	    m.put("startpg", startpg);
	    m.put("pageno", pageno);
	    
		all = tm2.selectList("shop.notice_selectall",m);
		
		return all;
	}
	
	public void prd_file_ck_del(HttpServletRequest req,String[] oneck) {
		String wurl = req.getServletContext().getRealPath("/noti_file_upload/");
		int w=0;
		while(w<oneck.length) {
			notice_dao selectdata = this.notice_selectone(oneck[w]);
			int ww=0;
			while(ww<selectdata.getN_save_img().split(",").length) { 
				String filenm = selectdata.getN_save_img().split(",")[ww];
				File fe = new File(wurl+filenm);
				fe.delete();
				
				ww++;
			}
			
			w++;
		}
	}
	
	public notice_dao notice_selectone(String no) {
		notice_dao result = tm2.selectOne("shop.notice_selectone",no);
		
		return result;
	}
	
	public int notice_del(String[] oneck) {
	    int result = 0;
	    int w=0;
	    while(w<oneck.length) {
	        result += tm2.delete("shop.notice_del",oneck[w]);	
	        w++;
	    }

	    return result;
	}
	
	public int notice_update(notice_dao dao,ArrayList<String> filesave,String action) {
		Map<String, Object> kc = new HashMap<String, Object>();
		kc.put("n_no", dao.getN_no());
		kc.put("n_fixed", dao.getN_fixed());
		kc.put("n_subject", dao.getN_subject());
		kc.put("adm_id", dao.getAdm_id());
		kc.put("n_contents", dao.getN_contents());
		
		if(action=="nochange") {
			kc.put("part", 1);
		}else {
			kc.put("part", 2);
			kc.put("n_ori_img", filesave.get(0));
			kc.put("n_save_img", filesave.get(1));
		}
		int result = tm2.insert("shop.notice_update",kc);
		
		return result;
	}
	
	public int notice_update_count(int count,String no) {
		count=count+1;
		Map<String, Object> kc = new HashMap<String, Object>();
		kc.put("n_viewcount", count);
		kc.put("n_no", no);
		
		int result = tm2.update("shop.notice_update_count",kc);
		
		return result;
	}
	
}
