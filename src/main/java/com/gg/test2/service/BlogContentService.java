package com.gg.test2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.test2.componet.BlogContentBean;
import com.gg.test2.componet.NavFooterBean;
import com.gg.test2.repository.BlogContentRepository;

@Service
public class BlogContentService {
	@Autowired
	private BlogContentRepository blogContentRepository;
	@Autowired 
	private NavFooterBean nf;

	public List<BlogContentBean> GetBlog() {
		return blogContentRepository.getBlog();
	}

	public String GetContentByID(Integer id) {
		return blogContentRepository.getContentByID(id);
	}

	public String GetTitleByID(Integer id) {
		return blogContentRepository.getTitleByID(id);
	}

	public List<BlogContentBean> GetBlogByID(Integer id) {
		return blogContentRepository.getBlogByID(id);
	}

	public List<BlogContentBean> GetBlogByUser(String userID) {
		return blogContentRepository.getBlogByUser(Integer.parseInt(userID));
	}
	
	public List<BlogContentBean> GetBlog(String userID,String blogID){
		System.out.println("userID="+userID+"*******\"blogID=\"+"+blogID);
		if(!userID.isEmpty()) {
			return GetBlogByUser(userID.toString());
		}else if(!blogID.isEmpty()) {
			return GetBlogByID(Integer.parseInt(blogID));
		}else {
			return GetBlog();
		}	
	}
	
	public NavFooterBean GetNavAndFooter() {
		return nf;
	}

	
}
