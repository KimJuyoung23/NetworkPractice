import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket client = new Socket("192.168.46.17", 20500);
		
		InputStream is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		OutputStream os = client.getOutputStream();//�ٸ��� ���´�
		DataOutputStream dos = new DataOutputStream(os);
		
		new Thread() {
			public void run() {
				while(true) {
					try {
						String msg = dis.readUTF();
						System.out.println(client.getInetAddress() + "���� �޼���: " + msg);
					}catch(Exception e) {
						System.out.println("������ ������ �����߽��ϴ�.");
						break;
					}
				}
			}
		}.start();
		
		while (true) {	
			dos.writeUTF(JOptionPane.showInputDialog("Ŭ���̾�"));
			dos.flush();//������ ����
	
			
		}
		// dis.close();
	}
}
