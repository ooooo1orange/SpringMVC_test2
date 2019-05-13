package com.gg.test2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gg.test2.componet.UserBean;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public class UserRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserBean user = new UserBean();

			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setWork_id(rs.getString("work_id"));

			return user;
		}
	}

	public List<UserBean> getUser2() {

		List rows = (List) jdbcTemplate.query("select * from users",
				new RowMapperResultSetExtractor(new UserRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			UserBean usermap = (UserBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
	}

	public List<UserBean> getUser() {

		List<UserBean> aa = new ArrayList<UserBean>();
		List rows = jdbcTemplate.queryForList("select * from users");

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			Map usermap = (Map) it.next();
			System.out.println(usermap.get("name"));
		}
		return aa;
	}

	public String getUserPWD(String work_id) {

		String password = jdbcTemplate.queryForObject("select password from users where work_id = ?",
				new Object[] { work_id }, java.lang.String.class);

		return password;
	}
	
	public void insertUser(String name, String email, String workid, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodepassword = passwordEncoder.encode(password);
		
		jdbcTemplate.update("INSERT INTO users (name,email,work_id,password) VALUES(?,?,?,?)", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, workid);
				ps.setString(4, encodepassword);
			}
		});
	}
}
