package com.gg.test2.componet;

import org.springframework.stereotype.Component;

@Component
public class TagsBean {
	private String tag;

	@Override
	public String toString() {
		return "TagsBean [tag=" + tag + "]";
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
