package com.gg.test2.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class EditBlogRepository {
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

		jdbcTemplate.update("INSERT INTO blog (title,content,owner) VALUES(?,?,?)", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setString(3, owner);
			}
		});
	}
	/**
	 * 好像沒有用，請都用updateTag
	 */
	public void insertTag(Integer blog_id, String[] tag) {
		// insert tag
	}
	/**
     * update table blog
     *
     * @param id table blog 的id
     * @param title 標題
     * @param content 內文
     * @param owner 作者代碼
     * @return no return
     */
	public void update(Integer id, String title, String content, String owner) {
		jdbcTemplate.update(
				"UPDATE blog SET title = ?,content = ?,owner = ?,modifydate = (datetime('now', 'localtime')) WHERE id = ? ",
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, title);
						ps.setString(2, content);
						ps.setString(3, owner);
						ps.setInt(4, id);

					}
				});
	}
	/**
     * update table tag
     *
     * @param blog_id table blog 的id
     * @param tag  tag陣列
     * @return no return
     */
	public void updateTag(Integer blog_id, String[] tag) {
		// update tag
		jdbcTemplate.update("delete from tag WHERE blog_id = ? ", blog_id);
		System.out.println("mmm%%%%%%%%%");
		Integer b = 0;
		System.out.println(b<tag.length);
		
		for (Integer a = 0; a < tag.length; a++) {
			System.out.println("@@@@@@");
			jdbcTemplate.update("INSERT INTO tag (blog_id,tag_name) VALUES(?,?) ", blog_id, tag[a]);
		}
	}

	public void delete(Integer id) {
		jdbcTemplate.update("delete from blog WHERE id = ? ", new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		});
	}

}
