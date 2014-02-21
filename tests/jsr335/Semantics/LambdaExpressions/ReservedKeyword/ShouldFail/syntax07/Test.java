import java.io.IOException;
import java.text.ParseException;

public class Test {
	public static void main(String[] args) {
		try {
			if(args[0].length() == 1)
				throw new ParseException(args[0], 1);
			else
				throw new IOException();
		} catch(ParseException | IOException _) {
			
		}
    }
	
	
}