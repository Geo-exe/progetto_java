package sourcecode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public enum FindByEnum implements FindByInterface {
	DATA{
		public ArrayList<Appointment> findBy(String searchingParameter, Agenda agenda) throws ParseException{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY"); 
			ArrayList<Appointment> result= new ArrayList<Appointment>();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar input = Calendar.getInstance();
			input.setTime(format.parse(searchingParameter));
			Calendar temp;
			for(Appointment appointment: agenda.getAppointments()) {
				temp=appointment.getDateTime();
				if(sdf.format(temp.getTime()).equals(sdf.format(input.getTime()))) {
					result.add(appointment);
				}
			}
			
			return result;
		}
	}, 
	NOME{
		public ArrayList<Appointment> findBy(String searchingParamether, Agenda agenda){
			ArrayList<Appointment> result= new ArrayList<Appointment>();
			
			for(Appointment appointment: agenda.getAppointments()) {
				if(appointment.getPerson().equals(searchingParamether)) {
					result.add(appointment);
				}
			}
			
			return result;
		}
	};	
}
