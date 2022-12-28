package utils;

import java.util.ArrayList;

import sourcecode.Appointment;

public class AppointmentUtils {

	public static Appointment findMin(ArrayList<Appointment> temp) {
		
		Appointment min=temp.get(0);
		
		for(Appointment a: temp) {
			//before ascendent 
			//after descedent
			if(a.getDate_time().before(min.getDate_time())) {
				min=a;
			}
			
			
		}
		
		return min;
	}
}
