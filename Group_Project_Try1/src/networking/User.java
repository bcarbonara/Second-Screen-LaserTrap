package networking;
import java.io.IOException;
import java.util.UUID;


public class User {
	private String uuid;
	
	public User(){
		uuid = UUID.randomUUID().toString();
	}
	public User(String aUUID){
		uuid = aUUID;
	}
	
	public static User decode(MessageInput in){
		User u = null;
		try {
			 u = new User(in.nextString());
		} catch (IOException e) {
			//THROW NEW EXCEPTION
		}
		return u;
	}
	
	public void encode(MessageOutput out) throws IOException{
		out.writeString(uuid);
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
