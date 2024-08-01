package shopping_admin;

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

@Controller
public class prd_controller extends pw_md{
	@Resource(name = "prd_md") 
	private product_md pm;
	private HttpSession session = null;
	
	/*--- product list ---*/
	@GetMapping("/admin/product_list.do")
	public String product_list() {
		
		return "/product_list";
	}
	
	@GetMapping("/admin/product_write.do")
	public String product_write() {
		
		return "/product_write";
	}
	
	@GetMapping("/admin/cate_list.do")
	public String cate_list(HttpServletResponse res,HttpServletRequest req,Model m) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			if(adm_id==null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인 해주세요.","./admin_main.do");
			}else {
				List<cate_dao> result = pm.category_selectall(adm_id);
				m.addAttribute("result",result);
			}
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");	
		}
		return "/cate_list";
	}
	
	@GetMapping("/admin/cate_write.do")
	public String cate_write(
			HttpServletResponse res,
			HttpServletRequest req,
			Model m
		) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			
			if(adm_id == null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인해주세요.","./index.jsp");
			}else {
				m.addAttribute("adm_id",adm_id);
			}
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		
		
		return "/cate_write";
	}
	
	@PostMapping("/admin/catewrite_ok.do")
	public void catewrite_ok(
			HttpServletResponse res,
			@ModelAttribute("cate") cate_dao catedao
		) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			int callback = pm.category_insert(catedao);
			if(callback > 0) {
				this.golocation(res, "카테고리 등록이 완료되었습니다", "./cate_list.do");
			}
			
		}catch(DataIntegrityViolationException e2) {
			this.gohistory(res,"대메뉴 코드,대메뉴명은 중복 등록이 불가능합니다.");
		}catch(Exception e){
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}

	}
	
	@PostMapping("/admin/cate_deleteok.do")
	public void cate_deleteok(
			String[] cateck,HttpServletResponse res
		) throws Exception{
	    res.setContentType("text/html;charset=utf-8");
	    
	    try {
	        int callback = pm.category_del(cateck);
	        if(callback > 0) {
	        	this.golocation(res, "삭제 완료했습니다", "./cate_list.do");
	        }else {
	        	this.golocation(res, "데이터 오류로 인해 삭제 실패했습니다", "./cate_list.do");
	        }
	    }catch(Exception e) {
	    	this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
	    }
	}
	
	@GetMapping("/admin/cate_modify.do")
	public String cate_modify(
			String c_no,
			Model m,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("cate") cate_dao catedao
		) throws Exception {
		try {
			this.session = req.getSession();
			String adm_id = (String)this.session.getAttribute("adm_id");
			
			if(adm_id == null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인해주세요.","./index.jsp");
			}else {
				cate_dao callback = pm.category_selectone(c_no);
				m.addAttribute("result",callback);
			}
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		
		return "/cate_modify";
	}
	
	@PostMapping("/admin/catemodify_ok.do")
	public void catemodify_ok(
			HttpServletResponse res,
			@ModelAttribute("cate") cate_dao catedao
		) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			int callback = pm.category_update(catedao);
			if(callback > 0) {
				this.golocation(res, "카테고리 수정이 완료되었습니다", "./cate_list.do");
			}
			
		}catch(DataIntegrityViolationException e2) {
			this.gohistory(res,"대메뉴 코드,대메뉴명은 중복 등록이 불가능합니다.");
		}catch(Exception e){
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}

	}
	

	
}
