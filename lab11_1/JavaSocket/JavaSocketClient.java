import java.io.*;
import java.net.*;

public class JavaSocketClient {
	public static void main(String... args) {
		try {
			int portNumber = 5001;
			Socket sock = new Socket("localhost", portNumber);

			ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
			outStream.writeObject("Hello Android!");
			outStream.flush();

			ObjectInputStream inStream = new ObjectInputStream(sock.getInputStream());
			System.out.println(inStream.readObject());
			sock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
