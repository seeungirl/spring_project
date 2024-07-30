package com.navershop.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

@Controller
public class web_controller {
	PrintWriter pw = null;
	//ajax 통신 - CORS방식 무조건 넣어줘야함!
	
	@Resource(name="md5pass")
	private md5_pass md;
	
	
	@Resource(name="userselect")
	private user_select us;
	
	@PostMapping("/idsearch.do")
	public String idserach(
			String[] uname,String uemail,
			HttpServletResponse res
			) throws Exception{ //아이디 찾기
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			if(uname[0] == null || uemail == null) {
				this.pw.print("<script>"
						+ "alert('올바른 접근 방식이 아닙니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}else {
				ArrayList<Object> result = new user_select().search_id(uname[0],uemail);
				System.out.println(result);
			}
			
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>"
					+ "alert('Database 문제로 인하여 해당 정보가 확인되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	@PostMapping("/passmodify.do")
	public String passmodify() { //패스워드 변경
		
		return null;
	}
	
	
	//패스워드 변경 여부를 체크(MD5 암호화)
	@GetMapping("/passwd.do")
	public String passwd() {
		String pwd="a1234";
		String result = md.md5_making(pwd);
		System.out.println(result); //828c88f34ecb4c1ca8d89e18c6fad1a
		
		return null;
	}
	
	//ajax-get
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@GetMapping("/ajaxok.do")
	public String ajaxok(
				//requestparam의 value는 꼭 front 에서 data: 에 key 작성한 대로 받아야함
			     //List 변수명은 달라도 상관 없음
				@RequestParam(value="all_data") List<String> alldata,
				HttpServletResponse res
			) throws Exception{
		System.out.println(alldata); //[20, 30, 40]
		System.out.println(alldata.get(0)); //20
		
		this.pw = res.getWriter();
		
		//front에서 dataType:json으로 받는 경우
		JSONObject jo = new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		
		//front에서 dataType:text으로 받는 경우
		//this.pw.print("ok");
		
		return null;
	}
	
	//ajax-post
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data,HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charshet=utf-8");
		this.pw = res.getWriter();
		//System.out.println(all_data);
		
		/* 1. (@RequestBody String all_data) 일 경우 
			System.out.println(all_data);
		   {"all_data":["홍길동","강감찬","이순신"]} =>jsonObject로 뜯을수 있다(key : all_data)
		   => 이걸 배열로 뜯으려면 (JSONArray)로 받아야함;
		   */
		   
		JSONObject jo = new JSONObject(all_data);
		//System.out.println(jo.get("all_data")); //["홍길동","강감찬","이순신"]
		
		JSONArray ja = (JSONArray)jo.get("all_data");
		System.out.println(ja.get(0)); //홍길동

		JSONObject result = new JSONObject();
		result.put("result","ok"); //잘 받았다
		
		this.pw.print(result);
		
		return null;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String arr,HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		JSONArray ja = new JSONArray(arr);
		JSONArray ja2 = (JSONArray)ja.get(0);
		
		JSONObject rs_0 = new JSONObject();
//		rs_0.add(ja2);
		System.out.println((JSONArray)ja.get(0));
		
		
//		this.pw.print(rs_0);
		
		return null;
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")

//	@GetMapping("/ajaxok5.do")
//	public String ajaxok5(@RequestParam String basket,HttpServletResponse res) throws Exception{
	
	@PostMapping(value="/ajaxok5.do", produces="application/json; charset=UTF-8")
	public String ajaxok5(
			@RequestBody String basket,
			HttpServletResponse res,
			HttpServletRequest req
			) throws Exception{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charshet=utf-8");

		this.pw = res.getWriter();

		JSONArray ja = new JSONArray();
		JSONArray ja2 = (JSONArray)ja.get(0);
		System.out.println(ja2.get(0));

		JSONArray jaArray = new JSONArray(basket);
//		JSONArray ja2 = (JSONArray)ja.get(0);
//		System.out.println(ja2.get(0));
		
		JSONObject jo2 = (JSONObject)ja.get(0);

		System.out.println(jo2);
		this.pw.print(jo2);
		
		
//		this.pw.write("ok");
		
		this.pw.close();
		
		return null;
	}
	
	@PostMapping("/loginok.do")
	public String loginok(String mid,HttpSession session) {
//		HttpSession session = req.getSession();
//		session.setAttribute("mid", mid);
//		//일반 쇼핑몰 기준 페이지 이동 없을 때 유지시간 30분 지정 
//		//-> 해당 부분이 없으면 페이지 몇번 이동하다보면 계속 로그아웃 발생해서 에러 발생할 수 있음
//		session.setMaxInactiveInterval(1800); //1800초 : 30분
//		
//		System.out.println(mid);
		
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800);
		
		System.out.println(mid);
		
		return null;
	}
	
	@GetMapping("/restapi.do")
	public String restapi(@SessionAttribute(name="mid", required = false) String mid ) throws Exception {
		System.out.println(mid); //등록되어있는 세션값 찍힘
		
		return null;
	}

	
	
}
