import java.net.*;
import java.io.*;

public class EchoServerMultiThreaded {
	final static int PORT = 2222;	//port number should be > 1023

	public static void main (String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + PORT + ", " + e);
			System.exit(1);
		}

		// listen forever
		while (true) {
			Socket clientSocket = null;
			try {
				// Ask the main computer, over the serverSocket, if there
				// are any clients that would like to communicate
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed: " + PORT + ", " + e);
				continue;
			}
			new EchoServerThread(clientSocket).start();
		}

	}
}


class EchoServerThread extends Thread {
	Socket socket = null;

	EchoServerThread(Socket socket) {
		this.socket = socket;
	}
	
	// start communicating with the client through the clientSocket
	public void run() {
		try {
			
			// establish a way to read and write
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			
			String inputLine, outputLine;
			
			while ((inputLine = is.readLine()) != null) {
				outputLine = inputLine;
				os.println(outputLine); //just send back whatever was sent to us 
				os.flush();
				
				if (outputLine.equals("quit")) break;
			}
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) { 
			System.out.println("I/O error: " + e);
		}
	}
}
