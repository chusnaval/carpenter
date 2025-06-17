package carpenter.commands;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentDateCommand implements Command {
    @Override
    public String execute(String... parts) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Format and print the current date
        String formattedDate = currentDate.format(formatter);
        return "Hoy estamos a: " + formattedDate;
    }
}
