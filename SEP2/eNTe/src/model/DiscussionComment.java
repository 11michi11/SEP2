package model;

public class DiscussionComment {

	private String content;
	private User user;

	public DiscussionComment(String content, User user) {
		this.content = content;
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "DiscussionComment{" +
				"content='" + content + '\'' +
				", user=" + user +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DiscussionComment)) return false;

		DiscussionComment that = (DiscussionComment) o;

		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		return user != null ? user.equals(that.user) : that.user == null;
	}


}
