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

import com.gg.test2.componet.BlogContentBean;
import com.gg.test2.componet.TagsBean;

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
			blogContent.setTag(rs.getString("tag"));
			blogContent.setModifydate(rs.getString("modifydate"));

			return blogContent;
		}
	}
	
	public class TagRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TagsBean tags = new TagsBean();
			tags.setTag(rs.getString("tag"));
			return tags;
		}
	}

	public List<BlogContentBean> getBlog() {

		List rows = (List) jdbcTemplate.query(
				"select a.*,b.name from\n" + 
				"(\n" + 
				"select a.*,b.tag from blog as a\n" + 
				"left join (select blog_id,GROUP_CONCAT(tag_name) as tag\n" + 
				"FROM tag\n" + 
				"group by blog_id) as b\n" + 
				"on a.id = b.blog_id\n" + 
				") as a\n" + 
				"left join users as b\n" + 
				"on a.owner = b.id\n" + 
				"order by a.modifydate desc",
				new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
	}
	/**
	 * 給insert tag前需要取得blog_id
	 * 
	 * @return table blog的流水號id
	 */
	public String getBlogIDbyTitle(String title) {
		String blog_id = jdbcTemplate.queryForObject("select a.id from blog as a\n" + "left join users as b\n"
				+ "on a.owner = b.id \n" + "where a.title = ?", new Object[] { title },
                java.lang.String.class);
		return blog_id;
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

		List rows = (List) jdbcTemplate.query("select a.*,b.name from\n" + 
				"(\n" + 
				"select a.*,b.tag from blog as a\n" + 
				"left join (select blog_id,GROUP_CONCAT(tag_name) as tag\n" + 
				"FROM tag\n" + 
				"group by blog_id) as b\n" + 
				"on a.id = b.blog_id\n" + 
				") as a\n" + 
				"left join users as b\n" + 
				"on a.owner = b.id\n" + 
				"where a.id = ? \n" + 
				"order by a.modifydate desc", new Object[] { id },
				new RowMapperResultSetExtractor(new BlogRowMapper()));


		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();
			
			// System.out.println(usermap.get("name"));
		}
		return rows;
		
	}
	public List<BlogContentBean> getBlogByUser(Integer userID) {

		List rows = (List) jdbcTemplate.query("select a.*,b.name from\n" + 
				"(\n" + 
				"select a.*,b.tag from blog as a\n" + 
				"left join (select blog_id,GROUP_CONCAT(tag_name) as tag\n" + 
				"FROM tag\n" + 
				"group by blog_id) as b\n" + 
				"on a.id = b.blog_id\n" + 
				") as a\n" + 
				"left join users as b\n" + 
				"on a.owner = b.id\n" + 
				"where a.owner = ? \n" + 
				"order by a.modifydate desc", new Object[] { userID },
				new RowMapperResultSetExtractor(new BlogRowMapper()));

		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			// System.out.println(usermap.get("name"));
		}
		return rows;
		
	}
	/**
	 * 用部落格ID查該篇文章所有的tag
	 * @param blogID 請給我部落格的ID
	 * @return List tag_name
	 */
	public List<TagsBean> getTag(Integer blogID) {
		List rows = (List) jdbcTemplate.query("select tag_name as tag\n" + 
				"FROM tag\n" + 
				"where blog_id = ? ", 
				new Object[] { blogID }, 
				new RowMapperResultSetExtractor(new TagRowMapper()));
		Iterator it = rows.iterator();
		while (it.hasNext()) {
			TagsBean tagmap = (TagsBean) it.next();
//			System.out.println(tagmap.getTag());
//			tag+="#"+tagmap.getTag();
		}
		System.out.println(rows);
		return rows;
	}
	
	public List<BlogContentBean> searchResult(String keyword){
		//用keyword連集出來的id結果找文章
		List rows = (List) jdbcTemplate.query(
				"select a.*,b.name from\n" + 
				"(\n" + 
				"select a.*,b.tag from blog as a\n" + 
				"left join (select blog_id,GROUP_CONCAT(tag_name) as tag\n" + 
				"FROM tag\n" + 
				"group by blog_id) as b\n" + 
				"on a.id = b.blog_id\n" + 
				") as a\n" + 
				"left join users as b\n" + 
				"on a.owner = b.id\n" + 
				"where a.title like ? \n" + 
				"or a.content like ? \n" + 
				"or a.tag like ? \n" + 
				"order by a.modifydate desc", new Object[] { "%"+keyword+"%","%"+keyword+"%","%"+keyword+"%" },new RowMapperResultSetExtractor(new BlogRowMapper()));
		
		Iterator it = rows.iterator();
		while (it.hasNext()) {
			BlogContentBean blogmap = (BlogContentBean) it.next();

			//System.out.println(blogmap.getContent());
		}
		return rows;
	}
	
}
