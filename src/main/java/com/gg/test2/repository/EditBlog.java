package com.gg.test2.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class EditBlog {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insert(String title, String content, String owner) {
		/*
		 * jdbcTemplate.update(new PreparedStatementCreator() { public PreparedStatement
		 * createPreparedStatement(Connection con) throws SQLException { String sql =
		 * "INSERT INTO user (name,age) VALUES(?,?)"; PreparedStatement ps =
		 * con.prepareStatement(sql); ps.setString(1, ""); ps.setInt(2, 1); return ps; }
		 * });
		 */
		System.out.println("&&&&&&&&&&&&&&&&&&");
//		String result = "title:" + title + " content:" + content + " owner:" + owner;
//		System.out.println(result);

		jdbcTemplate.update("INSERT INTO blog (title,content,owner) VALUES(?,?,?)", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setString(3, owner);
			}
		});
	}

	public void update(Integer id, String title, String content, String owner) {
		System.out.println("#######");
//		String result = "id:" + id + " title:" + title + " content:" + content + " owner:" + owner;
//		System.out.println(result);
		jdbcTemplate.update("UPDATE blog SET title = ?,content = ?,owner = ?,modifydate = (datetime('now', 'localtime')) WHERE id = ? ", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setString(3, owner);
				ps.setInt(4, id);
			}
		});
	}
	
	public void delete(Integer id) {
		System.out.println("$$$$$$");
//		String result = "id:" + id;
//		System.out.println(result);
		jdbcTemplate.update("delete from blog WHERE id = ? ", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		});
	}

}
