package carpenter.database;

import java.util.ArrayList;
import java.util.List;

import carpenter.model.Item;

public class ItemVault {

	public static void addItemRequest(Item item) {
		DatabaseConnector dbc = new DatabaseConnector();
		List<Item> items = new ArrayList<>();
		items.add(item);
		dbc.insertItems(items);
		dbc.close();

	}

	public static void addItemRequests(List<Item> items) {
		DatabaseConnector dbc = new DatabaseConnector();
		dbc.insertItems(items);
		dbc.close();

	}

	public static void addItemPurchase(Item item) {
		DatabaseConnector dbc = new DatabaseConnector();
		List<Item> items = new ArrayList<>();
		items.add(item);
		dbc.insertItems(items);
		dbc.close();

	}

	public static void addItemPurchases(List<Item> items) {
		DatabaseConnector dbc = new DatabaseConnector();
		dbc.insertItems(items);
		dbc.close();

	}

	public static List<Item> findItems(Item item, Boolean owned) {
		DatabaseConnector dbc = new DatabaseConnector();
		List<Item> items = dbc.searchItems(item.getType(), item.getTitle(),
				item.getGenre() != null ? item.getGenre().getTag() : null, item.getYear(), owned);
		dbc.close();
		return items;
	}

}
