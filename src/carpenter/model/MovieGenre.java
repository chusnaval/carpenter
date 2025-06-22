package carpenter.model;

public enum MovieGenre {
	// Animación y Entretenimiento
	ANIMACION("Animación", "ANI", 1.1f), ANIME("Anime", "ANM", 1.2f), INFANTIL("Infantil", "INF", 1.3f),
	MUSICAL("Musical", "MUS", 1.4f), NAVIDENA("Navideña", "NAV", 1.5f),

	// Literatura y Narrativa
	DRAMA("Drama", "DRA", 2.1f), ROMANTICA("Romántica", "ROM", 2.2f), COMEDIA("Comedia", "COM", 2.3f),

	// Ciencia Ficción
	CIENCIA_FICCION("Ciencia Ficción", "CIF", 3.1f),

	// Acción
	ACCION("Acción", "ACC", 4.1f), AVENTURA("Aventura", "AVE", 4.2f), FANTASIA("Fantasía", "FAN", 4.3f),

	// Misterio, Crimen, Thriller
	THRILLER("Thriller", "THR", 5.1f), MISTERIO("Misterio", "MIS", 5.2f), CRIMEN("Crimen", "CRI", 5.3f),

	// Terror
	TERROR("Terror", "TER", 6.1f),

	// Conflicto
	BELICO("Bélico", "BEL", 7.1f), WESTERN("Western", "WES", 7.2f),

	// Documental
	DOCUMENTAL("Documental", "DOC", 8.1f), DEPORTES("Deportes", "DEP", 8.2f);

	private final String name;
	private final String tag;
	private final float order;

	MovieGenre(String name, String tag, float order) {
		this.name = name;
		this.tag = tag;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public String getTag() {
		return tag;
	}

	public float getOrder() {
		return order;
	}

	public static MovieGenre fromText(String text) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Input text cannot be null or empty");
		}

		// Convertir el texto de entrada a mayúsculas para comparar con las etiquetas
		String upperText = text.toUpperCase();

		// Buscar por etiqueta
		for (MovieGenre genre : MovieGenre.values()) {
			if (genre.getTag().equals(upperText)) {
				return genre;
			}
		}

		// Buscar por nombre (ignorando mayúsculas y minúsculas)
		for (MovieGenre genre : MovieGenre.values()) {
			if (genre.getName().equalsIgnoreCase(text)) {
				return genre;
			}
		}

		// Si no se encuentra ninguna coincidencia, lanzar una excepción o devolver un
		// valor por defecto
		throw new IllegalArgumentException("No matching MovieGenre found for input: " + text);
	}
}
