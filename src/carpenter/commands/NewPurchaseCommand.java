package carpenter.commands;

import carpenter.database.ItemVault;

public class NewPurchaseCommand implements Command {

	private final static String SUCESSFULL = "Se ha almacenado correctamente la compra.";
	private final static String ERROR_UNKNOWN_TYPE = "Usage: req -t <type> -i <title> -g <genre> -y <year>\n";

	@Override
	public String execute(String... parts) {
		String type = null;
		String title = null;
		String genre = null;
		String year = null;
		String unknown = null;
		if (parts.length < 9) {
			return ERROR_UNKNOWN_TYPE;
		}
		for (int i = 1; i < parts.length; i++) {
			switch (parts[i]) {
			case "-t":
			case "-type":
			case "--t":
			case "--type":
				type = parts[++i];
				break;
			case "-i":
			case "-title":
			case "--i":
			case "--title":
				title = parts[++i];
				break;
			case "-g":
			case "-genre":
			case "--g":
			case "--genre":
				genre = parts[++i];
				break;
			case "-y":
			case "-year":
			case "--y":
			case "--year":
				year = parts[++i];
				break;
			default:
				unknown = parts[++i];
			}
		}

		if (unknown != null) {
			return "Unknown option: " + unknown + "\n";
		} else if (type == null || title == null) {
			return ERROR_UNKNOWN_TYPE;
		}

		ItemVault.addItemPurchase(type, title, genre, year);

		return SUCESSFULL;
	}

}
