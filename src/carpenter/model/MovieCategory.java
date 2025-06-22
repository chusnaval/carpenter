package carpenter.model;

public enum MovieCategory {
    ARTE_Y_ENTRETENIMIENTO(1, "Arte y Entretenimiento", "Amarillo"),
    LITERATURA_Y_NARRATIVA(2, "Literatura y Narrativa", "Azul"),
    CIENCIA_FICCION(3, "Ciencia Ficción", "Plateado"),
    ACCION(4, "Acción", "Rojo"),
    MISTERIO_CRIMEN_THRILLER(5, "Misterio, Crimen, Thriller", "Morado"),
    TERROR(6, "Terror", "Negro"),
    CONFLICTO(7, "Conflicto", "Naranja"),
    DOCUMENTAL(8, "Documental", "Verde");

    private final int number;
    private final String name;
    private final String color;

    MovieCategory(int number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
