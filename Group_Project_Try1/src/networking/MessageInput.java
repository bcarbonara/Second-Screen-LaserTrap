package networking;
import java.io.IOException;
import java.io.InputStream;


public class MessageInput {
	private InputStream is;

	public MessageInput(InputStream is) {
		super();
		this.is = is;
	}
	
	public char nextChar() throws IOException{
		return (char)is.read();
	}
	
	public String next() throws IOException{
		String buff = "";
		char next = nextChar();
		while(next != ' '){
			buff += next;
			next = nextChar();
		}
		return buff;
	}
	public int nextInt() throws IOException{
		int next;
		try{
			next = Integer.valueOf(next());
		}catch(NumberFormatException e){
			throw new IOException();
		}
		return next;
	}
	
	public String nextString() throws IOException{
		String buff = "";
		try{
			int length = nextInt();
			for(int i = 0 ; i < length ; i++){
				buff += nextChar();
			}
		}catch(NumberFormatException e){
			throw new IOException();
		}
		return buff;
	}
	
}
