package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import carpenter.commands.Command;
import carpenter.commands.CurrentDateCommand;
import carpenter.commands.CurrentTimeCommand;
import carpenter.commands.ExitCommand;
import carpenter.commands.GreetCommand;

public class RetroMSDOSApp {
	
	private static Map<String, Command> commandMap = new HashMap<>();
	
	static {
		// register commands
		// TODO add, register, find
		commandMap.put("time", new CurrentTimeCommand());
		commandMap.put("date", new CurrentDateCommand());
		commandMap.put("exit", new ExitCommand());
	}
	
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Carpenter 0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Use a monospaced font for a retro look
        Font retroFont = new Font("Monospaced", Font.BOLD, 14);

        // Create a text area with a retro color scheme
        JTextArea textArea = new JTextArea();
        textArea.setFont(retroFont);
        textArea.setBackground(Color.BLUE);
        textArea.setForeground(Color.YELLOW); 
        textArea.setText(new GreetCommand().execute() + "\n");
        textArea.setEditable(false);

        // Create an input field for commands
        JTextField inputField = new JTextField();
        inputField.setFont(retroFont);
        inputField.setBackground(Color.BLUE);
        inputField.setForeground(Color.YELLOW);

        // Add an action listener to the input field to handle command input
        inputField.addActionListener(e -> {
            String input = inputField.getText().trim();
            
            if(input.startsWith("exit")) {
            	frame.dispose();
            }else {
            	String[] parts = input.split("\\s+");
            	String commandName = parts[0];
            	String[] arguments = new String[parts.length - 1];
            	System.arraycopy(parts, 1, arguments, 0, parts.length - 1);
            	Command command = commandMap.get(commandName.toLowerCase());
            	
            	if (command!=null) {
            		
            		textArea.append(command.execute() + "\n");
            		inputField.setText("");
            	}else {
            		textArea.append("Comando desconocido: " + command + "\n");
            	}
            }
            
        });

        // Add components to the frame
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);
    }
}
