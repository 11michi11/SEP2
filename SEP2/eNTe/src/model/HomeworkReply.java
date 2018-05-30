package model;

public class HomeworkReply {

	private String content;
	private Student student;
	private boolean late;
	private MyDate handInDate;

	public HomeworkReply(String content, Student student,boolean late,MyDate handInDate) {
		this.content = content;
		this.student = student;
		this.late = late;
		this.handInDate = handInDate;
	}

	public String getContent() {
		return content;
	}

	public Student getStudent() {
		return student;
	}

	public boolean isLate() {
		return late;
	}

	public MyDate getHandInDate() {
		return handInDate;
	}

	@Override
	public String toString() {
		return "HomeworkReply{" +
				"content='" + content + '\'' +
				", student=" + student +
				", late=" + late +
				", handInDate=" + handInDate +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HomeworkReply)) return false;

		HomeworkReply that = (HomeworkReply) o;

		if (late != that.late) return false;
		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		if (student != null ? !student.equals(that.student) : that.student != null) return false;
		return handInDate != null ? handInDate.equals(that.handInDate) : that.handInDate == null;
	}
}
