import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Lab2 implements ActionListener {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2 window = new Lab2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lab2() {
		initialize();
	}

	JTextArea encrypted;
	JTextArea decrypted;
	JTextField countLabel;
	JTextField letterLabel;
	JTextField subLabel;
	JTextField cryptTF;
	JTextField plainTF;
	JButton updateButton;
	JButton resetButton;
	JButton undoButton;

	Stack<Character> undoStack = new Stack<Character>();
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("CrytoQuote Helper");
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		encrypted = new JTextArea();
		encrypted.setText("Enter encrypted quote");
		encrypted.setWrapStyleWord(true);
		encrypted.setRows(4);
		encrypted.setFont(new Font("Courier", Font.PLAIN, 24));
		encrypted.setColumns(60);
		encrypted.setLineWrap(true);
		frame.getContentPane().add(encrypted);
		
		decrypted = new JTextArea();
		decrypted.setForeground(Color.RED);
		decrypted.setText("");
		decrypted.setWrapStyleWord(true);
		decrypted.setEditable(false);
		decrypted.setRows(4);
		decrypted.setFont(new Font("Courier", Font.PLAIN, 24));
		decrypted.setLineWrap(true);
		decrypted.setColumns(60);
		frame.getContentPane().add(decrypted);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(statsPanel);
		statsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		countLabel = new JTextField("                                                                              ");
		countLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		countLabel.setEditable(false);
		statsPanel.add(countLabel);
		
		letterLabel = new JTextField(" A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z ");
		letterLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		letterLabel.setEditable(false);
		statsPanel.add(letterLabel);
		
		subLabel = new JTextField(" -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - ");
		subLabel.setFont(new Font("Courier", Font.PLAIN, 18));
		subLabel.setForeground(Color.RED);
		subLabel.setEditable(false);
		statsPanel.add(subLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		frame.getContentPane().add(bottomPanel);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(Color.WHITE);
		
		cryptTF = new JTextField("   ");
		subPanel.add(cryptTF);
		JLabel equalsLabel = new JLabel("EQUALS");
		subPanel.add(equalsLabel);
		plainTF = new JTextField("   ");
		plainTF.addActionListener(this);
		subPanel.add(plainTF);
		bottomPanel.add(subPanel, BorderLayout.WEST);
		
		JLabel spacer = new JLabel("                         ");
		bottomPanel.add(spacer, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		buttonPanel.add(updateButton);
		undoButton = new JButton("Undo");
		undoButton.addActionListener(this);
		undoButton.setEnabled(false);
		buttonPanel.add(undoButton);
		resetButton = new JButton("Reset Subs");
		resetButton.addActionListener(this);
		buttonPanel.add(resetButton);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);

	}
	
/************************************************************************/
/*               DO NOT CHANGE ANYTHING ABOVE THIS POINT                 /
/************************************************************************/
	
	private boolean firstpass = true;
	String first_encrypted;
	String old_decr;
	String old_subs;
	
	static HashMap<Character, Integer> characterCount(String inputString) 
    { 
		
		// Creating a HashMap containing char 
		// as a key and occurrences as  a value 
		HashMap<Character, Integer> charCountMap 
            = new HashMap<Character, Integer>(); 
  
        // Converting given string to char array 
  
        char[] strArray = inputString.toCharArray(); 
  
        // checking each char of strArray 
        for (char c : strArray) { 
            if (charCountMap.containsKey(c)) { 
  
                // If char is present in charCountMap, 
                // incrementing it's count by 1 
                charCountMap.put(c, charCountMap.get(c) + 1); 
            } 
            else { 
  
                // If char is not present in charCountMap, 
                // putting this char to charCountMap with 1 as it's value 
                charCountMap.put(c, 1); 
            } 
        } 
        
        return charCountMap;
  
    } 

    public void actionPerformed(ActionEvent e) {
    	
		System.out.println(encrypted.getText());
		String encr = encrypted.getText();
		encr = encr.toUpperCase();
		if (!encr.equals(first_encrypted) && firstpass==false) {
			firstpass = true;
			countLabel.setText("                                                                              ");
			subLabel.setText(" -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - ");
		}
		
		String decr = decrypted.getText();
		decr = decr.toUpperCase();
		String count = countLabel.getText();
		String letters = letterLabel.getText();
		String subs = subLabel.getText();
		
 		if (firstpass) {
 			first_encrypted = encr;
 			HashMap<Character, Integer> charCountMap = characterCount(encr);
 			
 			System.out.println(charCountMap.get('A'));
 		
 			System.out.println(letters);
 		
 			for (int i=0; i<letters.length(); i++) {
 				//System.out.println(letters.charAt(i) + " " + charCountMap.get(letters.charAt(i)));
 				if (letters.charAt(i) >= 'A' && letters.charAt(i) <= 'Z') {

 					if (charCountMap.get(letters.charAt(i)) != null) {
 						//System.out.println("Replace!");
 						int value = charCountMap.get(letters.charAt(i));
 						int length = String.valueOf(value).length();
 						count = count.substring(0,i-length+1)+value+count.substring(i-length+2);
 					}
 
 				}
 			}
 			countLabel.setText(count);
 			firstpass = false;
 		}
 		  	
    	String command = e.getActionCommand();
    	
    	if (command.equals("Update")) {

    		String crypt_char = cryptTF.getText().trim();
    		crypt_char = crypt_char.toUpperCase();
    		
    		String plain_char = plainTF.getText().trim();
    		plain_char = plain_char.toUpperCase();
    		
    		boolean goodInput;
    		try {
    			boolean goodCryptA = crypt_char.charAt(0) >= 'A';
    			boolean goodCryptZ = crypt_char.charAt(0) <= 'Z';
    			boolean goodCrypt = goodCryptA && goodCryptZ;
    			boolean goodPlainA = plain_char.charAt(0) >= 'A';
    			boolean goodPlainZ = plain_char.charAt(0) <= 'Z';
    			boolean goodPlain = goodPlainA && goodPlainZ;
    			goodInput = goodCrypt && goodPlain;
    			   			
    		} catch (Exception exception) {
    			crypt_char = "";
    			plain_char = "";
    			goodInput = false;

    			if (decr.length() != encr.length()) {
    				decr = encr;
    				for(int i = 0; i < encr.length() ; i++) { 
    					char c = encr.charAt(i);
    					char ee;
    					if (c >= 'A' && c<= 'Z'){
    						ee = '-';
    					} else {
    						ee = c;
    					}
    					decr = decr.substring(0,i)+ee+decr.substring(i+1);
    				}
    			}    
    		}
    		  		
    		if (goodInput) {
    			if (decr.length() != encr.length()) {
    				decr = encr;
    				for(int i = 0; i < encr.length() ; i++) { 
    					char c = encr.charAt(i);
    					char ee;
    					if (c == crypt_char.charAt(0)) {
    						ee = plain_char.charAt(0);
    					} else if (c >= 'A' && c<= 'Z'){
    						ee = '-';
    					} else {
    						ee = c;
    					}
    					decr = decr.substring(0,i)+ee+decr.substring(i+1);
    				}
    			} else {
					old_subs = subs;
		    		old_decr = decr;
    				for(int i = 0; i < encr.length() ; i++) { 
    					char c = encr.charAt(i);
    					char ee;
    					if (c == crypt_char.charAt(0)) {
    						ee = plain_char.charAt(0);
    						int value = 3*(c-65)+1;
        					subs = subs.substring(0,value)+ee+subs.substring(value+1);
        					undoButton.setEnabled(true);
    					} else { 
    						ee = decr.charAt(i);
    					}   			
    					decr = decr.substring(0,i)+ee+decr.substring(i+1);
    					subLabel.setText(subs);
    				}
    			}
   		
    			System.out.println(decr);
    			
    		} // ends goodInput
			decrypted.setText(decr);
			System.out.println(old_decr);
			System.out.println(old_subs);
    	} else if (command == "Reset Subs") {
    		decr = encr;
			for(int i = 0; i < encr.length() ; i++) { 
				char c = encr.charAt(i);
				char ee;
				if (c >= 'A' && c<= 'Z'){
					ee = '-';
				} else {
					ee = c;
				}
				decr = decr.substring(0,i)+ee+decr.substring(i+1);
			}
			decrypted.setText(decr);
			subLabel.setText(" -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - ");
		}  else if (command == "Undo") {
			decrypted.setText(old_decr);
			subLabel.setText(old_subs);
			undoButton.setEnabled(false);			
		} // end Undo
    } // ends actionPerformed
} // ends main class
    		  
    		
    	
    	
    	
