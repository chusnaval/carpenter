package carpenter.commands;

public class GreetCommand implements Command {
	
	@Override
	public String execute(String... parts) {
		return "Hola, Chus!";
	}

}