package networking;
import java.io.IOException;


public class ChatMessage extends GameMessage{
	private String message;
	public static final String OPERATION = "CHAT_MSG";
	
	public ChatMessage(User u, String message) {
		super(u);
		this.message = message;
	}
	public ChatMessage(MessageInput in){
		super(in);
		try {
			message = in.nextString();
			in.nextChar();
			in.nextChar();
		} catch (IOException e) {
			//THROW NEW EXCEPTION HERE
		}
	}
	public void encode(MessageOutput out){
		super.encode(out);
		try {
			out.writeString(message);
			out.writeExactly(EOLN);
		} catch (IOException e) {
			//NEW EXCEPTION HERE
		}	
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String getOperation() {
		return OPERATION;
	}
	@Override
	public String toString() {
		return "ChatMessage ["+this.getTheUser().toString()+",message=" + message + "]";
	}
	
	

}
