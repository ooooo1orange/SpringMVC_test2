package com.gg.test2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gg.test2.componet.UserBean;
import com.gg.test2.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<UserBean> GetUser() {
		return userRepository.getUser();
	}

	public List<UserBean> GetUser2() {
		return userRepository.getUser2();
	}
	
	public String GetUserIDByWorkID(Authentication auth) {
		return userRepository.getUserIDByWorkID(auth.getName());
	}

	public String GoToSignUP(String name, String email, String workid, String password) {
		String h = "";
		if (workid.isEmpty()) {
			// 沒傳註冊資料過來
			h = sinuphtml();

		} else {
			// 有傳註冊資料過來
			// insert db
			userRepository.insertUser(name, email, workid, password);
			h = "<h1>註冊成功！</h1>" + "<Script Language=\"JavaScript\">\r\n"
					+ "setTimeout(\"location.href='/test2/'\",1000);\r\n" + "</Script> ";
		}
		return h;
	}

	private String sinuphtml() {
		return "<!-- Main Content -->\r\n" + "  <div class=\"container\">\r\n" + "    <div class=\"row\">\r\n"
				+ "      <div class=\"col-lg-8 col-md-10 mx-auto\">\r\n" + "        <p>請輸入註冊資料!</p>\r\n"
				+ "        <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->\r\n"
				+ "        <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->\r\n"
				+ "        <!-- To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->\r\n"
				+ "        <form name=\"sentMessage\" id=\"contactForm\" novalidate action=\"/test2/signup\">\r\n"
				+ "          <div class=\"control-group\">\r\n"
				+ "            <div class=\"form-group floating-label-form-group controls\">\r\n"
				+ "              <label>Name</label>\r\n"
				+ "              <input type=\"text\" name = \"name\" class=\"form-control\" placeholder=\"Name\" id=\"name\" required data-validation-required-message=\"Please enter your name.\">\r\n"
				+ "              <p class=\"help-block text-danger\"></p>\r\n" + "            </div>\r\n"
				+ "          </div>\r\n" + "          <div class=\"control-group\">\r\n"
				+ "            <div class=\"form-group floating-label-form-group controls\">\r\n"
				+ "              <label>Email Address</label>\r\n"
				+ "              <input type=\"email\" name=\"email\" class=\"form-control\" placeholder=\"Email Address\" id=\"email\" required data-validation-required-message=\"Please enter your email address.\">\r\n"
				+ "              <p class=\"help-block text-danger\"></p>\r\n" + "            </div>\r\n"
				+ "          </div>\r\n" + "          <div class=\"control-group\">\r\n"
				+ "            <div class=\"form-group col-xs-12 floating-label-form-group controls\">\r\n"
				+ "              <label>工號</label>\r\n"
				+ "              <input type=\"text\" name=\"workid\" class=\"form-control\" placeholder=\"Work ID\" id=\"work_id\" required data-validation-required-message=\"Please enter your Work ID.\">\r\n"
				+ "              <p class=\"help-block text-danger\"></p>\r\n" + "            </div>\r\n"
				+ "          </div>\r\n" + "          <div class=\"control-group\">\r\n"
				+ "            <div class=\"form-group floating-label-form-group controls\">\r\n"
				+ "              <label>Password</label>\r\n"
				+ "              <input type=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" id=\"Password\" required data-validation-required-message=\"Please enter a Password.\"></textarea>\r\n"
				+ "              <p class=\"help-block text-danger\"></p>\r\n" + "            </div>\r\n"
				+ "          </div>\r\n" + "          <br>\r\n" + "          <div id=\"success\"></div>\r\n"
				+ "          <div class=\"form-group\">\r\n"
				+ "            <button type=\"submit\" class=\"btn btn-primary\" id=\"sendMessageButton\">Send</button>\r\n"
				+ "          </div>\r\n" + "        </form>\r\n" + "      </div>\r\n" + "    </div>\r\n" + "  </div>";
	}

}
