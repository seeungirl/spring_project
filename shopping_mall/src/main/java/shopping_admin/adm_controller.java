package shopping_admin;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adm_controller {
	PrintWriter pw = null;
	
	@Resource(name = "admlist_md") 
	private adminlist_md al_m;
	
	@PostMapping("/admin/adminjoin_ok.do")
	public void adminjoin_ok(
			HttpServletResponse res,
			HttpServletRequest req,
			@ModelAttribute("admlist") adminlist_dao dao
		) throws Exception{
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		try {
			this.pw = res.getWriter();
			int callback = al_m.admlist_insert(dao);
			System.out.println(callback);
			if(callback>0){
				this.pw.write("<script>"
						+ "alert('관리자 등록이 완료되었습니다');"
						+ "location.href='./admin_main.do';"
						+ "</script>");
			}
		}catch(DataIntegrityViolationException e2) {
			this.pw.write("<script>"
					+ "alert('해당 정보로 이미 가입된 회원이 있습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('데이터 오류로 관리자 등록에 실패했습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			this.pw.close();
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
	public String adminlogin_ok(
			HttpServletResponse res,
			HttpServletRequest req,
			@ModelAttribute("admlist") adminlist_dao dao
			) throws Exception {
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		this.session = req.getSession();
		try {
			this.pw = res.getWriter();
			adminlist_dao callback = al_m.admlist_login(dao); 
			
			if(callback.getAdm_aprove().equals("Y")==true) {
				this.session.setAttribute("adm_id", callback.getAdm_id());
				this.session.setMaxInactiveInterval(1800);
				
				this.pw.write("<script>"
						+ "alert('로그인에 성공했습니다.');"
						+ "location.href='./admin_main.do';"
						+ "</script>");
			}else {
				this.pw.write("<script>"
						+ "alert('승인전 회원입니다. 관리자에게 승인을 요청하세요.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			this.pw.write("<script>"
					+ "alert('아이디와 비밀번호를 확인해주세요.');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	@GetMapping("/admin/admin_logout.do")
	public void admin_logout(HttpServletResponse res,HttpServletRequest req) {
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			this.pw = res.getWriter();
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			
			if(adm_id!=null) {
				this.session.invalidate();
				this.pw.write("<script>"
						+ "alert('정상적으로 로그아웃되셨습니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");				
			}else {
				this.pw.write("<script>"
						+ "alert('잘못된 접근입니다.');"
						+ "location.href='./admin_main.do';"
						+ "</script>");	
			}
		}catch(Exception e) {
			
		}finally {
			this.pw.close();
		}
	}
	
	@GetMapping("/admin/admin_main.do")
	public String admin_main(HttpServletResponse res,HttpServletRequest req) {
		res.setContentType("text/html; charset=UTF-8");
	
		try {
			this.pw = res.getWriter();
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			if(adm_id==null) {
				this.pw.write("<script>"
						+ "alert('로그인 하셔야 접근 가능합니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
				this.pw.close();				
			}else {
				
			}
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./index.jsp';"
					+ "</script>");
			this.pw.close();
		}
	
		return "/admin/admin_main";
	}
	
	@GetMapping("/admin/admin_master.do")
	public String admin_master(HttpServletResponse res,HttpServletRequest req) {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			System.out.println(adm_id);
			if(adm_id==null) {
				this.pw.write("<script>"
						+ "alert('최고관리자만 등록이 가능합니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
				this.pw.close();
			}
			if(adm_id.equals("master")==false) {
				this.pw.write("<script>"
						+ "alert('최고관리자만 등록이 가능합니다.');"
						+ "location.href='./admin_main.do';"
						+ "</script>");
				this.pw.close();
			}else {
				
			}
			
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./index.jsp';"
					+ "</script>");
			this.pw.close();
		}
		return "/admin/admin_master";
	}
	
	@GetMapping("/admin/admin_list.do")
	public String admin_list(HttpServletResponse res,HttpServletRequest req,Model m) {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			if(adm_id==null) {
				this.pw.write("<script>"
						+ "alert('로그인 해주세요');"
						+ "location.href='./index.jsp';"
						+ "</script>");
				this.pw.close();
			}
			if(adm_id.equals("master")==false) {
				this.pw.write("<script>"
						+ "alert('최고관리자만 등록이 가능합니다.');"
						+ "location.href='./admin_main.do';"
						+ "</script>");
				this.pw.close();
			}else {
				List<adminlist_dao> callback = al_m.admlist_selectall();
				m.addAttribute("result",callback);
			}
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./index.jsp';"
					+ "</script>");
			this.pw.close();
		}
		
		return "/admin/admin_list";
	}
	
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
			this.pw.write("<script>"
					+ "alert('잘못된 접근입니다.');"
					+ "location.href='./index.jsp';"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	@GetMapping("/admin/admin_siteinfo.do")
	public String admin_siteinfo(HttpServletResponse res) {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			//this.pw.close();
		}catch(Exception e) {
			
		}
		
		return "/admin/admin_siteinfo";
	}
	
}
