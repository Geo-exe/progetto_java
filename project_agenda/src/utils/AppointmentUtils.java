package utils;

import java.util.ArrayList;

import sourcecode.Appointment;

public class AppointmentUtils {

	public static Appointment findMin(ArrayList<Appointment> temp) {
		
		Appointment min=temp.get(0);
		
		for(Appointment a: temp) {
			//<0 ascendent 
			//>0 descedent
			if(a.compare(min)<0) {
				min=a;
			}
			
			
		}
		
		return min;
	}
}
