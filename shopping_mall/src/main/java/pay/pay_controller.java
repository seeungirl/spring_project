package pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pay_controller {
	@GetMapping("/pay/coupon_list.do")
	public String coupon_list(Model m) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//1page당 데이터 두개씩
		int pageno = 2;
		try {
			String sql ="select * from coupon order by cidx desc limit ?,?";			
			con = new dbinfo().info();
			ps = con.prepareStatement(sql);
			ps.setInt(1,0);
			ps.setInt(2,pageno);
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
			
			m.addAllAttributes(Arrays.asList(arr.get(0)));
		}catch(Exception e) {
			System.out.println(e);
		}finally {
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
