import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {
	public static void main(String[] args) throws Exception {// ������ ����ó�� �������
		ServerSocket server = new ServerSocket(20500);
		System.out.println("������ ����ϴ� �� �Դϴ�...");
		Socket sock = server.accept();// �������� ������ ���·� ��ȯ. ��û�� ���� ������ ���ѷ��� / socket�� ��ȯ�ϴϱ� �޾���
		System.out.println(sock.getInetAddress() + "���� �����ϼ̽��ϴ�.");

		OutputStream os = sock.getOutputStream();// �ٸ��� ���´�
		DataOutputStream dos = new DataOutputStream(os);

		InputStream is = sock.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						String msg = dis.readUTF();
						System.out.println(sock.getInetAddress() + "���� �޼���: " + msg);
					}catch(Exception e) {
						System.out.println("������ ������ �����߽��ϴ�.");
						break;
					}
				}
			}
		}.start();
		
		while (true) {
			
			dos.writeUTF(JOptionPane.showInputDialog("����"));
			dos.flush();// ������ ����
		}
		// dos.close();//���� ����

	}
}
