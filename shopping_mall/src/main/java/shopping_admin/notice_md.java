package shopping_admin;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("noti_md")
public class notice_md {
	@Resource(name="template2")
	private SqlSessionTemplate tm2;
}
