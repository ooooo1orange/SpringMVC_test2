package com.gg.test2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.gg.test2.componet.UserBean;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate JdbcTemplate;

	public class UserRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBean user = new UserBean();

			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));

			return user;
		}
	}

	public List<UserBean> getUser2() {

		
		List rows =  (List) JdbcTemplate.query("select * from users",
		        new RowMapperResultSetExtractor(new UserRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			UserBean usermap = (UserBean) it.next();
			
			//System.out.println(usermap.get("name"));
		}
		return rows;
	}

	public List<UserBean> getUser() {

		List<UserBean> aa = new ArrayList<UserBean>();
		List rows = JdbcTemplate.queryForList("select * from users");

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			Map usermap = (Map) it.next();
			System.out.println(usermap.get("name"));
		}
		return aa;
	}
	
	public String getUserPWD(String work_id) {

		String password = JdbcTemplate.queryForObject("select password from users where work_id = ?",new Object[] {work_id},
                java.lang.String.class);

		
		return password;
	}
}
