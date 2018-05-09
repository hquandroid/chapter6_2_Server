package cn.edu.hqu.cst.android.SimpleServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleServer {
	public static ArrayList<Socket> socketList=new ArrayList<Socket>();
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss=new ServerSocket(30000);
		while(true) {
			Socket s=ss.accept();
			socketList.add(s);
			new Thread(new ServerThread(s)).start();
		}
		
		
	}

}
