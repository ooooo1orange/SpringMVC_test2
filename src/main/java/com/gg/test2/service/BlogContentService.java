package com.gg.test2.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gg.test2.componet.BlogContentBean;
import com.gg.test2.componet.TagsBean;
import com.gg.test2.repository.BlogContentRepository;
import com.gg.test2.repository.UserRepository;

@Service
public class BlogContentService {
	@Autowired
	private BlogContentRepository blogContentRepository;
	@Autowired
	private UserRepository ur;

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

	public List<BlogContentBean> GetBlogByUser(Authentication auth) {
		String workid = auth.getName(); // 取得工號
		String id = ur.getUserIDByWorkID(workid);
		return blogContentRepository.getBlogByUser(Integer.parseInt(id));
	}

	public List<BlogContentBean> GetBlog(String blogID) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String workid = auth.getName();
		if (!blogID.isEmpty()) {
			System.out.println("blogID=" + blogID);
			return GetBlogByID(Integer.parseInt(blogID));
		} else if (!workid.isEmpty()) {
			// System.out.println("我的發文列表");
			return GetBlogByUser(auth);
		} else {
			return GetBlog();
		}
	}

	public String getTag(String Blog_ID) {
		List<TagsBean> tags = blogContentRepository.getTag(Integer.parseInt(Blog_ID));
		String tag = "";
		Iterator it = tags.iterator();
		while (it.hasNext()) {
			TagsBean tagmap = (TagsBean) it.next();
//			System.out.println(tagmap.getTag());
			tag+="#"+tagmap.getTag();
			if(it.hasNext()) {
				tag+=",";
			}
		}
		System.out.println(tag);
		return tag;
	}

	// 文章編輯頁面顯示判斷
	public void showEditType(Model model, String strID, Authentication auth) {
		String id = ur.getUserIDByWorkID(auth.getName());
		model.addAttribute("username", id);
		if (strID.isEmpty()) {
			model.addAttribute("ListBlogContentBean", "第二步：請在此輸入內文");
			model.addAttribute("startupMode", "'wysiwyg'");
		} else {
			model.addAttribute("BlogTitleBean", GetTitleByID(Integer.parseInt(strID)));
			model.addAttribute("BlogContentBean", GetContentByID(Integer.parseInt(strID)));
//			model.addAttribute("ListBlogBean", blogContentService.GetBlogByID(Integer.parseInt(strID)));
			model.addAttribute("tags", getTag(strID));
			model.addAttribute("startupMode", "'source'");
			model.addAttribute("id", "\"id\" : " + Integer.parseInt(strID) + ",");
		}
	}

	// 搜尋關鍵字或tag
	public List<BlogContentBean> searchResult(String keyword) {
		return blogContentRepository.searchResult(keyword);
	}
}
