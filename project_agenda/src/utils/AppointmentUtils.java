package utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import sourcecode.Appointment;

public class AppointmentUtils {
	public static boolean checkAvailability(Calendar date_time, String location, String person, int duration,
			ArrayList<Appointment> list) throws ParseException {
		Calendar end = (Calendar) date_time.clone();
		end.add(Calendar.MINUTE, duration);
		int count = 0;

		for (Appointment a : list) {
			if (end.before(a.getDateTime()) || end.equals(a.getDateTime()) || date_time.after(a.getEndDate_time()) || date_time.equals(a.getEndDate_time())) {
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
