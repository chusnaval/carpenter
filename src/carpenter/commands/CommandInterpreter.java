package carpenter.commands;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandInterpreter {
	
	private Map<String, Command> commandMap = new HashMap<>();
	
	public CommandInterpreter() {
		// register commands
		commandMap.put("hola", new GreetCommand());
		commandMap.put("hora", new CurrentTimeCommand());
		commandMap.put("fecha", new CurrentDateCommand());
		commandMap.put("exit", new ExitCommand());
	}
	
	public void run() {
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				System.out.print("> ");
				String input = scanner.nextLine().trim();
				String[] parts = input.split("\\s+");
				
				String commandName = parts[0];
				String[] args = new String[parts.length - 1];
				System.arraycopy(parts, 1, args, 0, parts.length - 1);
				Command command = commandMap.get(commandName.toLowerCase());
				
				if(command != null) {
					command.execute();
				} else {
					System.out.println("Comando desconocido: " + input);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		CommandInterpreter interpreter = new CommandInterpreter();
		interpreter.run();
	}
}
		
			
		
	