package shopping_admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class notice_controller extends common_md{
	@Resource(name = "noti_md") 
	private notice_md nm;
	
	/*--- notice_list ---*/
	@GetMapping("/admin/notice_list.do")
	public String notice_list(
			HttpServletResponse res,HttpServletRequest req, Model m,
			@RequestParam(value="",required = false) Integer page
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		int pageno = 10;
		int startpg = 0;
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"로그인 해주세요.","./index.jsp");				
			}else {
				int fixctn = nm.notice_selectfixed(adm_id);
				m.addAttribute("fixctn",fixctn);
				
				int ctn = nm.notice_selectcount(adm_id);
				m.addAttribute("total",ctn);
				if(page==null || page==1) { //Integer이어야 null 사용 가능
					startpg = 0;
				}else {
					startpg = (page-1)*pageno;
				}
				m.addAttribute("startpg",startpg);
				
				List<prd_dao> result = nm.notice_selectall(adm_id,startpg,pageno);
				m.addAttribute("result",result);
			}
		}catch(Exception e) {
			System.out.println(e);
//			this.golocation(res,"잘못된 접근입니다.","./index.jsp");
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
			@ModelAttribute("notilist") notice_dao dao,
			@RequestParam("nfile") MultipartFile files[]
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		ArrayList<String> filesave = nm.noti_filesave(req,files);
		try {
			if(dao.getN_fixed() == null) {
				dao.setN_fixed("N");
			}else {
				dao.setN_fixed("Y");
			}
			if(filesave.size() == 2) {
				int callback = nm.notice_insert(dao,filesave,"insert");
				if(callback > 0) {
					this.golocation(res,"공지사항 등록이 완료되었습니다","./notice_list.do");
				}else {
					nm.noti_filesave_fail(req,filesave.get(1));
					this.golocation(res,"데이터 오류로 상품 등록에 실패했습니다.","./notice_list.do");
				}
			}
		}catch(Exception e) {
			nm.noti_filesave_fail(req,filesave.get(1));
			this.golocation(res,"잘못된 접근입니다.","./notice_list.do");
		}
	}
	
	@PostMapping("/admin/noti_deleteok.do")
	public void noti_deleteok(
			String[] oneck,HttpServletResponse res, HttpServletRequest req
		) throws Exception{
	    res.setContentType("text/html;charset=utf-8");
	    
	    try {
	    	nm.prd_file_ck_del(req,oneck);	    	
	        int callback = nm.notice_del(oneck);
	        if(callback > 0) {
	        	this.golocation(res, "삭제 완료했습니다", "./notice_list.do");
	        }else {
	        	this.golocation(res, "데이터 오류로 인해 삭제 실패했습니다", "./notice_list.do");
	        }
	    }catch(Exception e) {
	    	System.out.println(e);
	    	this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
	    }
	}
	
	@GetMapping("/admin/notice_view.do")
	public String notice_view(String no,Model m, HttpServletRequest req) {
		try {
			notice_dao callback = nm.notice_selectone(no);
			m.addAttribute("result",callback);
			HttpSession session = req.getSession();
			
			String ss_noticount = (String)session.getAttribute("noti_count_"+no);
			if(ss_noticount == null) {
				session.setAttribute("noti_count_"+no , "1");
				session.setMaxInactiveInterval(1800);
				
				notice_dao result = nm.notice_selectone(no);
				int count = result.getN_viewcount();
				int count_cb = nm.notice_update_count(count, no);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return "/notice_view";
	}
	
	@GetMapping("/admin/notice_modify.do")
	public String notice_modify(String no,Model m) {
		try {
			notice_dao callback = nm.notice_selectone(no);
			m.addAttribute("result",callback);
		}catch(Exception e) {
			
		}
		
		return "/notice_modify";
	}
	
	@PostMapping("/admin/notice_modifyok.do")
	public void notice_modifyok(
			String n_old_ori, String selected_filename,String n_no[],
			HttpServletRequest req, HttpServletResponse res,
			@ModelAttribute("notilist") notice_dao dao,
			@RequestParam("nfile") MultipartFile files[]
			) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		try {
			if(dao.getN_fixed() == null) {
				dao.setN_fixed("N");
			}else {
				dao.setN_fixed("Y");
			}
			
			ArrayList<String> filesave = null;
			int callback = 0;
			if(n_old_ori.equals(selected_filename)) { //첨부파일 변경없이 그대로 가는 경우
				callback = nm.notice_update(dao,filesave,"nochange");
			}else {
				nm.prd_file_ck_del(req,n_no);
				filesave = nm.noti_filesave(req,files);
				callback = nm.notice_update(dao,filesave,"change");
			}
			
			if(callback > 0) {
				this.golocation(res, "수정이 완료되었습니다", "./notice_view.do?no="+dao.getN_no());
			}else {
				this.golocation(res, "데이터 오류로 수정에 실패했습니다. 다시 시도해주세요.", "./notice_view.do?no="+dao.getN_no());
			}
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
	}
}
