package cn.edu.hqu.cst.android.SimpleServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThread implements Runnable {
	Socket s=null;
	BufferedReader br=null;

	public ServerThread(Socket s) throws UnsupportedEncodingException, IOException {
		this.s=s;
		br=new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
	}

	@Override
	public void run() {
		String content=null;
		while((content=readFromClient())!=null) {
			for(Iterator<Socket> it=SimpleServer.socketList.iterator();it.hasNext();) {
				Socket s=it.next();
				try {
				OutputStream os=s.getOutputStream();
				os.write((content+"\n").getBytes("utf-8"));
				}
				catch(SocketException e) {
					e.printStackTrace();
					it.remove();
					System.out.println(SimpleServer.socketList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private String readFromClient() {
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SimpleServer.socketList.remove(s);
		}
		
		return null;
	}

}
