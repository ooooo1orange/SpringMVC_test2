package com.gg.test2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.jboss.resteasy.annotations.interception.RedirectPrecedence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.test2.componet.BlogContentBean;
import com.gg.test2.componet.UserBean;
import com.gg.test2.repository.BlogContentRepository;
import com.gg.test2.repository.EditBlog;
import com.gg.test2.service.BlogContentService;
import com.gg.test2.service.UserService;

@Controller
public class HelloController {

	@ResponseBody
	@GetMapping("/homestring")
	public String homestring(@RequestParam("user") String user) {

		return "hello heome:" + user;
	}

	@ResponseBody
	@GetMapping("/home")
	public String home() {
		return "i`m home";
	}

	@GetMapping("/QQ")
	public String QQ(@ModelAttribute("user") String user) {
		System.out.println(user);
		return "home";
	}

	@GetMapping("/jump")
	public String jump(@ModelAttribute("user") String user) {
		System.out.println(user);
		return "redirect:/bye";
	}

	@GetMapping("/bye")
	public String bye(@ModelAttribute("user") String user) {
		System.out.println(user);
		return "bye";
	}

	@GetMapping("/jump2")
	public String jump2(@RequestParam("user") String user, @RequestParam("pwd") String pwd, RedirectAttributes red) {
		red.addAttribute("user", user);
		red.addFlashAttribute("pwd", pwd);
		return "redirect:/bye2";
	}

	@GetMapping("/bye2")
	public String bye2(@ModelAttribute("user") String user, @ModelAttribute("pwd") String pwd) {
		System.out.println(user);
		return "bye";
	}

	@GetMapping("/beanshow")
	public String beanshow(@Autowired UserBean ub, Model m) {

		List<UserBean> lub = new ArrayList<UserBean>();
		lub.add(ub);

		UserBean bb = new UserBean();
		bb.setName("joe");
		bb.setEmail("joe@yahoo.com");
		lub.add(bb);

		m.addAttribute("ListUsrBean", lub);
		return "home";
	}

	@Autowired
	UserService userService;

	@ResponseBody
	@GetMapping("/GetUser")
	public String GetUser() {
		userService.GetUser();
		return "User";
	}

	@GetMapping("/GetUser2")
	public String GetUser2(Model model) {
		// userService.GetUser();
		List<UserBean> lub = userService.GetUser2();
		model.addAttribute("ListUsrBean", lub);
		return "home";
	}

	@GetMapping("/uploadFile")
	public String uploadFile() {
		return "uploadFile";
	}

	@Autowired
	ServletContext context;

	final String UPLOADPATH = "resources/uploadimages";
	// final String UPLOADPATH =
	// "/Users/polarbear/Documents/javaspace/test2/src/main/webapp/upload";

	@ResponseBody
	@PostMapping("/upload")
	public String UploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("dddd");
		String filename = file.getOriginalFilename();
		System.out.println(context.getRealPath(""));
		System.out.println(File.separator);
		System.out.println(UPLOADPATH);
		System.out.println(filename);
		String path = context.getRealPath("") + File.separator + UPLOADPATH + File.separator + filename;
		System.out.println(path);
		// String path = UPLOADPATH + File.separator + filename;
		BufferedOutputStream outst;
		outst = new BufferedOutputStream(new FileOutputStream(new File(path)));

		outst.write(file.getBytes());
		outst.flush();
		outst.close();

		System.out.println("hello aa!");
		return "http://localhost:8080/test2/" + UPLOADPATH + File.separator + filename;

	}

}
