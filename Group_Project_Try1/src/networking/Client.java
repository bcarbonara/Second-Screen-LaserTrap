package networking;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client {
	private Socket clnt;
	private User theUser;
	public Client(String address, int port) throws UnknownHostException, IOException{
		clnt = new Socket(address,port);
		theUser = new User("random string");
	}
	public GameMessage read(){
		try {
			MessageInput is = new MessageInput(clnt.getInputStream());
			return GameMessage.decode(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void write(String chatMessage){
		try {
			MessageOutput out = new MessageOutput(clnt.getOutputStream());
			ChatMessage msg = new ChatMessage(theUser,chatMessage);
			msg.encode(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		final Client clnt;
		try {
			clnt = new Client(args[0],5000);
			ExecutorService ex = Executors.newFixedThreadPool(2);
			final Scanner in = new Scanner(System.in);
			ex.execute(new Runnable(){
				public void run(){	
					while(true){
						System.out.println(clnt.read().toString());
					}
				}
			});

			while(true){
				System.out.print("Message> ");
				String s2 =in.nextLine();
				clnt.write(s2);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
