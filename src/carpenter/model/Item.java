package carpenter.model;

import java.util.List;

public class Item {
	private String id;
	private String type;
	private String title;
	private MovieGenre genre;
	private String year;
	private boolean owned;

	public Item(String id, String type, String title, MovieGenre genre, String year, boolean owned) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.owned = owned;
	}

	// Método estático para generar el ID
	public static String generateId(String title, MovieGenre genre, String year) {
		// Eliminar artículos del título
		String processedTitle = title.replaceAll("\\b(the|a|an|el|la|los|las)\\b\\s*", "");

		// Obtener los primeros tres caracteres del título procesado
		String titlePart = processedTitle.length() >= 3 ? processedTitle.substring(0, 3).toUpperCase()
				: processedTitle.toUpperCase();

		// Obtener la etiqueta del género
		String genrePart = genre.getTag();

		// Obtener los cuatro dígitos del año
		String yearPart = year.length() >= 4 ? year.substring(0, 4) : String.format("%4s", year).replace(' ', '0');

		return titlePart + "-" + genrePart + "-" + yearPart;
	}

	// Getters y Setters
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public MovieGenre getGenre() {
		return genre;
	}

	public String getYear() {
		return year;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(MovieGenre genre) {
		this.genre = genre;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public String toFormattedColumns() {
		// Formatear cada atributo para que ocupe un ancho fijo
		String idFormatted = String.format("%-12s", id);
		String typeFormatted = String.format("%-15s", type);
		String titleFormatted = String.format("%-30s", title);
		String genreFormatted = String.format("%-20s", genre.getName());
		String yearFormatted = String.format("%-6s", year);
		String ownedFormatted = String.format("%-7s", owned ? "Sí" : "No");

		// Combinar todos los atributos formateados en una sola línea
		return String.format("%s | %s | %s | %s | %s | %s", idFormatted, typeFormatted, titleFormatted, genreFormatted,
				yearFormatted, ownedFormatted);
	}

	public static String listToFormattedColumns(List<Item> items) {
		StringBuilder sb = new StringBuilder();

		// Añadir encabezado
		sb.append(String.format("%-12s | %-15s | %-30s | %-20s | %-6s | %-7s\n", "ID", "Tipo", "Título", "Género",
				"Año", "Conseguido"));
		sb.append("-".repeat(95)).append("\n");

		// Añadir cada Item formateado
		for (Item item : items) {
			sb.append(item.toFormattedColumns()).append("\n");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("ID: %s, Type: %s, Title: %s, Genre: %s, Year: %s, Owned: %s", id, type, title,
				genre.getName(), year, owned);
	}

	public String getSuggestedId() {
		return Item.generateId(title, genre, year);
	}
}
