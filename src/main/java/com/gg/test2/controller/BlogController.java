package com.gg.test2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import com.gg.test2.repository.EditBlogRepository;
import com.gg.test2.service.BlogContentService;
import com.gg.test2.service.EditBlogService;
import com.gg.test2.service.UserService;

@Controller
public class BlogController {
	@Autowired
	BlogContentService blogContentService;
	@Autowired
	EditBlogService editBlogService;
	@Autowired
	UserService us;

	/* #########################bootstrap page######################### */
	@GetMapping("/")
	public String root() {
		return "redirect:index";
	}

	@GetMapping("/nav")
	public String nav() {
		return "nav";
	}

	@GetMapping("/footer")
	public String footer() {
		return "footer";
	}

	@GetMapping("/index")
	public String GoIndex(Model model) {
		List<BlogContentBean> lb = blogContentService.GetBlog();

		model.addAttribute("ListBlogContentBean", lb);
		return "index";
	}

	@ResponseBody
	@GetMapping("/about")
	public String GoAbout(Model model) {
		// 拿來測試用
		String Blog_ID = "11";
		String tags = blogContentService.getTag(Blog_ID);
		
		return "ok";
	}

	@GetMapping("/post")
	public String GoSelf(Model model, @ModelAttribute("bid") String blogID) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<BlogContentBean> lb = blogContentService.GetBlog(blogID);
		String id = us.GetUserIDByWorkID(auth);
		model.addAttribute("ListBlogContentBean", lb);
		model.addAttribute("UserID", id);
		return "post";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/index";
		// You can redirect wherever you want, but generally it's a good practice to
		// show login screen again.
	}

	@RequestMapping(value = "/signup", method = { RequestMethod.POST, RequestMethod.GET })
	public String signup(Model model, @ModelAttribute("workid") String workid,
			@ModelAttribute("password") String password, @ModelAttribute("name") String name,
			@ModelAttribute("email") String email) {
		// url會看到資料 待改進
		String h = us.GoToSignUP(name, email, workid, password);
		model.addAttribute("isSignUp", h);
		return "signup";
	}

	/* #########################bootstrap page######################### */

	/* #########################顯示編輯區######################### */
	@GetMapping("/re-edit")
	public String reEdit(@RequestParam(value = "id", required = true) Integer id, RedirectAttributes red) {
		red.addAttribute("id", id);
		return "redirect:/blogedit";
	}

	@GetMapping("/blogedit")
	public String blogedit(@ModelAttribute("id") String strID, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		blogContentService.showEditType(model, strID, auth);
		return "blogedit";
	}
	/* #########################顯示編輯區######################### */

	/* #########################新刪改查區#######這塊應該擺service################## */
	@GetMapping("/search")
	public String search(Model model, @ModelAttribute("keyword") String keyword) {
		List<BlogContentBean> lb = blogContentService.searchResult(keyword);
		model.addAttribute("ListBlogContentBean", lb);
		return "post";
	}

	@ResponseBody
	@PostMapping("/insertblog")
	public String insertblog(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "owner", required = true) String owner,
			@RequestParam(value = "tag", required = true) String tag) {
		String result = "title:" + title + " content:" + content + " owner:" + owner + " tag:" + tag;
		System.out.println(result);
		editBlogService.inert(title, content, owner, tag);
		return result;
	}

	@ResponseBody
	@PostMapping("/updateblog")
	public String updateblog(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "owner", required = true) String owner,
			@RequestParam(value = "tag", required = true) String tag) {
		String result = "title:" + title + " content:" + content + " owner:" + owner + " tag:" + tag;
		System.out.println(result);
		editBlogService.update(id.toString(), title, content, owner, tag);
		return result;
	}

	@ResponseBody
	@PostMapping("/deleteblog")
	public String deleteblog(@RequestParam(value = "id", required = true) Integer id) {
		String result = "id:" + id;
		System.out.println(result);
		//editBlogService.delete(id);
		return result;
	}
	/* #########################新刪改查區######################### */

	/* #########################廢除區######################### */
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
	/* #########################廢除區######################### */
}
