package com.gg.test2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gg.test2.componet.BlogContentBean;

@Repository
public class BlogContentRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public class BlogRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			BlogContentBean blogContent = new BlogContentBean();

			blogContent.setId(rs.getInt("id"));
			blogContent.setTitle(rs.getString("title"));
			blogContent.setContent(rs.getString("content"));
			blogContent.setOwner(rs.getString("owner"));
			blogContent.setName(rs.getString("name"));
			blogContent.setModifydate(rs.getString("modifydate"));

			return blogContent;
		}
	}

	public List<BlogContentBean> getBlog() {

		List rows = (List) jdbcTemplate.query(
				"select a.*,b.name as name from blog as a\n" + "left join users as b\n"
						+ "on a.owner = b.id order by a.modifydate desc",
				new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
	}

	public String getContentByID(Integer id) {

//		List rows = (List) jdbcTemplate.query("select a.*,b.name as name from blog as a\n" + "left join users as b\n"
//				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id },
//				new RowMapperResultSetExtractor(new BlogRowMapper()));
//
//		Iterator it = rows.iterator();
//		while (it.hasNext()) {
//			BlogContentBean blogmap = (BlogContentBean) it.next();
//
//			// System.out.println(usermap.get("name"));
//		}
//		return rows;

		String content = jdbcTemplate.queryForObject("select a.content from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id }, java.lang.String.class);

		System.out.println(content);
		return content;

	}

	public String getTitleByID(Integer id) {

		String title = jdbcTemplate.queryForObject("select a.title from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id }, java.lang.String.class);

		System.out.println(title);
		return title;

	}

	public List<BlogContentBean> getBlogByID(Integer id) {

		List rows = (List) jdbcTemplate.query(
				"select a.id,a.title,a.content,a.owner,a.modifydate,b.name from\n" + "(select * from blog\n"
						+ "where id in \n" + "(select id as blog_id from blog \n" + "where title like '%hihi%' \n"
						+ "or content like '%hihi%'\n" + "union\n" + "select blog_id from tag\n"
						+ "where tag_name like '%hihi%') )as a \n" + "left join users as b \n" + "on a.owner = b.id\n"
						+ "order by a.modifydate desc",
				new Object[] { id }, new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;

	}

	public List<BlogContentBean> getBlogByUser(Integer userID) {

		List rows = (List) jdbcTemplate.query(
				"select a.*,b.name as name from blog as a\n" + "left join users as b\n" + "on a.owner = b.id \n"
						+ "where a.owner = ? order by a.modifydate desc",
				new Object[] { userID }, new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;

	}

	public List<BlogContentBean> searchResult(String keyword) {
		// 用keyword連集出來的id結果找文章
		String sql = "select a.id,a.title,a.content,a.owner,a.modifydate,b.name from\n" + "(select * from blog\n"
				+ "where id in \n" + "(select id as blog_id from blog \n" + "where title like :keyword \n"
				+ "or content like :keyword \n" + "union\n" + "select blog_id from tag\n"
				+ "where tag_name like :keyword ) )as a \n" + "left join users as b \n" + "on a.owner = b.id\n"
				+ "order by a.modifydate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", "%" + keyword + "%");
		return jdbcTemplate.query(sql, new BlogRowMapper(), params);
	}

}
