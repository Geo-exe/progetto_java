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

		assertEquals( GetCalendar("15/01/2023 15:20"),app.getDateTime());
		assertEquals("Ufficio", app.getLocation());
		assertEquals("Capo", app.getPerson());
		assertEquals(60, app.getDuration());

	}

	@Test
	void ottieniDataOra() {
		Calendar data = GetCalendar("15/01/2023 15:20");

		Appointment app = new Appointment(data, "Ufficio", "Capo", 60);

		assertEquals(data, app.getDateTime());

	}

	@Test
	void ottieniLuogo() {
		String luogo = "Ufficio";
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), luogo, "Capo", 60);

		assertEquals(luogo, app.getLocation() );

	}

	@Test
	void ottieniPersona() {
		String nome = "Capo";
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", nome, 60);

		assertEquals(nome, app.getPerson());

	}

	@Test
	void ottieniDurata() {
		int durata = 60;
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", durata);

		assertEquals(durata, app.getDuration());

	}

	@Test
	void ottieniOraStringa() {
		String ora = "15:20";
		Appointment app = new Appointment(GetCalendar("15/01/2023 " + ora), "Ufficio", "Capo", 60);

		assertEquals(ora, app.getStrTime() );

	}

	@Test
	void ottieniDataStringa() {
		String data = "15/01/2023";
		Appointment app = new Appointment(GetCalendar(data + " 15:20"), "Ufficio", "Capo", 60);

		assertEquals(data, app.getStrDate() );

	}

	@Test
	void ottieniFine() {
		int durata = 60;

		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", durata);

		Calendar end = GetCalendar("15/01/2023 15:20");
		end.add(Calendar.MINUTE, durata);

		assertEquals(end, app.getEndDateTime() );

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
