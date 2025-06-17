package carpenter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import carpenter.commands.GreetCommand;

public class GuiUtils {

	private static Font retroFont = new Font("Monospaced", Font.BOLD, 14);
	
	public static JTextField createInputField() {
		JTextField inputField = new JTextField();
        inputField.setFont(retroFont);
        inputField.setBackground(Color.BLUE);
        inputField.setForeground(Color.YELLOW);
		return inputField;
	}

	public static JTextArea createTextArea() {

		JTextArea textArea = new JTextArea();
        textArea.setFont(retroFont);
        textArea.setBackground(Color.BLUE);
        textArea.setForeground(Color.YELLOW); 
        textArea.setText(new GreetCommand().execute() + "\n");
        textArea.setEditable(false);
		return textArea;
	}

	public static JFrame createMainFrame() {
		JFrame frame = new JFrame("Carpenter 0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
		return frame;
	}
}
