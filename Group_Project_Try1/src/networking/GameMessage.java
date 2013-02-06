package networking;
import java.io.IOException;


public abstract class GameMessage {
	private User theUser;
	protected final String HEADER = "GameMessagev1";
	protected final String EOLN = "\r\n";
	
	public GameMessage(User u){
		theUser = u;
	}
	public GameMessage(MessageInput in){
		theUser = User.decode(in);
	}
	public static GameMessage decode(MessageInput in){
		try {
			String hdr = in.next();
			String op = in.next();
			if(op.compareTo(ChatMessage.OPERATION) == 0){
				return new ChatMessage(in);
			}
		} catch (IOException e) {
			//THROW NEW EXCEPTION
		}
		return null;
	}
	
	public void encode(MessageOutput out){
		try {
			out.write(HEADER);
			out.write(getOperation());
			theUser.encode(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public User getTheUser() {
		return theUser;
	}
	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}
	public abstract String getOperation();
}
