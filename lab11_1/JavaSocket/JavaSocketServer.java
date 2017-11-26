import java.io.*;
import java.net.*;

public class JavaSocketServer {
	public static void main(String... args) {
		try {
			int portNumber = 5001;

			System.out.println("Java Socket Start...");
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("Listening at port: " + portNumber);

			while (true) {
				Socket sock = serverSocket.accept();
				InetAddress clientHost = sock.getLocalAddress();
				int clientPort = sock.getPort();
				System.out.println("Connect the client. Host is: " + clientHost + " " + clientPort);

				ObjectInputStream inStream = new ObjectInputStream(sock.getInputStream());
				Object obj = inStream.readObject();
				System.out.println("Input: " + obj);

				ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
				outStream.writeObject(obj + "... from server");
				outStream.flush();
				sock.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
