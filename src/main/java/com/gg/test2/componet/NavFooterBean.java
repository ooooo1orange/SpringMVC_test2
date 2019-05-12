package com.gg.test2.componet;

import org.springframework.stereotype.Component;

@Component
public class NavFooterBean {
	private String nav;
	private String footer;
	
	public NavFooterBean() {
		nav = NavHeader();
		footer = Footer();
	}

	@Override
	public String toString() {
		return "NavFooterBean [nav=" + nav + ", footer=" + footer + "]";
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	private String NavHeader() {
		String nav = "<!-- Navigation -->\n" + 
				"	<nav class=\"navbar navbar-expand-lg navbar-light fixed-top\"\n" + 
				"		id=\"mainNav\">\n" + 
				"		<div class=\"container\">\n" + 
				"			<a class=\"navbar-brand\" href=\"index\">Start Bootstrap</a>\n" + 
				"			<button class=\"navbar-toggler navbar-toggler-right\" type=\"button\"\n" + 
				"				data-toggle=\"collapse\" data-target=\"#navbarResponsive\"\n" + 
				"				aria-controls=\"navbarResponsive\" aria-expanded=\"false\"\n" + 
				"				aria-label=\"Toggle navigation\">\n" + 
				"				Menu <i class=\"fas fa-bars\"></i>\n" + 
				"			</button>\n" + 
				"			<div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" + 
				"				<ul class=\"navbar-nav ml-auto\">\n" + 
				"					<li class=\"nav-item\"><a class=\"nav-link\" href=\"index\">Home</a>\n" + 
				"					</li>\n" + 
				"					<li class=\"nav-item\"><a class=\"nav-link\" href=\"about\">About</a>\n" + 
				"					</li>\n" + 
				"					<li class=\"nav-item\"><a class=\"nav-link\" href=\"post\">Sample\n" + 
				"							Post</a></li>\n" + 
				"					<li class=\"nav-item\"><a class=\"nav-link\" href=\"contact\">Contact</a>\n" + 
				"					</li>\n" + 
				"				</ul>\n" + 
				"			</div>\n" + 
				"		</div>\n" + 
				"	</nav>";
		return nav;
	}
	
	private String Footer() {
		String footer = "<!-- Footer -->\n" + 
				"  <footer>\n" + 
				"    <div class=\"container\">\n" + 
				"      <div class=\"row\">\n" + 
				"        <div class=\"col-lg-8 col-md-10 mx-auto\">\n" + 
				"          <ul class=\"list-inline text-center\">\n" + 
				"            <li class=\"list-inline-item\">\n" + 
				"              <a href=\"#\">\n" + 
				"                <span class=\"fa-stack fa-lg\">\n" + 
				"                  <i class=\"fas fa-circle fa-stack-2x\"></i>\n" + 
				"                  <i class=\"fab fa-twitter fa-stack-1x fa-inverse\"></i>\n" + 
				"                </span>\n" + 
				"              </a>\n" + 
				"            </li>\n" + 
				"            <li class=\"list-inline-item\">\n" + 
				"              <a href=\"#\">\n" + 
				"                <span class=\"fa-stack fa-lg\">\n" + 
				"                  <i class=\"fas fa-circle fa-stack-2x\"></i>\n" + 
				"                  <i class=\"fab fa-facebook-f fa-stack-1x fa-inverse\"></i>\n" + 
				"                </span>\n" + 
				"              </a>\n" + 
				"            </li>\n" + 
				"            <li class=\"list-inline-item\">\n" + 
				"              <a href=\"#\">\n" + 
				"                <span class=\"fa-stack fa-lg\">\n" + 
				"                  <i class=\"fas fa-circle fa-stack-2x\"></i>\n" + 
				"                  <i class=\"fab fa-github fa-stack-1x fa-inverse\"></i>\n" + 
				"                </span>\n" + 
				"              </a>\n" + 
				"            </li>\n" + 
				"          </ul>\n" + 
				"          <p class=\"copyright text-muted\">Copyright &copy; Your Website 2019</p>\n" + 
				"        </div>\n" + 
				"      </div>\n" + 
				"    </div>\n" + 
				"  </footer>";
		return footer;
	}
}
