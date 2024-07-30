package shopping_admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;

public class adminlist_hashmap {
	public Map<String, String> makeMap(){
		adminlist_dao dao = new adminlist_dao();
		Map<String, String> kc = new HashMap<String, String>();
		
		kc.put("adm_id", dao.getAdm_id());
//		kc.put("adm_pass", s3.sq_sha3(dao.getAdm_pass()));
		kc.put("adm_name", dao.getAdm_name());
		kc.put("adm_email", dao.getAdm_email());
		kc.put("adm_phone", dao.getAdm_phone().replaceAll(",",""));
		kc.put("adm_position1", dao.getAdm_position1());
		kc.put("adm_position2", dao.getAdm_position2());
		
		return kc;
	}
}
