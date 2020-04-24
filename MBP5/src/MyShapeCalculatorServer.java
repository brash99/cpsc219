import java.net.*;
import java.text.DecimalFormat;
import java.io.*;

public class MyShapeCalculatorServer {
	final static int PORT = 2222;	//port number should be > 1023

	public static void main (String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + PORT + ", " + e);
			System.exit(1);
		}

		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed: " + PORT + ", " + e);
				continue;
			}
			new ServerThread(clientSocket).start();
		}

	}
}


class ServerThread extends Thread {
	Socket socket = null;
	ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			String inputLine, outputLine;
			System.out.println("here i am ...");
			
		    DecimalFormat df2 = new DecimalFormat("#.##");
			
			while ((inputLine = is.readLine()) != null) {
				System.out.println(inputLine);
				
				String[] parsed = inputLine.split(" ");
				outputLine = "error";
				
				if (parsed[0].equals("circle")) {
					Double radius = Double.parseDouble(parsed[1]);
					if (parsed[2].equals("area")) {
						Double area = 3.14159265*radius*radius;
						outputLine = df2.format(area);
					} else {
						if (parsed[2].equals("circum")) {
							Double circum = 2.0*3.14159265*radius;
							outputLine = df2.format(circum);
						}
					}
					
				} else {
					if (parsed[0].equals("rect")) {
						Double length = Double.parseDouble(parsed[1]);
						Double width = Double.parseDouble(parsed[2]);
						if (parsed[3].equals("area")) {
							Double area = length*width;
							outputLine = df2.format(area);
						} else {
							if (parsed[3].equals("perim")) {
								Double perim = 2.0*(length+width);
								outputLine = df2.format(perim);
							}
						}
					
					}
				}
				
				os.println(outputLine); //just send back whatever was sent to us 
				os.flush();
			}
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) { 
			System.out.println("I/O error: " + e);
		}
	}
}
