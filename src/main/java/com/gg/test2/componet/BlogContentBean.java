package com.gg.test2.componet;

import org.springframework.stereotype.Component;

@Component
public class BlogContentBean {
	private Integer id;
	private String title;
	private String content;
	private String owner;
	private String name;
	private String tag;
	private String modifydate;

	public BlogContentBean() {
		id = 0;
		title = "";
		content = "";
		owner = "";
		name = "";
		tag = "";
		modifydate = "";
	}

	@Override
	public String toString() {
		return "BlogContentBean [id=" + id + ", title=" + title + ", content=" + content + ", owner=" + owner
				+ ", name=" + name + ", tag=" + tag + ", modifydate=" + modifydate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	

}
