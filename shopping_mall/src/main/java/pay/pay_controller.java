package pay;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class pay_controller {
	//CORS방지 필수로 넣어줘야함
//	@RequestMapping(value="/pay/coupon_api.do",method=RequestMethod.GET)
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@GetMapping("/pay/coupon_api.do")
	public String coupon_api(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = null;
		JSONObject jo = null;
		JSONArray ja = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		pw = res.getWriter();
		try {
			con = new dbinfo().info();
			//모든 data 싹다 보내줌
			String sql ="select * from coupon order by cidx desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ja = new JSONArray();
			while(rs.next()) {
				jo = new JSONObject();
				jo.put("cidx", rs.getString(1));
				jo.put("cpname", rs.getString(2));
				jo.put("cprate", rs.getString(3));
				jo.put("cpuse", rs.getString(4));
				jo.put("cpdate", rs.getString(5));

				ja.put(jo);
			}
			pw.print(ja); //font에 찍어줌
			
		}catch(Exception e) {
			System.out.println(e);
			pw.print("error");
		}finally {
			rs.close();
			ps.close();
			con.close();
			pw.close();
		}
		
		return null;
	}
	
	
	@GetMapping("/pay/coupon_list.do")
	public String coupon_list(Model m,@RequestParam(value="",required = false) Integer page) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int pageno = 3; //data 3개씩
		int startpg = 0;
		try {
			//작업순서3. 각 페이지 넘버 별 - 몇번째 data부터 찍을지 정하는 변수
			if(page==null || page==1) { //Integer이어야 null 사용 가능
				startpg = 0;
			}else {
				startpg = (page-1)*pageno;
			}
			m.addAttribute("startpg",startpg); //Model로 찍어줌-가공된page번호

			con = new dbinfo().info();
			
			//작업순서2. data 총 갯수 가져오기
			String count = "select count(*) as ctn from coupon";
			ps = con.prepareStatement(count);
			rs2 = ps.executeQuery();
			rs2.next();
			m.addAttribute("total",rs2.getString("ctn")); //Model로 찍어줌
			
			//작업순서1. 1page당 데이터 두개씩
			String sql ="select * from coupon order by cidx desc limit ?,?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,startpg);
			ps.setInt(2,pageno); //startpg부터 pageno개의 data가 출력됨
			rs = ps.executeQuery();
			
			List<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
			while(rs.next()) {
				ArrayList<String> al = new ArrayList<String>();
				al.add(rs.getString(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				
				arr.add(al);
			}
			m.addAttribute("all",arr); //Model로 찍어줌
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			rs2.close();
			rs.close();
			ps.close();
			con.close();
		}
		
		
		return "/coupon_list";
	}
	
	@PostMapping("/pay/pay2.do")
	public String pay2(@ModelAttribute("product") pay_dao dao,Model m) throws Exception{
		//방식 1
		List<String> as = new ArrayList<String>();
		as.add(dao.getProduct_code());
		as.add(dao.getProduct_name());
		as.add(dao.getProduct_money());
		as.add(dao.getProduct_ea());
		as.add(dao.getPrice());
		
		//방식2
//		Collection<String> cl = new ArrayList<String>();
//		cl.add(dao.getProduct_code());
//		cl.add(dao.getProduct_name());
//		cl.add(dao.getProduct_money());
//		cl.add(dao.getProduct_ea());
//		cl.add(dao.getPrice());
		
		//방식3
//		Map<String, String> mp = new HashMap<String, String>();
//		mp.put("pdcode",dao.getProduct_code());
//		mp.put("pdname",dao.getProduct_name());
//		mp.put("pdmoney",dao.getProduct_money());
//		mp.put("pdea",dao.getProduct_ea());
//		mp.put("pdprice",dao.getPrice());
		
		//jsp에서 get(0) or [0] .. 이런식으로 받음
		m.addAllAttributes(Arrays.asList(as));
		//2. m.addAllAttributes(Arrays.asList(cl));		
		//3. m.addAllAttributes(Arrays.asList(mp));
		
		return "/pay2";  //WEB-INF/views
	}
	
	@PostMapping("/pay/pay3.do")
	public String pay3(
			@ModelAttribute("payinfo") pay_dao dao,
			HttpServletRequest req
		) throws Exception{
		req.setAttribute("goodcode", dao.getGoodcode());
		req.setAttribute("goodname", dao.getGoodname());
		req.setAttribute("goodea", dao.getGoodea());
		req.setAttribute("goodprice", dao.getGoodprice());
		req.setAttribute("price", dao.getPrice());
		req.setAttribute("buyername", dao.getBuyername());
		req.setAttribute("buyertel", dao.getBuyertel());
		req.setAttribute("buyeremail", dao.getBuyeremail());
		req.setAttribute("rec_post", dao.getRec_post());
		req.setAttribute("rec_way", dao.getRec_way());
		req.setAttribute("rec_addr", dao.getRec_addr());
		req.setAttribute("gopaymethod", dao.getGopaymethod());
		
		return "/pay3";
	}
	
}
