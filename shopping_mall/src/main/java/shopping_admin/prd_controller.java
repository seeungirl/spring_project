package shopping_admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class prd_controller extends common_md{
	@Resource(name = "prd_md") 
	private product_md pm;
	int pageno = 10;
	
	/*--- product list ---*/
	@GetMapping("/admin/product_list.do")
	public String product_list(
			HttpServletResponse res,HttpServletRequest req,Model m,
			@RequestParam(value="",required = false) Integer page,
	        @RequestParam(defaultValue = "", required = false) String search_select,
	        @RequestParam(defaultValue = "", required = false) String search_word
			) throws Exception{
		
		res.setContentType("text/html; charset=UTF-8");
		
		int startpg = 0;
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인 해주세요.","./admin_main.do");
			}else {
				int ctn = pm.product_selectcount(adm_id);
				m.addAttribute("total",ctn);
				
				if(page==null || page==1) { //Integer이어야 null 사용 가능
					startpg = 0;
				}else {
					startpg = (page-1)*pageno;
				}
				m.addAttribute("startpg",startpg);
				
				List<prd_dao> result = null;
				if(search_select.equals("") && search_word.equals("")) {
					result = pm.product_selectall(adm_id,startpg,pageno);	
				}else {
					result = pm.product_selectall2(adm_id,search_select,search_word,startpg,pageno);
		            m.addAttribute("search_select",search_select);
		            m.addAttribute("search_word",search_word);
				}
				
				m.addAttribute("result",result);
			}
		}catch(Exception e) {
			e.printStackTrace();
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");	
		}
		
		return "/product_list";
	}
	
	@GetMapping("/admin/product_write.do")
	public String product_write(
			Model m,HttpServletRequest req,HttpServletResponse res
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인 해주세요.","./admin_main.do");
			}else {
				List<cate_dao> result = pm.category_select_cate(adm_id);
				m.addAttribute("catelist",result);	
				m.addAttribute("adm_id",adm_id);
			}
			
		}catch(Exception e) {
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}		
		
		return "/product_write";
	}
	
	@GetMapping("/admin/duplication_prdcodeck.do")
	public String duplication_prdcodeck(
			HttpServletResponse res,
			HttpServletRequest req,
			@ModelAttribute("prdlist") prd_dao dao) 
		throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int callback = 0;
		
		try {
			this.pw = res.getWriter();
			callback = pm.prdlist_selectone(dao);
		}catch(Exception e) {
			callback = -1;
		}finally {
			this.pw.print(callback);
			this.pw.close();
		}
		
		return null;
	}
	
	@PostMapping("/admin/prdinsert_ok.do")
	public void prdinsert_ok(
			HttpServletResponse res, HttpServletRequest req,
			@RequestParam("files") MultipartFile files[],
			@ModelAttribute("prdlist") prd_dao dao) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		try {
			ArrayList<String> filesave = pm.prd_filesave(req,files);
			if(filesave.size() == 2) {
				int callback = pm.prdlist_insert(dao,filesave,"insert");
				if(callback > 0) {
					this.golocation(res,"상품 등록이 완료되었습니다","./product_list.do");
				}else {
					this.golocation(res,"데이터 오류로 상품 등록에 실패했습니다.","./product_list.do");
				}
			}
		}catch(DataIntegrityViolationException e2) {
			this.gohistory(res,"해당 정보로 이미 가입된 상품이 있습니다.");
		}catch(Exception e) {
			this.golocation(res,"데이터 오류로 상품 등록에 실패했습니다.","./product_list.do");
		}
	}
	
	@PostMapping("/admin/prd_deleteok.do")
	public void prd_deleteok(
			String[] oneck,HttpServletResponse res, HttpServletRequest req
		) throws Exception{
	    res.setContentType("text/html;charset=utf-8");
	    
	    try {
	    	pm.prd_file_ck_del(req,oneck);	    	
	        int callback = pm.product_del(oneck);
	        if(callback > 0) {
	        	this.golocation(res, "삭제 완료했습니다", "./product_list.do");
	        }else {
	        	this.golocation(res, "데이터 오류로 인해 삭제 실패했습니다", "./product_list.do");
	        }
	    }catch(Exception e) {
	    	System.out.println(e);
	    	this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
	    }
	}
	
	@GetMapping("/admin/product_modify.do")
	public String product_modify(
			String p_no,
			Model m,
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("prdlist") prd_dao dao
		) throws Exception {
		try {
			res.setContentType("text/html; charset=UTF-8");
			
			String adm_id = this.getsession(req);
			if(adm_id == null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인해주세요.","./index.jsp");
			}else {
				List<cate_dao> result = pm.category_select_cate(adm_id);
				m.addAttribute("catelist",result);	
				m.addAttribute("adm_id",adm_id);
				
				prd_dao callback = pm.product_selectone(p_no);
				m.addAttribute("result",callback);
			}
		}catch(Exception e) {
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}
		
		return "/product_modify";
	}
	
	@PostMapping("/admin/prdmodify_ok.do")
	public void prdmodify_ok(
			HttpServletResponse res, HttpServletRequest req,
			String del_p_thumb_img[], String old_p_ori_img[], 
			String old_p_thumb_img[], @RequestParam("files") MultipartFile files[],
			@ModelAttribute("prdlist") prd_dao dao
		) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		
		try {
			//일단 나중에 다시
			ArrayList<String> result = pm.file_modify(req, del_p_thumb_img, old_p_ori_img,old_p_thumb_img, files);
//			System.out.println(result);

			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
//			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}

	}
	
	
	@GetMapping("/admin/cate_list.do")
	public String cate_list(
			HttpServletResponse res,HttpServletRequest req,Model m,
			@RequestParam(value="",required = false) Integer page,
	        @RequestParam(defaultValue = "", required = false) String search_select,
	        @RequestParam(defaultValue = "", required = false) String search_word
			) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		
		int startpg = 0;
		try {
			String adm_id = this.getsession(req);
			if(adm_id==null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인 해주세요.","./admin_main.do");
			}else {
				int ctn = pm.category_selectcount(adm_id);
				m.addAttribute("total",ctn);
				
				if(page==null || page==1) { //Integer이어야 null 사용 가능
					startpg = 0;
				}else {
					startpg = (page-1)*pageno;
				}
				m.addAttribute("startpg",startpg);
				
				List<cate_dao> result = null;
				if(search_select.equals("") && search_word.equals("")) {
					result = pm.category_selectall(adm_id,startpg,pageno);	
				}else {
					result = pm.category_selectall2(adm_id,search_select,search_word,startpg,pageno);
					
		            m.addAttribute("search_select",search_select);
		            m.addAttribute("search_word",search_word);
				}
				
				m.addAttribute("result",result);
			}
		}catch(Exception e) {
			System.out.println(e);
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
			String adm_id = this.getsession(req);
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
			String[] oneck,HttpServletResponse res,HttpServletRequest req,
			@ModelAttribute("cate") cate_dao catedao
		) throws Exception{
	    res.setContentType("text/html;charset=utf-8");
	    
	    try {
	    	String adm_id = this.getsession(req);
			if(adm_id == null) {
				this.golocation(res,"쇼핑몰 관리자로 로그인해주세요.","./index.jsp");
			}else {
				int callback = pm.category_del(oneck,adm_id);
				
				if(callback > 0) {
					this.golocation(res, "삭제 완료했습니다", "./cate_list.do");
				}else {
					if(callback==-1) {
						this.golocation(res,"카테고리에 속한 상품이 모두 변경 또는 삭제 되었을 경우만 삭제 가능합니다. 확인 후 삭제해주세요.","./cate_list.do");
					}else {
						this.golocation(res, "데이터 오류로 인해 삭제 실패했습니다", "./cate_list.do");						
					}
				}
			}
	    }catch(Exception e) {
	    	System.out.println(e);
//	    	this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
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
			res.setContentType("text/html; charset=UTF-8");
			
			String adm_id = this.getsession(req);
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
			e2.printStackTrace();
			this.gohistory(res,"해당 대메뉴 코드에 동일한 대메뉴 코드및 이름이 존재합니다.123");
		}catch(Exception e){
			System.out.println(e);
			this.golocation(res,"잘못된 접근입니다.","./admin_main.do");
		}

	}
	

	
}
