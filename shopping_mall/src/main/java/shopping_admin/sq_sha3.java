package shopping_admin;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository("sqsha3")
public class sq_sha3 {
	public String sq_sha3(String adm_pass) {
		StringBuilder sb = new StringBuilder();
		
		try {
			MessageDigest sh3 = MessageDigest.getInstance("SHA3-224");
			sh3.update(adm_pass.getBytes());
			for(byte bt : sh3.digest()) {
				sb.append(String.format("%x", bt));
			}
		}catch(Exception e) {
			sb.append("인자값 오류 발생으로 생성이 되지 않음");
		}
		
		return sb.toString();
	}

}
