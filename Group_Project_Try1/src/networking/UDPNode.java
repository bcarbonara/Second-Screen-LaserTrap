package networking;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UDPNode {
	private DatagramSocket sock;
	private ConcurrentLinkedQueue<DatagramPacket> packs;
	
	public UDPNode() throws SocketException{
		sock = new DatagramSocket();
		packs = new ConcurrentLinkedQueue<DatagramPacket>();
	}
	
	public void enque(DatagramPacket pck){
		packs.add(pck);
	}
	
	
	public void run(){
		Thread a = new Thread(new Runnable(){
			public void run(){
				while(true){
					DatagramPacket pack = packs.peek();
					if(pack != null){
						packs.remove();
						byte b[] = pack.getData();
						String s = new String(b);
						System.out.println(s.length() + ":" + s);
						DatagramPacket sendPack = new DatagramPacket(b,b.length,pack.getAddress(),pack.getPort());
						try {
							sock.send(sendPack);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		a.start();
		System.out.println("NODE STARTED!");
	}
	
}
