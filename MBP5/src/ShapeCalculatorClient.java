import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.*;

public class ShapeCalculatorClient extends JFrame implements ActionListener {
	static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		ShapeCalculatorClient scc = new ShapeCalculatorClient();
		scc.setVisible(true);
	}
	JRadioButton circleButton;
	JRadioButton rectangleButton;
	ButtonGroup shapeGroup;

	JRadioButton areaButton;
	JRadioButton circumferenceButton;
	ButtonGroup calculationGroup;

	JLabel radLenLabel;
	JTextField radLenTF;
	JLabel widthLabel;
	JTextField widthTF;

	JButton calcButton;
	JLabel ansLabel;
	JTextField ansTF;

	public ShapeCalculatorClient() {
		setTitle("Shape Calculator");
		setBounds(100, 100, 320, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		circleButton = new JRadioButton("Circle");
		circleButton.setBounds(25, 24, 100, 16);
		circleButton.setSelected(true);
		getContentPane().add(circleButton);

		rectangleButton = new JRadioButton("Rectangle");
		rectangleButton.setBounds(25, 64, 100, 16);
		getContentPane().add(rectangleButton);

		shapeGroup = new ButtonGroup();
		shapeGroup.add(circleButton);
		shapeGroup.add(rectangleButton);

		areaButton = new JRadioButton("Area");
		areaButton.setBounds(150, 24, 100, 16);
		areaButton.setSelected(true);
		getContentPane().add(areaButton);

		circumferenceButton = new JRadioButton("Circumference");
		circumferenceButton.setBounds(150, 64, 140, 16);
		getContentPane().add(circumferenceButton);

		calculationGroup = new ButtonGroup();
		calculationGroup.add(areaButton);
		calculationGroup.add(circumferenceButton);

		radLenLabel = new JLabel("Radius");
		radLenLabel.setBounds(45, 104, 50, 16);
		getContentPane().add(radLenLabel);

		radLenTF = new JTextField();
		radLenTF.setBounds(100, 104, 50, 16);
		getContentPane().add(radLenTF);

		widthLabel = new JLabel("Width");
		widthLabel.setBounds(170, 104, 50, 16);
		getContentPane().add(widthLabel);
		widthLabel.setVisible(false);

		widthTF = new JTextField();
		widthTF.setBounds(225, 104, 50, 16);
		getContentPane().add(widthTF);
		widthTF.setVisible(false);

		calcButton = new JButton("Calculate");
		calcButton.setBounds(100, 144, 80, 16);
		getContentPane().add(calcButton);

		ansLabel = new JLabel("Answer");
		ansLabel.setBounds(80, 184, 50, 16);
		getContentPane().add(ansLabel);

		ansTF = new JTextField();
		ansTF.setBounds(150, 184, 75, 16);
		getContentPane().add(ansTF);
		ansTF.setEditable(false);

		circleButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		calcButton.addActionListener(this);
		
		connectToServer();
	}

	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		System.out.println("Action event = " + action);
		if (action.equals("Circle")) {
			radLenLabel.setText("Radius");
			circumferenceButton.setText("Circumference");
			ansTF.setText("");
			widthLabel.setVisible(false);
			widthTF.setVisible(false);
		} else if (action.equals("Rectangle")) {
			radLenLabel.setText("Length");
			circumferenceButton.setText("Perimeter");
			ansTF.setText("");
			widthLabel.setVisible(true);
			widthTF.setVisible(true);
		} else if (action.equals("Area")) {
			ansTF.setText("");
		} else if (action.equals("Perimeter") || action.equals("Circumference")) {
			ansTF.setText("");
		} else if (action.equals("Calculate")) {
			calculate();
		} 
	}

// Do not change anything above this line
// Two global variables have been defined here and you will need to add more
	final static String server = "127.0.0.1";
	final static int port = 2222;
	//final static int port = 31415;
	Socket socket;
	BufferedReader streamIn;
	BufferedOutputStream streamOut;
	InputStreamReader dataIn;
	PrintWriter pw;
	

// Then implement the following methods

// The following method connects to the ShapeCalcultorServer
	void connectToServer() {
		
		
		try {
			//create the socket to connect to the server and PrintWriter, dataInputStream
        	socket = new Socket(server,port);
        	dataIn = new InputStreamReader(socket.getInputStream());
        	streamIn = new BufferedReader(dataIn);
        	streamOut = new BufferedOutputStream(socket.getOutputStream());
        	pw = new PrintWriter(streamOut);
		} catch (IOException e) {
			System.out.println("Error: Unable to connect to server port ");
		}

	}

// The following method sends an appropriate command to the server
// Then reads the result and displays it in the answer text field
	void calculate() {
		
		System.out.println("In calculate() ... ");
	
		
		try {
	    	//begin calculate if statements
			System.out.println("In try construct ...");
			if (circleButton.isSelected()) {
				String radius = radLenTF.getText();
				if (areaButton.isSelected()) {
					// area of circle
					String text = "circle " + radius + " area";
					pw.println(text);
					pw.flush();
					System.out.println("Getting answer ... ");
					String answer = streamIn.readLine();
					System.out.println("Got answer ... ");
					System.out.println(answer);
;			    	ansTF.setText(answer);
				} else {
					// perimeter of circle
					String text = "circle " + radius + " circum";
					pw.println(text);
					pw.flush();
					String answer = streamIn.readLine();
					System.out.println(answer);
			    	ansTF.setText(answer);
				}
			}
			
			if (rectangleButton.isSelected()) {
				String length = radLenTF.getText();
				String width = widthTF.getText();
				if (areaButton.isSelected()) {
					// area of rectangle
					String text = "rect " + length + " " + width + " area";
					pw.println(text);
					pw.flush();
					String answer = streamIn.readLine();
					System.out.println(answer);
			     	ansTF.setText(answer);
				} else {
					// perimeter of rectangle
					String text = "rect " + length + " " + width + " perim";
					pw.println(text);
					pw.flush();
					String answer = streamIn.readLine();
					System.out.println(answer);
			    	ansTF.setText(answer);
				}
			}

		} catch (IOException e) {
			System.out.println("Unable to communicate with server ... ");
			return;
		}
		
		

	}

}

