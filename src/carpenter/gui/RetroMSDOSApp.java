package carpenter.gui;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import carpenter.commands.Command;
import carpenter.commands.CurrentDateCommand;
import carpenter.commands.CurrentTimeCommand;
import carpenter.commands.ExitCommand;
import carpenter.commands.FindItemCommand;
import carpenter.commands.NewPurchaseCommand;
import carpenter.commands.RequestNewPurchaseCommand;

public class RetroMSDOSApp {
	
	private static Map<String, Command> commandMap = new HashMap<>();

	private static int historyIndex = -1;
	static {
		// register commands
		commandMap.put("time", new CurrentTimeCommand());
		commandMap.put("req", new RequestNewPurchaseCommand());
		commandMap.put("add", new NewPurchaseCommand());
		commandMap.put("find", new FindItemCommand());
		commandMap.put("date", new CurrentDateCommand());
		commandMap.put("exit", new ExitCommand());
	}
	
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = GuiUtils.createMainFrame();

        // Create a text area with a retro color scheme
        JTextArea textArea =  GuiUtils.createTextArea();

        // Create an input field for commands
        JTextField inputField = GuiUtils. createInputField();

        List<String> commandHistory = new ArrayList<>();
  
        
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!commandHistory.isEmpty()) {
                        if (historyIndex == -1) {
                            historyIndex = commandHistory.size() - 1;
                        } else if (historyIndex > 0) {
                            historyIndex--;
                        }
                        inputField.setText(commandHistory.get(historyIndex));
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (historyIndex >= 0 && historyIndex < commandHistory.size() - 1) {
                        historyIndex++;
                        inputField.setText(commandHistory.get(historyIndex));
                    } else if (historyIndex == commandHistory.size() - 1) {
                        historyIndex = -1;
                        inputField.setText("");
                    }
                }
            }
        });
        
        
        // Add an action listener to the input field to handle command input
        inputField.addActionListener(e -> {
            String input = inputField.getText().trim();
            commandHistory.add(input);
            historyIndex = -1; // Reset history index
            boolean inQuotes = false;
            List<String> parts = new ArrayList<>();
            StringBuilder currentPart = new StringBuilder();
            
            if(input.startsWith("exit")) {
            	frame.dispose();
            }else {
            	for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);

                    if (c == '"') {
                        inQuotes = !inQuotes; // Toggle the inQuotes flag
                    } else if (Character.isWhitespace(c) && !inQuotes) {
                        if (currentPart.length() > 0) {
                            parts.add(currentPart.toString());
                            currentPart.setLength(0); // Reset the current part
                        }
                    } else {
                        currentPart.append(c);
                    }
                }
            	 if (currentPart.length() > 0) {
                     parts.add(currentPart.toString());
                 }
            	String[] arrParts = parts.toArray(new String[0]);
            	
            	String commandName = arrParts[0];
            	String[] arguments = new String[arrParts.length - 1];
            	System.arraycopy(arrParts, 1, arguments, 0, arrParts.length - 1);
            	Command command = commandMap.get(commandName.toLowerCase());
            	
            	if (command!=null) {
            		
            		textArea.append(command.execute(arrParts) + "\n");
            		
            	}else {
            		textArea.append("Comando desconocido: " + command + "\n");
            	}
            	inputField.setText("");
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
