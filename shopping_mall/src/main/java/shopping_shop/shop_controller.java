package shopping_shop;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shopping_admin.adminlist_md;
import shopping_admin.common_md;
import shopping_admin.notice_dao;


@Controller
public class shop_controller extends shopping_admin.common_md{
	PrintWriter pw = null;
	String shopname = "shop1";
	
	@Resource(name = "shop_md") 
	private shop_md sm;
	
	@GetMapping("/shop/login.do")
	public String login(String no,Model m) {
		try {
			
		}catch(Exception e) {
			
		}
		
		return "/shop/login";
	}
	
	@GetMapping("/shop/join.do")
	public String join(String no,Model m) {
		try {
			
		}catch(Exception e) {
			
		}
		
		return "/shop/join";
	}
	
	@GetMapping("/{shopname}/agree.do")
	public String agree(
			String no,Model m,
			HttpServletRequest req
			) {
		try {
			m.addAttribute("shopname",shopname);
			
			String adm_id = this.getsession(req);
			System.out.println(adm_id);
		}catch(Exception e) {
			
		}
		
		return "/shop/agree";
	}
}
