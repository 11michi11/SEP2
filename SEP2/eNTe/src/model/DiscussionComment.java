package model;

public class DiscussionComment {

	private String content;
	private String author;
	private MyDate date;
	private String id;
	public static final String noDiscussionCommnetId = "ThereIsNoDiscussion*****************";

	public DiscussionComment(String content, String author, MyDate date) {
		this.content = content;
		this.author = author;
		this.date = date;
	}
	public DiscussionComment(String id ,String content, String author, MyDate date) {
		this.content = content;
		this.author = author;
		this.date = date;
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public String getUser() {
		return author;
	}

	@Override
	public String toString() {
		return "DiscussionComment{" +
				"content='" + content + '\'' +
				", author=" + author +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DiscussionComment)) return false;

		DiscussionComment that = (DiscussionComment) o;

		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		return author != null ? author.equals(that.author) : that.author == null;
	}


}
