import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Lab3 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel addressLabel;
	private JPanel addressPanel;
	private JTextField addressTextField;
	private JPanel buttonPanel;
	private JLabel cityLabel;
	private JPanel cityStatePanel;
	private JTextField cityTextField;
	private JLabel givenNameLabel;
	private JPanel givenNamePanel;
	private JTextField givenNameTextField;
	private JLabel stateLabel;
	private JTextField stateTextField;
	private JLabel surnameLabel;
	private JPanel surnamePanel;
	private JTextField surnameTextField;
	private JButton getButton;
	private JLabel positionLabel;
	private JTextField positionTF;
	private JButton nextButton;
	private JButton previousButton;
	
	String bookFile = null;
	String indexFile = null;
	
	RandomAccessFile index; 
	RandomAccessFile book;
	
	private static JFileChooser fc = new JFileChooser();

	public Lab3() {
		
		System.out.println("Here I am 1");
		
		setTitle("Address Book");
		setBounds(100, 100, 704, 239);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridLayout(5, 0));
		
		System.out.println("Here I am 2");

		surnamePanel = new JPanel();
		surnameLabel = new JLabel();
		surnameTextField = new JTextField();
		givenNamePanel = new JPanel();
		givenNameLabel = new JLabel();
		givenNameTextField = new JTextField();
		addressPanel = new JPanel();
		addressLabel = new JLabel();
		addressTextField = new JTextField();
		cityStatePanel = new JPanel();
		cityLabel = new JLabel();
		cityTextField = new JTextField();
		stateLabel = new JLabel();
		stateTextField = new JTextField();
		buttonPanel = new JPanel();
		getButton = new JButton();
		positionLabel = new JLabel("Position:");
		positionTF = new JTextField("            ");
		nextButton = new JButton();
		previousButton = new JButton();

		surnamePanel.setName("surnamePanel");
		
		System.out.println("Here I am 3");

		surnameLabel.setText("Surname");
		surnameLabel.setName("surnameLabel");
		surnamePanel.add(surnameLabel);

		surnameTextField.setColumns(45);
		surnameTextField.setText("");
		surnameTextField.setName("surnameTextField");
		surnamePanel.add(surnameTextField);

		getContentPane().add(surnamePanel);

		givenNamePanel.setName("givenNamePanel");

		givenNameLabel.setText("Given Names");
		givenNameLabel.setName("givenNameLabel");
		givenNamePanel.add(givenNameLabel);

		givenNameTextField.setColumns(45);
		givenNameTextField.setText("");
		givenNameTextField.setName("givenNameTextField");
		givenNamePanel.add(givenNameTextField);

		getContentPane().add(givenNamePanel);

		addressPanel.setName("addressPanel");

		addressLabel.setText("Street Address");
		addressLabel.setName("addressLabel");
		addressPanel.add(addressLabel);

		addressTextField.setColumns(45);
		addressTextField.setText("");
		addressTextField.setName("addressTextField");
		addressPanel.add(addressTextField);

		getContentPane().add(addressPanel);

		cityStatePanel.setName("cityStatePanel");

		cityLabel.setText("City");
		cityLabel.setName("cityLabel");
		cityStatePanel.add(cityLabel);

		cityTextField.setColumns(30);
		cityTextField.setText("");
		cityTextField.setName("cityTextField");
		cityStatePanel.add(cityTextField);

		stateLabel.setText("State");
		stateLabel.setName("stateLabel");
		cityStatePanel.add(stateLabel);

		stateTextField.setColumns(5);
		stateTextField.setText("");
		stateTextField.setName("stateTextField");
		cityStatePanel.add(stateTextField);

		getContentPane().add(cityStatePanel);

		buttonPanel.setName("buttonPanel");

		getButton.setText("Get");
		getButton.setName("getButton");
		getButton.addActionListener(this);
		buttonPanel.add(getButton);
		
		buttonPanel.add(positionLabel);
		buttonPanel.add(positionTF);

		nextButton.setText("Next");
		nextButton.setName("nextButton");
		nextButton.addActionListener(this);
		buttonPanel.add(nextButton);

		previousButton.setText("Previous");
		previousButton.setName("previousButton");
		previousButton.addActionListener(this);
		buttonPanel.add(previousButton);

		getContentPane().add(buttonPanel);
		
		System.out.println("Here I am 4");

		getFiles();
		
		System.out.println("Here I am 5");
		
		try {
			index = new RandomAccessFile(indexFile, "r");
			book = new RandomAccessFile(bookFile, "r");
		} catch(IOException ioe) {
			System.out.println(ioe);
			System.exit(0);
		}
		
		System.out.println("Here I am 6");

	}
	
	void getFiles() {
		
		//JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Select the Address Book");
		int returnVal = fc.showOpenDialog(this);
		if (returnVal != JFileChooser.APPROVE_OPTION)
			System.exit(0);
		bookFile = fc.getSelectedFile().getPath();
//the index file is usually in the same directory as the address book
		fc.setCurrentDirectory(new File(fc.getSelectedFile().getParent()));
		fc.setDialogTitle("Select the Index File");
		returnVal = fc.showOpenDialog(this);
		if (returnVal != JFileChooser.APPROVE_OPTION)
			System.exit(0);
		indexFile = fc.getSelectedFile().getPath();
	}


	public static void main(String[] args) {
		System.out.println("Starting main ...");
		Lab3 window = new Lab3();
		System.out.println("Window Object created ...");
		window.setVisible(true);
		System.out.println("Window set to visible ...");
	}

/***************************************************************/

// implement this method
// do not change any of the code that I have written
// you may add additional methods if you wish
	
	private int currentPosition;
	
	public void actionPerformed(ActionEvent evt) {
		
		System.out.println("Starting action performed ...");
		
		System.out.println(evt.getActionCommand());
		
		String command = evt.getActionCommand();
		if (command.equals("Get")) {
			try {
				int position = Integer.parseInt(positionTF.getText().trim());
				retrieveAndSet(position);
			} catch (Exception e) {
				System.out.println("Exception!!!");
				positionTF.setText(Integer.toString(currentPosition));
			}
		}
		
		if (command.equals("Next")) {
			currentPosition = currentPosition + 1;
			retrieveAndSet(currentPosition);
			positionTF.setText(Integer.toString(currentPosition));
		}
		
		if (command.equals("Previous")) {
			currentPosition = currentPosition - 1;
			if (currentPosition > 0) {
				retrieveAndSet(currentPosition);
				positionTF.setText(Integer.toString(currentPosition));
			} else {
				System.out.println("Position must be > 0!!");
				currentPosition = currentPosition + 1;
			}
		}
		
	}
	
	public void retrieveAndSet(int position) {
		
		try {
			long offset = 8*(position-1);
			RandomAccessFile raf = new RandomAccessFile(indexFile,"r");
			raf.seek(offset);
			long indexPosition = raf.readLong();
			System.out.println("Entry in index file = " + indexPosition);
	
			RandomAccessFile raf2 = new RandomAccessFile(bookFile,"r");
			raf2.seek(indexPosition);
			String surname = raf2.readUTF();
			String givenNames = raf2.readUTF();
			String streetAddress = raf2.readUTF();
			String city = raf2.readUTF();
			String state = raf2.readUTF();
	
			surnameTextField.setText(surname);
			givenNameTextField.setText(givenNames);
			addressTextField.setText(streetAddress);
			cityTextField.setText(city);
			stateTextField.setText(state);
			
			currentPosition = position;
		
			raf.close();
			raf2.close();
		} catch (FileNotFoundException f) {
			System.out.println("Index file not found!!!");
		} catch (Exception e) {
			System.out.println("Exception!!!");
			positionTF.setText(Integer.toString(currentPosition));
		}
		
	}

}
