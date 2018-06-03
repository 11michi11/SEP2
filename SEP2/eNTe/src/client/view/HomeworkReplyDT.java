package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.HomeworkReply;

public class HomeworkReplyDT {

	private SimpleStringProperty name;
	private SimpleStringProperty classNo;
	private SimpleStringProperty handInDate;
	private HomeworkReply reply;

	public HomeworkReplyDT(HomeworkReply reply) {
		this.name = new SimpleStringProperty(reply.getStudent().getName());
		this.classNo = new SimpleStringProperty(reply.getStudent().getClassNo().toString());
		this.handInDate = new SimpleStringProperty(reply.getHandInDate().toString());
		this.reply = reply;
	}

	public String getName() {
		return name.get();
	}

	public String getClassNo() {
		return classNo.get();
	}

	public String getHandInDate() {
		return handInDate.get();
	}

	public HomeworkReply getReply() {
		return reply;
	}
}
