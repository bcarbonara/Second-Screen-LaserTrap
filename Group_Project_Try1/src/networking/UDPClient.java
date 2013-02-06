package networking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDPClient {
	public static void main(String args[]) throws SocketException, UnknownHostException{
	
		while(true){
			DatagramSocket sock = new DatagramSocket();
			String msg = "hello world";
			byte b[] = msg.getBytes();
			DatagramPacket pack = new DatagramPacket(b,b.length,InetAddress.getLocalHost(),4501);
			try {
				sock.send(pack);
				sock.receive(pack);
				System.out.println(new String(pack.getData()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sock.close();
		}
		
	}
}
