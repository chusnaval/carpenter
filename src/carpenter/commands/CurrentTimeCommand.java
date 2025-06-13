package carpenter.commands;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class CurrentTimeCommand implements Command {

	@Override
	public String execute(){
		
		// get the current time
		LocalTime currentTime = LocalTime.now();
		
		// Define a formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		// format
		String formattedTime = currentTime.format(formatter);
		return "La hora es: " + formattedTime;
	}
}