package com.gg.test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.test2.repository.BlogContentRepository;
import com.gg.test2.repository.EditBlogRepository;

@Service
public class EditBlogService {
	@Autowired
	EditBlogRepository editBlogRepository;
	@Autowired
	BlogContentRepository blogContentRepository; 
	
	/**
	 * 
	 * @param title String
	 * @param content String
	 * @param owner String
	 * @param tag String
	 */
	public void inert(String title, String content, String owner,String tag) {
		//tag轉成陣列
		editBlogRepository.insert(title, content, owner);
		String id = blogContentRepository.getBlogIDbyTitle(title);
		String[] tags = {};
		if(!tag.isEmpty()) {
			tags = tag.replace("#","").split(",");
		}
		
		editBlogRepository.updateTag(Integer.parseInt(id), tags);
	}
	/**
	 * 
	 * @param id 要知道blog的id
	 * @param title
	 * @param content
	 * @param owner
	 * @param tag
	 */
	public void update(String id, String title, String content, String owner,String tag) {
		//tag轉成陣列
		editBlogRepository.update(Integer.parseInt(id), title, content, owner);
		String[] tags = {};
		if(!tag.isEmpty()) {
			tags = tag.replace("#","").split(",");
		}
		editBlogRepository.updateTag(Integer.parseInt(id), tags);
	}
}
