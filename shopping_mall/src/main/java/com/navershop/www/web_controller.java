package com.navershop.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class web_controller {
	PrintWriter pw = null;
	//ajax 통신 - CORS방식 무조건 넣어줘야함!
	
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
		System.out.println(ja2.get(0));
		
		
		this.pw.write("ok");
		
		return null;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ajaxok5.do")
	public String ajaxok5(@RequestParam String basket,HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		JSONArray ja = new JSONArray();
		JSONArray ja2 = (JSONArray)ja.get(0);
		System.out.println(ja2.get(0));
		
		
		this.pw.write("ok");
		
		return null;
	}
	
	
}
