package carpenter.commands;

import carpenter.model.Item;
import carpenter.model.ItemResponse;
import carpenter.model.MovieGenre;

public class CommandParser {

	public static final String ERROR_UNKNOWN_TYPE = "Unknown command type";

	public static ItemResponse parseCommand(String... parts) {
		String id = null;
		String response = "NONE";
		String type = null;
		String title = null;
		MovieGenre genre = null;
		String year = null;
		Boolean owned = null;
		String unknown = null;

		try {
			for (int i = 1; i < parts.length; i++) {

				switch (parts[i]) {
				case "-id":
				case "--id":
					if (i + 1 < parts.length) {
						id = parts[++i];
					}
					break;
				case "-t":
				case "-type":
				case "--t":
				case "--type":
					if (i + 1 < parts.length) {
						type = parts[++i];
					}
					break;
				case "-i":
				case "-title":
				case "--i":
				case "--title":
					if (i + 1 < parts.length) {
						title = parts[++i];
					}
					break;
				case "-g":
				case "-genre":
				case "--g":
				case "--genre":
					if (i + 1 < parts.length) {
						try {
							genre = MovieGenre.fromText(parts[++i]);
						} catch (Exception e) {
							response = "Invalid option for genre";
						}
					}
					break;
				case "-y":
				case "-year":
				case "--y":
				case "--year":
					if (i + 1 < parts.length) {
						year = parts[++i];
					}
					break;
				case "-o":
				case "--o":
					if (i + 1 < parts.length) {
						try {
							owned = Boolean.parseBoolean(parts[++i]);
						} catch (Exception e) {
							response = "Invalid option for owned";
						}
					}
					break;
				default:
					unknown = "Unknown parameters: " + parts[++i];
					break;
				}

				if (unknown != null) {
					return new ItemResponse(null, "Unknown parameters");
				}
			}

			if ("auto".equals(id)) {
				id = Item.generateId(title, genre, year);
			}

			if (type != null && title != null && genre != null && year != null) {

				Item item = new Item(id, type, title, genre, year, owned != null ? owned : false);
				return new ItemResponse(item, response);
			} else {
				return new ItemResponse(null, "Missing required parameters");
			}
		} catch (Exception e) {
			return new ItemResponse(null, "Error parsing command: " + e.getMessage());
		}
	}

}
