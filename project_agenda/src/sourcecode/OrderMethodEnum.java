package sourcecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public enum OrderMethodEnum implements OrderMethodInterface {
	ASCENDING {
		public void orderByDate(ArrayList<Appointment> appointments) {
			Collections.sort(appointments, new Comparator<Appointment>() {
				@Override
				public int compare(Appointment a1, Appointment a2) {
					return a1.getDateTime().compareTo(a2.getDateTime());
				}
			});
		}
	},
	DESCENDING {
		public void orderByDate(ArrayList<Appointment> appointments) {
			Collections.sort(appointments, new Comparator<Appointment>() {
				@Override
				public int compare(Appointment a1, Appointment a2) {
					return a2.getDateTime().compareTo(a1.getDateTime());
				}
			});
		}
	};
}