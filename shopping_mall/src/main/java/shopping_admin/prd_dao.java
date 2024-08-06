package shopping_admin;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class prd_dao {
	int p_no,p_price,p_dc_percent,p_dc_money,p_stock;
	String adm_id,cate_name,p_code,p_name,p_summary;
	String p_use,p_soldout,p_thumb_img,p_desc,p_indate;
	MultipartFile p_ori_img;
}
