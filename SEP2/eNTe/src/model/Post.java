package model;

import java.io.Serializable;

public class Post implements Serializable{
	
	private String title;
	private String content;
	
	public Post(String title, String content) {
		super();
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + "]";
	}
	
	

}
