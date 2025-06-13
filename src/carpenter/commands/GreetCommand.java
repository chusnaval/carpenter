package carpenter.commands;

public class GreetCommand implements Command {
	
	@Override
	public String execute() {
		return "Hola, Chus!";
	}

}