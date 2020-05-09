import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;

class MyThread extends Thread {
	Socket socket = null;
	MyThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			
			String inputLine, outputLine;
			
			outputLine = "";
			
			boolean flag = true;
			
			while ((inputLine = is.readLine()) != null && (flag = true)) {
				
				// DELAY n
				// QUIT
				
				String[] parsed = inputLine.split(" ");
				
				// parsed = {"DELAY","n"}
				// parsed = {"QUIT"}
				
				if (parsed[0].equals("DELAY")) {

					// wait n milliseconds
					int milliseconds = Integer.parseInt(parsed[1]);
					try {
						wait(milliseconds);
					} catch (Exception e) {
						System.out.println("Problem waiting ...");
					}
					
					outputLine = "OK";
					
				} else {
					if (parsed[0].equals("QUIT")) {
						outputLine = "BYE";
						flag = false;
					} else {
						outputLine = "INVALID COMMAND";
					}
				} 
				
				os.println(outputLine); 
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