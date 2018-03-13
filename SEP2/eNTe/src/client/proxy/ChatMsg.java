package client.proxy;

public class ChatMsg {

	private String sender;
	private String receiver;
	private String content;
	
	public ChatMsg(String sender, String receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public String getSender() {
		return sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public String getContent() {
		return content;
	}
}
