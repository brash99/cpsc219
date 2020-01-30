import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Lab1 extends JFrame implements ActionListener {
	static final long serialVersionUID = 1L;
	private JTextField assemblerInstruction;
	private JTextField binaryInstruction;
	private JTextField hexInstruction;
	private JLabel errorLabel;
	
	public Lab1() {
		setTitle("M68000");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		assemblerInstruction = new JTextField();
		assemblerInstruction.setBounds(25, 24, 134, 28);
		getContentPane().add(assemblerInstruction);
		assemblerInstruction.setColumns(10);

		JLabel lblAssemblyLanguage = new JLabel("Assembly Language");
		lblAssemblyLanguage.setBounds(25, 64, 160, 16);
		getContentPane().add(lblAssemblyLanguage);

		binaryInstruction = new JTextField();
		binaryInstruction.setBounds(25, 115, 180, 28);
		getContentPane().add(binaryInstruction);
		binaryInstruction.setColumns(10);

		hexInstruction = new JTextField();
		hexInstruction.setBounds(236, 115, 134, 28);
		getContentPane().add(hexInstruction);
		hexInstruction.setColumns(10);

		JLabel lblBinary = new JLabel("Binary Instruction");
		lblBinary.setBounds(25, 155, 190, 16);
		getContentPane().add(lblBinary);

		JLabel lblHexEquivalent = new JLabel("Hex Instruction");
		lblHexEquivalent.setBounds(236, 155, 131, 16);
		getContentPane().add(lblHexEquivalent);

		errorLabel = new JLabel("");
		errorLabel.setBounds(25, 235, 280, 16);
		getContentPane().add(errorLabel);

		JButton btnEncode = new JButton("Encode");
		btnEncode.setBounds(230, 25, 117, 29);
		getContentPane().add(btnEncode);
		btnEncode.addActionListener(this);

		JButton btnDecode = new JButton("Decode Binary");
		btnDecode.setBounds(30, 183, 170, 29);
		getContentPane().add(btnDecode);
		btnDecode.addActionListener(this);

		JButton btnDecodeHex = new JButton("Decode Hex");
		btnDecodeHex.setBounds(230, 183, 150, 29);
		getContentPane().add(btnDecodeHex);
		btnDecodeHex.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		errorLabel.setText("");
		if (evt.getActionCommand().equals("Encode")) {
			encode();
		} else if (evt.getActionCommand().equals("Decode Binary")) {
			decodeBin();
		} else if (evt.getActionCommand().equals("Decode Hex")) {
			decodeHex();
		}
	}

	public static void main(String[] args) {
		Lab1 window = new Lab1();
		window.setVisible(true);
	}

	String shortToHex(short x) {
		String ans="";
		for (int i=0; i<4; i++) {
			int hex = x & 15;
			char hexChar = "0123456789ABCDEF".charAt(hex);
			ans = hexChar + ans;
			x = (short)(x >> 4);
		}
		return ans;
	}

	String shortToBinary(short x) {
		String ans="";
		for(int i=0; i<16; i++) {
			ans = (x & 1) + ans;
			x = (short)(x >> 1);
		}
		return ans;
	}
	
/************************************************************************/
/*               DO NOT CHANGE ANYTHING ABOVE THIS POINT                 /
/************************************************************************/
	void encode() {
		binaryInstruction.setText("");
		hexInstruction.setText("");
		errorLabel.setText("");
		String assemble = assemblerInstruction.getText().trim().toUpperCase();
// Break the instruction into two pieces:
//    the operations and the registers
// Determine which operation it is and
//    start creating the machine instruction 
		StringTokenizer token = new StringTokenizer(assemble);
		if (token.countTokens() != 2) {
			errorLabel.setText("Illegal format for assembly instruction");
			return;
		}
		StringTokenizer token2 = new StringTokenizer(token.nextToken(), ".");
		String oper = token2.nextToken();
		int position = 0;
		if(oper.equals("ADD")) {
			position = 0xD000;
		} else if(oper.equals("SUB")) {
			position = 0x9000;
		} else if(oper.equals("MULS")) {
			position = 0xC000;
		} else if(oper.equals("DIVS")) {
			position = 0x8000;
		} else {
			errorLabel.setText("Illegal operation for assembly instruction");
			return;
		}
		
	
		String size = token2.nextToken();
		if (oper.equals("ADD")||oper.equals("SUB")) {
			if(size.equals("B")) 
				position = position | (0x000<<6);
			else if(size.equals("W"))
				position = position | (0x001<<6);
			else if(size.equals("L"))
				position = position | (0x010<<6);
			else {
				errorLabel.setText("Illegal operation for assembly1 instruction");
			}
		} else {
			// instruction must be MULS or DIVS if we are here
			// Thus, set these bits to 111 always
			position = position | (0x111<<6);
		}
			  
		//String result = oper + "." + size;
		
		   
		String operands = token.nextToken();
// Now tokenize the registers using a comma as the delimiter
// This should create two pieces -- the source and the destination
		token = new StringTokenizer(operands, ",", true); //because the returnDelims is true, "," also becomes a token
		if (token.countTokens() != 3 || operands.endsWith(",")) {
			errorLabel.setText("Illegal format for assembly instruction");
			return;
		}
		String source = token.nextToken();
// Encode the source register and put those bits in the proper place
		position = position | (encodeRegister(source) << 9);
		token.nextToken();	//skip the comma
		String destination = token.nextToken();
// Encode the destination register and put those bits in the proper place
		position = position | encodeRegister(destination);
// If no errors occurred, display the binary and hex encodings
		if (errorLabel.getText().equals("")) {
			binaryInstruction.setText(shortToBinary((short)position));
			hexInstruction.setText(shortToHex((short)position));
		}
	}
	
	int encodeRegister(String register) {
		int registerCode = 0;
		char registerNum = '0';
// Check for predecrement mode
		if(register.charAt(0)=='-') {
			if(register.length() != 4 || register.charAt(1) != ',' || register.charAt(4) != ',' ||
					register.charAt(2) != 'D') {
				errorLabel.setText("Illegal register specification");
				return 0;
			}
			registerCode = 4 << 3;
			registerNum = register.charAt(3);
		}
// Check for post increment mode
		else if(register.endsWith("+")) {
			if(register.length() != 3 || register.charAt(0) != '(' || register.charAt(3) != ')' ||
					register.charAt(1) != 'D') {
				errorLabel.setText("Illegal register specification");
				return 0;
			}
			registerCode = 2 << 3;
			registerNum = register.charAt(2);
		}
// Check for indirect mode
		else if(register.charAt(0) == '(') {
			if(register.length() != 3 || register.charAt(3) != ')' || register.charAt(1) != 'D') {
				errorLabel.setText("Illegal register specification");
				return 0;
			}
			registerCode = 1 << 3;
			registerNum = register.charAt(2);
		}
// Check for direct mode
		else if(register.charAt(0) == 'D') {
			if(register.length() != 2) {
				errorLabel.setText("Illegal register specification");
				return 0;
			}
			registerNum = register.charAt(1);
		}
		else {
			errorLabel.setText("Illegal register specification");
			return 0;
		}
		if(registerNum <'0' || registerNum > '7') {
			errorLabel.setText("Illegal register number");
			return 0;
		}
// Return the mode merged with the register number
		return registerCode + Integer.parseInt("" + registerNum);
	}

//
// To decode an instruction from binary or hex, convert the string to an int
// Then decode the machine instruction from that int
//

	void decodeBin() {
// Verify that the string is a legitimate binary number and convert to an int
		assemblerInstruction.setText("");
		hexInstruction.setText("");
		errorLabel.setText("");
		String s = binaryInstruction.getText().trim();
		if (s.length()!=16) {
			errorLabel.setText("Binary number can only be 16 digits");
			return;
		}
		int bin;
		try {
			bin = Integer.parseInt(s,2);
		} catch (Exception e) {
			errorLabel.setText("Illegal binary number");
			return;
		}
		hexInstruction.setText(shortToHex((short)bin));
		decode(bin);
	}

	void decodeHex() {
// Verify that the string is a legitimate hex number and convert to an int
		assemblerInstruction.setText("");
		binaryInstruction.setText("");
		String s = hexInstruction.getText().trim();
		if (s.length()!=4) {
			errorLabel.setText("Hex number must be 4 digits");
			return;
		}
		int binary;
		try {
			binary = Integer.parseInt(s,16);
		} catch (Exception e) {
			errorLabel.setText("Illegal Hex Number");
			return;
		}
		binaryInstruction.setText(shortToBinary((short)binary));
		decode(binary);
	}
	
	void decode(int binary) {
// Decode the operation part (high order 4 bits)
		String assemble = "";
		if ((binary & 0xF000) == 0xD000)
			assemble = "ADD";
		else if ((binary & 0xF000) == 0x9000)
			assemble = "SUB";
		else if ((binary & 0xF000) == 0xC000)
			assemble = "MULS";
		else if ((binary & 0xF000) == 0x8000)
			assemble = "DIVS";
		else {
			errorLabel.setText("Illegal Instruction");
			return;
		}
	String size2 = "";
			if (assemble == "ADD" || assemble == "SUB") {
				if ((binary & 0x01C0) == 0x0000) {
					size2 = "B";
				} else if ((binary & 0x01C0) == 0x0040) {
					size2 = "W";
				} else if ((binary & 0x01C0) == 0x0080) {
					size2 = "L";
				} else {
					errorLabel.setText("Illegal binary operation");
				}
            } else {
            	if ((binary & 0x01C0) != 0x01C0) {
            		errorLabel.setText("Illegal binary operation");
            	} else {
            		size2 = "W";
            	}
            }
		
// Isolate the source register and decode it
		int source = (binary >> 4) & 0x00E0;
		assemble = assemble+ "." +size2;
// Isolate the destination register and decode it
		int destination = binary & 0x3F;
		assemble = assemble +" " + registerDecode(source)+"," + registerDecode(destination); 
// If no errors occurred, display the assembler instruction
		if (errorLabel.getText().equals(""))
			assemblerInstruction.setText(assemble);
	}
	
	String registerDecode(int register) {
		String code = "";
// Convert the register number to a printable character
		code = Integer.toString(register & 7);
// Isolate the addressing mode and decode it
		int mode = (register >> 3) & 7;
		if(mode == 0) code = "D" + code;
		else if (mode == 1) code = "D" + code + "";
		else if (mode == 2) code = "D" + code + "+";
		else if (mode == 4) code = "-D" + code + "";
		else errorLabel.setText("Illegal instruction");
		return code;
	}

}
