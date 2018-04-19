package model;

import java.io.Serializable;

public class Post implements Serializable{
	
	private String title;
	private String content;
	
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Post) {
			Post other = (Post)obj;
			return content.equals(other.content) && title.equals(other.title);	
		}
		return false;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + "]";
	}
	
	

}
