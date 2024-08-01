package shopping_admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class pw_md {
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
}
