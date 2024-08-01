package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("admlist_md")
public class adminlist_md {
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	@Resource(name="sqsha3")
	private sq_sha3 s3;
	
	
	/*----------join,login---------*/
	//회원가입 insert
	public int admlist_insert(adminlist_dao dao) {
		Map<String, String> kc = new HashMap<String, String>();
		kc.put("adm_id", dao.getAdm_id());
		kc.put("adm_pass", s3.sq_sha3(dao.getAdm_pass()));
		kc.put("adm_name", dao.getAdm_name());
		kc.put("adm_email", dao.getAdm_email());
		kc.put("adm_phone", dao.getAdm_phone().replaceAll(",",""));
		kc.put("adm_position1", dao.getAdm_position1());
		kc.put("adm_position2", dao.getAdm_position2());
		kc.put("adm_aprove", "N");
		
		int result = tm2.insert("shop.admlist_insert",kc);
		
		return result;
	}
	
	//로그인, 회원가입시 중복체크
	public int admlist_selectone(adminlist_dao dao) {
		Map<String, String> kc = new HashMap<String, String>();
		
		kc.put("adm_id", dao.getAdm_id());
		kc.put("adm_pass", s3.sq_sha3(dao.getAdm_pass()));

		int result = tm2.selectOne("shop.admlist_selectid",kc);
		
		return result; 
	}
	
	public adminlist_dao admlist_login(adminlist_dao dao) {
		Map<String, String> kc = new HashMap<String, String>();
		
		kc.put("adm_id", dao.getAdm_id());
		kc.put("adm_pass", s3.sq_sha3(dao.getAdm_pass()));

		adminlist_dao result = tm2.selectOne("shop.admlist_login",kc);
		
		return result;
	}
	
	public List<adminlist_dao> admlist_selectall(){
	    List<adminlist_dao> all = new ArrayList<adminlist_dao>();
	    all = tm2.selectList("shop.admlist_selectall");

		return all;
	}
	
	public int admlist_apv_update(adminlist_dao dao) {
		Map<String, String> kc = new HashMap<String, String>();
		
		kc.put("adm_no", String.valueOf(dao.getAdm_no()));
		kc.put("adm_aprove", dao.getAdm_aprove());
		
		int result = tm2.update("shop.admlist_apv_update",kc);
		
		return result;
	}
	
	
	/*----------site info---------*/
	
	public int admsiteinfo_insert_mj(mj_set_dao mjdao) {
		int r1 = tm2.insert("shop.mjdao_insert",mjdao);
		return r1;
	}
	
	public int admsiteinfo_insert_mb(mb_set_dao mbdao) {
		int r2 = tm2.insert("shop.mbdao_insert",mbdao);
		return r2;
	}
	
	public int admsiteinfo_insert_mp(mp_set_dao mpdao) {
		int r3 = tm2.insert("shop.mpdao_insert",mpdao);
		return r3;
	}
	
	public int admsiteinfo_deleteall(String adm_id) {
		int result = tm2.insert("shop.mpdao_insert",adm_id);
		return result;
	}
	
	public mj_set_dao admsiteinfo_select_mj(String adm_id) {
		mj_set_dao result = tm2.selectOne("shop.mjset_selectone",adm_id);
		
		return result;
	}
	
	public mb_set_dao admsiteinfo_select_mb(String adm_id) {
		mb_set_dao result = tm2.selectOne("shop.mbset_selectone",adm_id);
		
		return result;
	}
	
	public mp_set_dao admsiteinfo_select_mp(String adm_id) {
		mp_set_dao result = tm2.selectOne("shop.mpset_selectone",adm_id);
		
		return result;
	}
	
	public int admsiteinfo_select_jbp(String adm_id) {
		int result = tm2.selectOne("shop.mjset_selectcount",adm_id);
		
		return result;
	}
	
	public int setinfo_mj_update(mj_set_dao mjdao) {
		int u1 = tm2.update("shop.setinfo_mj_update",mjdao);
		return u1;
	}
	
	public int setinfo_mb_update(mb_set_dao mbdao) {
		int u2 = tm2.update("shop.setinfo_mb_update",mbdao);
		return u2;
	}
	
	public int setinfo_mp_update(mp_set_dao mpdao) {
		int u3 = tm2.update("shop.setinfo_mp_update",mpdao);
		return u3;
	}

}
