package shopping_shop;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shopping_admin.adminlist_md;
import shopping_admin.common_md;
import shopping_admin.mj_set_dao;
import shopping_admin.notice_dao;
import shopping_admin.term_dao;


@Controller
public class shop_controller extends shopping_admin.common_md{
	PrintWriter pw = null;
	String shopname = null;
	private HttpSession session = null;
	
	@Resource(name = "shop_md") 
	private shop_md sm;
	
	@Resource(name = "admlist_md") 
	private adminlist_md al_m;
	
	@GetMapping("/{shopname}/nosite.do")
	public String nosite(Model m) {
		
		return "/shop/nosite";
	}
	
	@GetMapping("/{shopname}/index.do")
	public String index(Model m,HttpServletRequest req,HttpServletResponse res) {
		this.shopname = this.setShopSession(req, res);
		m.addAttribute("shopname",this.shopname);
		
		return "/shop/index";
	}
	
	@GetMapping("/{shopname}/login.do")
	public String login(String no,Model m,
			@ModelAttribute("mjdao") mj_set_dao dao,
			HttpServletRequest req, HttpServletResponse res
			) {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.shopname = this.setShopSession(req, res);
			
			
		}catch(Exception e) {
			
		}
		
		return "/shop/login";
	}
	
	@GetMapping("/{shopname}/agree.do")
	public String agree(
			String no,Model m,
			HttpServletRequest req, HttpServletResponse res
			) {
		this.shopname = this.setShopSession(req, res);
		try {
			this.session = req.getSession();
			String adm_id = (String)session.getAttribute("adm_id");
			
			term_dao callback = al_m.select_term(adm_id);
			m.addAttribute("term",callback);

		}catch(Exception e) {
			
		}
		
		return "/shop/agree";
	}
	
	@RequestMapping(value = "/{shopname}/join.do")
	public String login(
			@ModelAttribute("memberdao") member_dao dao,Model m,
			HttpServletRequest req,HttpServletResponse res
			) {
		res.setContentType("text/html; charset=UTF-8");
		this.shopname = this.setShopSession(req, res);
		this.session = req.getSession();
		try {
			if(dao.getM_agree_mail()==null || dao.getM_agree_sms()==null) {
				this.golocation(res, "약관 동의를 먼저 해주세요.", "./agree.do");
			}else {
				String adm_id = (String)session.getAttribute("adm_id");
				
				m.addAttribute("agree",dao);
				m.addAttribute("adm_id",session.getAttribute("adm_id"));				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/shop/join";
	}
}
