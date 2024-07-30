package shopping_admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("admlist_md")
public class adminlist_md {
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
	
	@Resource(name="sqsha3")
	private sq_sha3 s3;
	
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

}
