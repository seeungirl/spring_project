package shopping_admin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class prd_imgsave {
	//파일명 생성 (random함수 이용)
	public String rename() {
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		int no = (int)Math.ceil(Math.random()*1000);
		String today = sf.format(day);
		
		String datacode = today + "_" + no;
		
		return datacode;
	}
}
