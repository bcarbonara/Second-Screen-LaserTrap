package networking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


public class UDPServer {
	private DatagramSocket sock;
	private List<UDPNode> nodes;
	public final int NODE_COUNT = 5;
	private int node_itr;
	public UDPServer() throws SocketException{
		sock = new DatagramSocket(4501);
		nodes = new ArrayList<UDPNode>();
		for(int i = 0 ; i < NODE_COUNT ; i++){
			UDPNode n = new UDPNode();
			n.run();
			nodes.add(n);
			
		}
		node_itr = 0;
		
	}
	
	public void run() throws IOException{
		DatagramPacket pack = new DatagramPacket(new byte[1024],1024);
		sock.receive(pack);
		nodes.get(node_itr).enque(pack);
		node_itr++;
		node_itr = node_itr % NODE_COUNT;

	}
	
	public static void main(String args[]){
		try {
			UDPServer serv = new UDPServer();
			while(true){
				serv.run();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
