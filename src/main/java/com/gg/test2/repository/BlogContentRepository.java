package com.gg.test2.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
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
				"select a.*,b.name as name from blog as a\n" + "left join users as b\n" + "on a.owner = b.id",
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
				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id },
                java.lang.String.class);

		System.out.println(content);
		return content;

		
	}
	
	public String getTitleByID(Integer id) {

		String title = jdbcTemplate.queryForObject("select a.title from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id },
                java.lang.String.class);

		System.out.println(title);
		return title;

		
	}
	
	public List<BlogContentBean> getBlogByID(Integer id) {

		List rows = (List) jdbcTemplate.query("select a.*,b.name as name from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.id = ?", new Object[] { id },
				new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
		
	}
	public List<BlogContentBean> getBlogByUser(Integer userID) {

		List rows = (List) jdbcTemplate.query("select a.*,b.name as name from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.owner = ?", new Object[] { userID },
				new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
		
	}
	
	public String getUserIDByWorkID(String workid) {
		String id = jdbcTemplate.queryForObject("select id from users where work_id = ?", new Object[] { workid },
                java.lang.String.class);

		return id;
	}

}
