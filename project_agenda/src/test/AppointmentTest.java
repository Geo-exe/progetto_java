package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import sourcecode.Appointment;

class AppointmentTest {

	@Test
	void creaAppuntamento() {

		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60);

		assertEquals(app.getDateTime(), GetCalendar("15/01/2023 15:20"));
		assertEquals(app.getLocation(), "Ufficio");
		assertEquals(app.getPerson(), "Capo");
		assertEquals(app.getDuration(), 60);

	}

	@Test
	void ottieniDataOra() {
		Calendar data = GetCalendar("15/01/2023 15:20");

		Appointment app = new Appointment(data, "Ufficio", "Capo", 60);

		assertEquals(app.getDateTime(), data);

	}

	@Test
	void ottieniLuogo() {
		String luogo = "Ufficio";
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), luogo, "Capo", 60);

		assertEquals(app.getLocation(), luogo);

	}

	@Test
	void ottieniPersona() {
		String nome = "Capo";
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", nome, 60);

		assertEquals(app.getPerson(), nome);

	}

	@Test
	void ottieniDurata() {
		int durata = 60;
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", durata);

		assertEquals(app.getDuration(), durata);

	}

	@Test
	void ottieniOraStringa() {
		String ora = "15:20";
		Appointment app = new Appointment(GetCalendar("15/01/2023 " + ora), "Ufficio", "Capo", 60);

		assertEquals(app.getStrTime(), ora);

	}

	@Test
	void ottieniDataStringa() {
		String data = "15/01/2023";
		Appointment app = new Appointment(GetCalendar(data + " 15:20"), "Ufficio", "Capo", 60);

		assertEquals(app.getStrDate(), data);

	}

	@Test
	void ottieniFine() {
		int durata = 60;

		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", durata);

		Calendar end = GetCalendar("15/01/2023 15:20");
		end.add(Calendar.MINUTE, durata);

		assertEquals(app.getEndDateTime(), end);

	}

	Calendar GetCalendar(String string) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(string));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}
}
