package networking;
import java.io.IOException;
import java.io.OutputStream;


public class MessageOutput{
	private OutputStream os;
	
	public MessageOutput(OutputStream os){
		this.os = os;
	}
	
	public void writeInt(int aInt) throws IOException{
		os.write((""+aInt+" ").getBytes());
	}
	public void writeString(String s) throws IOException{
		writeInt(s.length());
		os.write(s.getBytes());
	}
	public void write(String s) throws IOException{
		os.write((s+" ").getBytes());
	}
	
	public void writeExactly(String s) throws IOException{
		os.write(s.getBytes());
	}
}
