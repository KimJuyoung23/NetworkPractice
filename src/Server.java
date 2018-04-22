import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {
	public static void main(String[] args) throws Exception {// 원래는 예외처리 해줘야함
		ServerSocket server = new ServerSocket(20500);
		System.out.println("연결을 대기하는 중 입니다...");
		Socket sock = server.accept();// 서버소켓 리스닝 상태로 전환. 요청이 들어올 때까지 무한루프 / socket형 반환하니까 받아줌
		System.out.println(sock.getInetAddress() + "님이 연결하셨습니다.");

		OutputStream os = sock.getOutputStream();// 다리를 놓는다
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = sock.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						String msg = dis.readUTF();
						System.out.println(sock.getInetAddress() + "님의 메세지: " + msg);
					}catch(Exception e) {
						System.out.println("상대방이 연결을 종료했습니다.");
						break;
					}
				}
			}
		}.start();
		
		while (true) {
			
			dos.writeUTF(JOptionPane.showInputDialog("서버"));
			dos.flush();// 데이터 보냄
		}
		// dos.close();//연결 끊기

	}
}
