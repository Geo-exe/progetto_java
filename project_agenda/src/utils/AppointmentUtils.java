package utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import sourcecode.Appointment;

public class AppointmentUtils {

	public static Appointment findMin(ArrayList<Appointment> temp, String order) {

		Appointment min = temp.get(0);

		for (Appointment a : temp) {
			// before ascendent
			// after descedent
			if (order.equals("Ascendente")) {

				if (a.getDateTime().before(min.getDateTime())) {
					min = a;
				}
			} else if (order.equals("Discendente")) {
				if (a.getDateTime().after(min.getDateTime())) {
					min = a;
				}

			}

		}

		return min;
	}

	public static boolean checkAvailability(Calendar date_time, String location, String person, int duration,
			ArrayList<Appointment> list) throws ParseException {
		Calendar end = (Calendar) date_time.clone();
		end.add(Calendar.MINUTE, duration);
		int count = 0;

		for (Appointment a : list) {
			if (end.before(a.getDateTime()) || date_time.after(a.getEndDate_time())) {
				count++;
			}
		}

		if (count == list.size()) {
			return true;
		} else {
			return false;
		}

	}
}
