package shopping_shop;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import shopping_admin.adminlist_md;
import shopping_admin.notice_dao;


@Controller
public class shop_controller {
	PrintWriter pw = null;
	
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
	
	@GetMapping("/shop/agree.do")
	public String agree(String no,Model m) {
		try {
			
		}catch(Exception e) {
			
		}
		
		return "/shop/agree";
	}
}
