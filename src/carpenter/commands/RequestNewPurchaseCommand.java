package carpenter.commands;

import carpenter.database.ItemVault;
import carpenter.model.Item;
import carpenter.model.ItemResponse;

public class RequestNewPurchaseCommand implements Command {

	private final static String SUCESSFULL = "Se ha almacenado correctamente la petición de búsqueda.";
	private final static String ERROR_UNKNOWN_TYPE = "Usage: req -id <id> -t <type> -i <title> [-g <genre>] [-y <year>]\n";

	@Override
	public String execute(String... parts) {
		ItemResponse response = null;
		if (parts.length < 11) {
			return ERROR_UNKNOWN_TYPE;
		}
		
		response = CommandParser.parseCommand(parts);
		
		Item item = response.getItem();
		item.setOwned(false);
		ItemVault.addItemPurchase(item);

		return SUCESSFULL;
	}

}
