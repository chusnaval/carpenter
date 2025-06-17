package carpenter.commands;

public class ExitCommand implements Command {

	@Override
	public String execute(String... parts) {
		return "Terminando.";
	}
}