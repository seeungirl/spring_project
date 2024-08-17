package shopping_admin;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adm_controller extends common_md{
	
	PrintWriter pw = null;
	
	@Resource(name = "admlist_md") 
	private adminlist_md al_m;
	
	/*--- login,join ---*/
	@PostMapping("/admin/adminjoin_ok.do")
	public void adminjoin_ok( HttpServletResponse res, HttpServletRequest req, @ModelAttribute("admlist") adminlist_dao dao) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		try {
			int callback = al_m.admlist_insert(dao);
			if(callback>0){
				this.golocation(res,"관리자 등록이 완료되었습니다","./admin_main.do");
			}
		}catch(DataIntegrityViolationException e2) {
			this.gohistory(res,"해당 정보로 이미 가입된 회원이 있습니다.");
		}catch(Exception e) {
			this.gohistory(res,"데이터 오류로 관리자 등록에 실패했습니다.");
		}
	}
	
	@GetMapping("/admin/duplication_idck.do")
	public String duplication_idck(
			HttpServletResponse res,
			HttpServletRequest req,
			@ModelAttribute("admlist") adminlist_dao dao) 
		throws Exception {
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int callback = 0;
		try {
			this.pw = res.getWriter();
			callback = al_m.admlist_selectone(dao);
		}catch(Exception e) {
			callback = -1;
		}finally {
			this.pw.print(callback);
			this.pw.close();
		}
		
		return null;
	}
	
	private HttpSession session = null;
	@PostMapping("/admin/adminlogin_ok.do")
	public String adminlogin_ok( HttpServletResponse res, HttpServletRequest req, @ModelAttribute("admlist") adminlist_dao dao ) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		this.session = req.getSession();
		try {
			adminlist_dao callback = al_m.admlist_login(dao); 
			String message = "";
			
			if(callback.getAdm_aprove().equals("Y")==true) { 
				this.session.setAttribute("adm_id", callback.getAdm_id());
				this.session.setMaxInactiveInterval(1800);
				message = dao.getAdm_id()+"님 환영합니다.";
			}else {
				message = "승인전 회원입니다. 관리자에게 승인을 요청하세요.";
			}
			this.golocation(res,message,"./admin_main.do");
		}catch(Exception e) {
			this.gohistory(res,"아이디와 비밀번호를 확인해주세요.");
		}
		return null;
	}
	
	@GetMapping("/admin/admin_logout.do")
	public void admin_logout(HttpServletResponse res,HttpServletRequest req) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			String adm_id = this.getsession(req);
			if(adm_id!=null) {
				this.session.invalidate();
				this.golocation(res,"정상적으로 로그아웃되셨습니다.","./index.jsp");
			}else {
				this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
			}
		}catch(Exception e) {
			e.printStackTrace();
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
	}
	
	/*--- admin_main ---*/
	@GetMapping("/admin/admin_main.do")
	public String admin_main(HttpServletResponse res,HttpServletRequest req) throws Exception{
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
	
		return "/admin_main";
	}
	
	/*--- admin_master ---*/
	@GetMapping("/admin/admin_master.do")
	public String admin_master(HttpServletResponse res,HttpServletRequest req) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null || adm_id.equals("master")==false) {
				this.golocation(res,"최고관리자만 등록이 가능합니다.","./admin_main.do");
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		return "/admin_master";
	}
	
	/*--- admin_master승인 ---*/
	@PostMapping("/admin/change_aprove.do")
	public String change_aprove(
			HttpServletResponse res,
			HttpServletRequest req,
			String adm_aprove,
			String adm_no,
			@ModelAttribute("admlist") adminlist_dao dao
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");	
		
		try {
			this.pw = res.getWriter();
			
			int callback = al_m.admlist_apv_update(dao);
			this.pw.print(callback);
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	/*--- admin_list ---*/
	@GetMapping("/admin/admin_list.do")
	public String admin_list(HttpServletResponse res,HttpServletRequest req,Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null || adm_id.equals("master")==false) {
				this.golocation(res,"최고관리자만 등록이 가능합니다.","./admin_main.do");
			}else {
				List<adminlist_dao> callback = al_m.admlist_selectall();
				m.addAttribute("result",callback);
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		
		return "/admin_list";
	}
	
	/*--- admin_기본설정 변경---*/
	@GetMapping("/admin/admin_siteinfo.do")
	public String admin_siteinfo(
			HttpServletResponse res,
			HttpServletRequest req,
			Model m
		) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id == null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인해주세요.","./index.jsp");
			}else {
				mj_set_dao c1 = this.al_m.admsiteinfo_select_mj(adm_id);
				mb_set_dao c2 = this.al_m.admsiteinfo_select_mb(adm_id);
				mp_set_dao c3 = this.al_m.admsiteinfo_select_mp(adm_id);
				
				m.addAttribute("adm_id",adm_id);
				m.addAttribute("mj",c1);
				m.addAttribute("mb",c2);
				m.addAttribute("mp",c3);
			}
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		
		return "/admin_siteinfo";
	}
	
	@PostMapping("/admin/adm_siteinfo_ok.do")
	public void adm_siteinfo_ok(
			HttpServletResponse res,HttpServletRequest req,String adm_id,
			@ModelAttribute("setjoin") mj_set_dao mjdao,
			@ModelAttribute("setbasic") mb_set_dao mbdao,
			@ModelAttribute("setpay") mp_set_dao mpdao
		) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		try {
			int s1 = this.al_m.admsiteinfo_select_jbp(adm_id);
			if(s1 > 0) { //수정할경우
				int u1 = al_m.setinfo_mj_update(mjdao);
				int u2 = al_m.setinfo_mb_update(mbdao);
				int u3 = al_m.setinfo_mp_update(mpdao);
				if(u1+u2+u3 == 3) {
					this.golocation(res,"수정이 완료되었습니다.","./admin_siteinfo.do");
				}else {
					this.golocation(res,"데이터 오류로 수정에 실패했습니다. 다시 시도해주세요.","./admin_siteinfo.do");
				}
			}else { //새로 작성할경우
				int c1 = al_m.admsiteinfo_insert_mj(mjdao);
				int c2 = al_m.admsiteinfo_insert_mb(mbdao);
				int c3 = al_m.admsiteinfo_insert_mp(mpdao);
				if(c1+c2+c3 == 3) {
					this.golocation(res,"저장이 완료되었습니다.","./admin_siteinfo.do");
				}else {
					int callback = this.al_m.admsiteinfo_deleteall(adm_id);
					this.golocation(res,"데이터 오류로 등록에 실패했습니다. 다시 시도해주세요.","./admin_siteinfo.do");
				}
			}
		}catch(DuplicateKeyException e2) {
			this.gohistory(res,"이미 존재하는 홈페이지 제목입니다.");
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"데이터 오류로 저장에 실패했습니다. 다시 시도해주세요.","./admin_siteinfo.do");
		}

	}
	
	/*--- shop_member_list ---*/
	@GetMapping("/admin/shop_member_list.do")
	public String shop_member_list(
			HttpServletResponse res,HttpServletRequest req,
			@ModelAttribute("termdao") term_dao termdao, Model m
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"로그인 해주세요.","./index.jsp");				
			}else {
				term_dao result = al_m.select_term(adm_id);
				m.addAttribute("term",result);
			}
		}catch(Exception e) {
			e.printStackTrace();
//			this.golocation(res,"잘못된 접근입니다.","./shop_member_list.do");
		}
	
		return "/shop_member_list";
	}
	
	@PostMapping("/admin/term_insertok.do")
	public void term_insertok(
			HttpServletResponse res,HttpServletRequest req,
			@ModelAttribute("termdao") term_dao termdao,String name
			) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"로그인 해주세요.","./index.jsp");
			}else {
				termdao.setAdm_id(adm_id);
				int callback = al_m.select_term_id(adm_id);
				int result = al_m.term_godata(termdao,callback);
				if(result > 0) {
					this.golocation(res,"저장이 완료되었습니다.","./shop_member_list.do");
				}else {
					this.golocation(res,"데이터 오류로 저장에 실패했습니다. 다시 시도해주세요.","./admin_main.do");
				}
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./shop_member_list.do");
			e.printStackTrace();
		}
		
	}
	
}
