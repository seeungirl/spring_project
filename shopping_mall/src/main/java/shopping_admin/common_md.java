package shopping_admin;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping_shop.shop_md;

public class common_md {
	PrintWriter pw = null;
	public void golocation(HttpServletResponse res,String msg,String url) throws Exception {
		this.pw=res.getWriter();
		this.pw.write("<script>"
				+ "alert('"+ msg +"');"
				+ "location.href='"+ url +"';"
				+ "</script>");
		this.pw.close();
	}
	
	public void gohistory(HttpServletResponse res,String msg) throws Exception {
		this.pw=res.getWriter();
		this.pw.write("<script>"
				+ "alert('"+ msg +"');"
				+ "history.go(-1);"
				+ "</script>");
		this.pw.close();
	}
	
	public void onlygolocation(HttpServletResponse res,String url) throws Exception {
		this.pw=res.getWriter();
		this.pw.write("<script>"
				+ "location.href='"+ url +"';"
				+ "</script>");
		this.pw.close();
	}
	
	private HttpSession session = null;
	protected String getsession(HttpServletRequest req) {
		this.session = req.getSession();
		String session_adm_id = (String)this.session.getAttribute("adm_id");
		
		return session_adm_id;
	}
	
	@Resource(name = "shop_md") 
	private shop_md sm;
	
	protected String setShopSession(HttpServletRequest req,HttpServletResponse res) {
		String shopname="";
		String adm_id="";
		
		this.session = req.getSession();
		
		List<mj_set_dao> callback = sm.select_shopname_all();
		String nowpath = req.getRequestURI().split("/")[1];
		
		int count=0;
		int w=0;
		while(w<callback.size()) {
			if(callback.get(w).getMs_mallname().equals(nowpath)) {
				shopname=callback.get(w).getMs_mallname();
				adm_id = callback.get(w).getAdm_id();
				count++;
			}
			w++;
		}
		if(count == 1) {
			this.session.setAttribute("shopname", shopname);
			this.session.setAttribute("adm_id", adm_id);
			this.session.setMaxInactiveInterval(1800);
		}else {
			try {
				this.onlygolocation(res,"./nosite.do");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		return shopname;
	}
}
