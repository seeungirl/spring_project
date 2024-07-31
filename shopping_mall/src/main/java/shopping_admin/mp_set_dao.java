package shopping_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class mp_set_dao {
	int mps_no,ms_pay_minpoint,ms_pay_maxpoint,ms_pay_deliveryprice;
	String adm_id,ms_pay_bank,ms_pay_banknum,ms_pay_credituse,ms_pay_phoneuse,ms_pay_bookgiftcarduse;
	String ms_pay_cashreceipt,ms_pay_deliveryname,ms_pay_deliverydate;
}
