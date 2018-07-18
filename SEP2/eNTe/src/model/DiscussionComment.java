package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "discussioncomments", schema = "test")
public class DiscussionComment {

	@Column(name = "content")
	private String content;

	@Column(name = "authorid")
	private String author;

	@Column(name = "date")
	@Type(type = "MyDateMapper")
	private MyDate date;

	@Id
	@Column(name = "commentid")
	private String id;

	@Column(name = "discussionid")
	private String discussionid;

	@Transient
	public static final String noDiscussionCommnetId = "ThereIsNoDiscussion*****************";

	public DiscussionComment(){}

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

	public static String getNoDiscussionCommnetId() {
		return noDiscussionCommnetId;
	}

	public String getContent() {
		return content;
	}

	public String getUser() {
		return author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDiscussionid() {
		return discussionid;
	}

	public void setDiscussionid(String discussionId) {
		this.discussionid = discussionId;
	}

	@Override
	public String toString() {
		return "DiscussionComment{" +
				"content='" + content + '\'' +
				", author='" + author + '\'' +
				", date=" + date +
				", id='" + id + '\'' +
				", discussionId='" + discussionid + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DiscussionComment that = (DiscussionComment) o;

		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		if (author != null ? !author.equals(that.author) : that.author != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return discussionid != null ? discussionid.equals(that.discussionid) : that.discussionid == null;
	}

	@Override
	public int hashCode() {
		int result = content != null ? content.hashCode() : 0;
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (discussionid != null ? discussionid.hashCode() : 0);
		return result;
	}


}
