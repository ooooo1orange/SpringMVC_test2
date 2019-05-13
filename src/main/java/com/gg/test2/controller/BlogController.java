package com.gg.test2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.test2.componet.BlogContentBean;
import com.gg.test2.componet.NavFooterBean;
import com.gg.test2.repository.EditBlog;
import com.gg.test2.service.BlogContentService;
import com.gg.test2.service.UserService;

@Controller
public class BlogController {
	@Autowired
	BlogContentService blogContentService;
	@Autowired
	EditBlog ib;
	@Autowired
	NavFooterBean nf;
	@Autowired
	UserService us;

	/* #########################bootstrap page######################### */
	@GetMapping("/")
	public String root() {
		return "redirect:index";
	}

	@GetMapping("/index")
	public String GoIndex(Model model) {
		// nf = blogContentService.GetNavAndFooter();
		model.addAttribute("NavAndFooter", nf);
		List<BlogContentBean> lb = blogContentService.GetBlog();
		model.addAttribute("ListBlogContentBean", lb);
		return "index";
	}

	@GetMapping("/about")
	public String GoAbout(Model model) {
		model.addAttribute("NavAndFooter", nf);
		return "about";
	}

	@GetMapping("/post")
	public String GoSelf(Model model, @ModelAttribute("uid") String userID, @ModelAttribute("bid") String blogID) {
		model.addAttribute("NavAndFooter", nf);
		List<BlogContentBean> lb = blogContentService.GetBlog(userID, blogID);
		model.addAttribute("ListBlogContentBean", lb);
		return "post";
	}

	@ResponseBody
	@GetMapping("/pass")
	public String pass() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("123456");
		return password;
	}

	@RequestMapping(value = "/signup", method = { RequestMethod.POST, RequestMethod.GET })
	public String signup(Model model, @ModelAttribute("workid") String workid,
			@ModelAttribute("password") String password, 
			@ModelAttribute("name") String name,
			@ModelAttribute("email") String email) {
		
		//url會看到資料 待改進
		String h = us.GoToSignUP(name, email, workid, password);
		model.addAttribute("isSignUp", h);
		
		return "signup";
	}

	/* #########################bootstrap page######################### */

	/* #########################顯示編輯區######################### */
	@GetMapping("/blog")
	public String GetBlog(Model model) {
		List<BlogContentBean> lb = blogContentService.GetBlog();
		model.addAttribute("ListBlogContentBean", lb);
		return "blog";
	}

	@GetMapping("/content")
	public String Getcontent(Model model, @ModelAttribute("id") String strID) {
		List<BlogContentBean> lb = blogContentService.GetBlogByID(Integer.parseInt(strID));
		model.addAttribute("ListBlogContentBean", lb);
		return "contentpage";
	}

	@GetMapping("/re-edit")
	public String reEdit(@RequestParam(value = "id", required = true) Integer id, RedirectAttributes red) {
		red.addAttribute("id", id);
		return "redirect:/blogedit";
	}

	@GetMapping("/blogedit")
	public String blogedit(@ModelAttribute("id") String strID, Model model) {
		System.out.println(strID);
		showEditType(model, strID);
		return "blogedit";
	}
	/* #########################顯示編輯區######################### */

	/* #########################新刪改查區#######這塊應該擺service################## */
	@ResponseBody
	@PostMapping("/insertblog")
	public String insertblog(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "owner", required = true) String owner) {
		String result = "title:" + title + " content:" + content + " owner:" + owner;
		System.out.println(result);
		ib.insert(title, content, owner);
		return result;
	}

	@ResponseBody
	@PostMapping("/updateblog")
	public String updateblog(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "owner", required = true) String owner) {
		String result = "id:" + id + " title:" + title + " content:" + content + " owner:" + owner;
		System.out.println(result);
		ib.update(id, title, content, owner);
		return result;
	}

	@ResponseBody
	@PostMapping("/deleteblog")
	public String deleteblog(@RequestParam(value = "id", required = true) Integer id) {
		String result = "id:" + id;
		System.out.println(result);
		ib.delete(id);
		return result;
	}

	private void showEditType(Model model, String strID) {
		if (strID.isEmpty()) {
			model.addAttribute("ListBlogContentBean", "第二步：請在此輸入內文");
			model.addAttribute("startupMode", "'wysiwyg'");
		} else {
			model.addAttribute("BlogTitleBean", blogContentService.GetTitleByID(Integer.parseInt(strID)));
			model.addAttribute("BlogContentBean", blogContentService.GetContentByID(Integer.parseInt(strID)));
//			model.addAttribute("ListBlogBean", blogContentService.GetBlogByID(Integer.parseInt(strID)));
			model.addAttribute("startupMode", "'source'");
			model.addAttribute("id", "\"id\" : " + Integer.parseInt(strID) + ",");
		}
	}
	/* #########################新刪改查區######################### */
}
