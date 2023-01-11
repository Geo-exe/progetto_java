package sourcecode;

import java.text.ParseException;
import java.util.ArrayList;

public interface FindByInterface {
	public ArrayList<Appointment> findBy(String searchingParamether, Agenda agenda) throws ParseException;
}
