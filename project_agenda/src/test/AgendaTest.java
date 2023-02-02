package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import sourcecode.Agenda;
import sourcecode.Appointment;
import sourcecode.FindByEnum;
import sourcecode.OrderMethodEnum;
import sourcecode.UnavailabilityException;

class AgendaTest {

	@Test
	void creaAgendaSoloNome() {
		String nomeTest = "Test";
		Agenda agenda = new Agenda(nomeTest);
		assertEquals(nomeTest, agenda.getName());
		assertEquals(0, agenda.size());
	}

	@Test
	void creaAgendaConAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/10/2023 15:18"), "Ufficio", "Capo", 60));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals("Prova", agenda.getName());
		assertEquals(1, agenda.size());

	}

	@Test
	void creaAgendaConAppuntamenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		assertEquals("Prova", agenda.getName());
		assertEquals(3, agenda.size());

	}

	@Test
	void nomeAgenda() {

		Agenda agenda = new Agenda("Prova");
		assertEquals("Prova", agenda.getName());

	}

	@Test
	void appuntamentiAgenda() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(appuntamenti, agenda.getAppointments());

	}

	@Test
	void numeroAppuntamenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(appuntamenti.size(), agenda.size());

	}

	@Test
	void aggiungiAppuntamento() {
		String nomeTest = "Prova";
		Agenda agenda = new Agenda(nomeTest);
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
		} catch (ParseException e) {
		}
		assertDoesNotThrow(() -> agenda.addAppointment(c1, nomeTest, nomeTest, 50));
		assertThrows(UnavailabilityException.class, () -> agenda.addAppointment(c1, nomeTest, nomeTest, 50));

	}

	@Test
	void rimuoviIndice() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		Appointment secondoAppuntamento = agenda.getAppointmentAt(1);

		agenda.removeAt(0);

		assertEquals(2, agenda.size());
		assertEquals(secondoAppuntamento, agenda.getAppointmentAt(0));

	}

	@Test
	void rimuoviTutto() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		agenda.removeAll();

		assertEquals(0, agenda.size());

	}

	@Test
	void modificaAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		String nomeTest = "Test";
		Agenda agenda = new Agenda(nomeTest, appuntamenti);

		assertDoesNotThrow(() -> agenda.modifyAppointment(GetCalendar("25/10/2022 18:10"), "Studio", "Cliente", 35, 0));
		assertThrows(UnavailabilityException.class,
				() -> agenda.modifyAppointment(GetCalendar("25/10/2022 18:10"), "Studio", "Cliente", 35, 0));
		assertEquals(1, agenda.size());
		assertEquals(GetCalendar("25/10/2022 18:10"), agenda.getAppointmentAt(0).getDateTime());
		assertEquals("Studio", agenda.getAppointmentAt(0).getLocation());
		assertEquals("Cliente", agenda.getAppointmentAt(0).getPerson());
		assertEquals(35, agenda.getAppointmentAt(0).getDuration());

	}

	@Test
	void ordinaAppuntamentiCrescenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		Appointment prec = agenda.sortAppointments(OrderMethodEnum.valueOf("CRESCENTE")).get(0);

		for (Appointment app : agenda.sortAppointments(OrderMethodEnum.valueOf("CRESCENTE"))) {

			if (app.getDateTime().before(prec.getDateTime())) {
				result = true;

			} else {
				prec = app;
			}
		}

		assertFalse(result);
	}

	@Test
	void ordinaAppuntamentiDecrescenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		Appointment prec = agenda.sortAppointments(OrderMethodEnum.valueOf("DECRESCENTE")).get(0);

		for (Appointment app : agenda.sortAppointments(OrderMethodEnum.valueOf("DECRESCENTE"))) {

			if (app.getDateTime().after(prec.getDateTime())) {
				result = true;

			} else {
				prec = app;
			}
		}

		assertFalse(result);
	}

	@Test
	void trovaAppuntamentiNome() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		ArrayList<Appointment> appuntamentifiltrati = new ArrayList<Appointment>();
		boolean result = false;
		int c = 0;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		appuntamentifiltrati = agenda.findAppointments(FindByEnum.valueOf("NOME"), "Capo");

		for (Appointment app : appuntamentifiltrati) {

			if (app.getPerson().equals("Capo")) {
				c++;
			}
		}

		if (c == appuntamentifiltrati.size()) {
			result = true;
		}

		assertTrue(result);

	}

	@Test
	void trovaAppuntamentiData() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		ArrayList<Appointment> appuntamentifiltrati = new ArrayList<Appointment>();
		boolean result = false;
		int c = 0;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		appuntamentifiltrati = agenda.findAppointments(FindByEnum.valueOf("DATA"), "15/01/2023");

		for (Appointment app : appuntamentifiltrati) {
			if (app.getStrDate().equals("15/01/2023")) {
				c++;
			}
		}

		if (c == appuntamentifiltrati.size()) {
			result = true;
		}

		assertTrue(result);

	}

	@Test
	void rimuoviOggettoAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60);
		appuntamenti.add(app);
		Agenda agenda = new Agenda("Prova", appuntamenti);

		agenda.removeObj(app);

		assertEquals(0, agenda.size());
	}

	@Test
	void aggiungiOggettoAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60);
		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.addObj(app);
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertEquals(1, agenda.size());
		assertEquals(app, agenda.getAppointmentAt(0));
	}

	@Test
	void indiceOggettoAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60);
		appuntamenti.add(app);
		Agenda agenda = new Agenda("Prova", appuntamenti);

		assertEquals(0, agenda.getAppointmentIndex(app));
		assertEquals(app, agenda.getAppointmentAt(agenda.getAppointmentIndex(app)));
	}

	@Test
	void appuntamentoDatoInidice() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		Appointment app = new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60);
		appuntamenti.add(app);
		Agenda agenda = new Agenda("Prova", appuntamenti);

		assertEquals(app, agenda.getAppointmentAt(0));

	}

	@Test
	void aggiungiAppuntamentoDatoInidice() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		int indice = 2;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		String nomeTest = "Prova";
		Agenda agenda = new Agenda(nomeTest, appuntamenti);

		assertDoesNotThrow(
				() -> agenda.addAppointmentAtIndex(GetCalendar("15/01/2023 11:20"), "Ufficio", "Capo", 60, indice));
		assertThrows(UnavailabilityException.class,
				() -> agenda.addAppointmentAtIndex(GetCalendar("15/01/2023 11:20"), "Ufficio", "Capo", 60, indice));

		Appointment app = agenda.getAppointmentAt(indice);
		assertEquals(GetCalendar("15/01/2023 11:20"), app.getDateTime());
		assertEquals("Ufficio", app.getLocation());
		assertEquals("Capo", app.getPerson());
		assertEquals(60, app.getDuration());

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
