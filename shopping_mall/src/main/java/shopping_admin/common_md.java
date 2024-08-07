package shopping_admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private HttpSession session = null;
	protected String getsession(HttpServletRequest req) {
		this.session = req.getSession();
		String session_adm_id = (String)this.session.getAttribute("adm_id");
		
		return session_adm_id;
	}
}
