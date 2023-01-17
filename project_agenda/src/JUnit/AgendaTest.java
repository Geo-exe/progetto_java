package JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import sourcecode.Agenda;
import sourcecode.Appointment;
import sourcecode.UnavailabilityException;

class AgendaTest {

	@Test
	void creaAgendaSoloNome() {
		Agenda agenda = new Agenda("Prova");
		assertEquals(agenda.getName(), "Prova");
		assertEquals(agenda.size(), 0);
	}

	@Test
	void creaAgendaConAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/10/2023 15:18"), "Ufficio", "Capo", 60));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.getName(), "Prova");
		assertEquals(agenda.size(), 1);

	}

	@Test
	void creaAgendaConAppuntamenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.getName(), "Prova");
		assertEquals(agenda.size(), 3);

	}

	@Test
	void nomeAgenda() {

		Agenda agenda = new Agenda("Prova");
		assertEquals(agenda.getName(), "Prova");

	}

	@Test
	void appuntamentiAgenda() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.getAppointments(), appuntamenti);

	}

	@Test
	void numeroAppuntamenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.size(), appuntamenti.size());

	}

	@Test
	void aggiungiAppuntamento() throws ParseException, UnavailabilityException {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		agenda.addAppointment(GetCalendar("13/5/2021 8:00"), "Ufficio", "Capo", 20);

	}

	@Test
	void aggiungiAppuntamentoErrore() throws ParseException, UnavailabilityException {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.addAppointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertFalse(result);

	}

	Agenda SetAgenda() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/1/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/5/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		return agenda;
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
