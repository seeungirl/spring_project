package shopping_admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class notice_controller extends common_md{
	@Resource(name = "noti_md") 
	private notice_md nm;
	
	/*--- notice_list ---*/
	@GetMapping("/admin/notice_list.do")
	public String notice_list(HttpServletResponse res,HttpServletRequest req) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"로그인 해주세요.","./index.jsp");				
			}else {
				
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./index.jsp");
		}
	
		return "/notice_list";
	}
	
	/*--- notice write ---*/
	@GetMapping("/admin/notice_write.do")
	public String notice_write(HttpServletResponse res,HttpServletRequest req,Model m) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"로그인 해주세요.","./index.jsp");				
			}else {
				m.addAttribute("adm_id",adm_id);
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./index.jsp");
		}
	
		return "/notice_write";
	}
	
	@PostMapping("/admin/notice_writeok.do")
	public void notice_writeok(
			HttpServletResponse res,HttpServletRequest req,
			@ModelAttribute("notilist") notice_dao dao
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			System.out.println(dao.getN_fixed());
			
			
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./index.jsp");
		}
	}
}
