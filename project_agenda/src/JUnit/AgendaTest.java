package JUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

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

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.getAppointments(), appuntamenti);

	}

	@Test
	void numeroAppuntamenti() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);
		assertEquals(agenda.size(), appuntamenti.size());

	}

	@Test
	void aggiungiAppuntamento() throws ParseException, UnavailabilityException {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		agenda.addAppointment(GetCalendar("13/05/2021 8:00"), "Studio", "Cliente", 20);

		assertEquals(agenda.getAppointmentAt(3).getDateTime(), GetCalendar("13/05/2021 8:00"));
		assertEquals(agenda.getAppointmentAt(3).getLocation(), "Studio");
		assertEquals(agenda.getAppointmentAt(3).getPerson(), "Cliente");
		assertEquals(agenda.getAppointmentAt(3).getDuration(), 20);

	}

	@Test
	void aggiungiAppuntamentoErrore() throws ParseException, UnavailabilityException {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.addAppointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertFalse(result);
		assertEquals(agenda.size(), 3);

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

		assertEquals(agenda.size(), 2);
		assertEquals(agenda.getAppointmentAt(0), secondoAppuntamento);

	}

	@Test
	void rimuoviTutto() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("25/10/2022 18:10"), "Ufficio", "Capo", 35));
		appuntamenti.add(new Appointment(GetCalendar("13/05/2023 8:00"), "Ufficio", "Capo", 20));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		agenda.removeAll();

		assertEquals(agenda.size(), 0);

	}

	@Test
	void modificaAppuntamento() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.modifyAppointment(GetCalendar("25/10/2022 18:10"), "Studio", "Cliente", 35, 0);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertTrue(result);
		assertEquals(agenda.size(), 1);
		assertEquals(agenda.getAppointmentAt(0).getDateTime(), GetCalendar("25/10/2022 18:10"));
		assertEquals(agenda.getAppointmentAt(0).getLocation(), "Studio");
		assertEquals(agenda.getAppointmentAt(0).getPerson(), "Cliente");
		assertEquals(agenda.getAppointmentAt(0).getDuration(), 35);

	}

	@Test
	void modificaAppuntamentoErrore() throws ParseException, UnavailabilityException {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.modifyAppointment(GetCalendar("15/01/2023 15:30"), "Studio", "Cliente", 35, 0);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertFalse(result);
		assertEquals(agenda.size(), 1);
		assertEquals(agenda.getAppointmentAt(0).getDateTime(), GetCalendar("15/01/2023 15:20"));
		assertEquals(agenda.getAppointmentAt(0).getLocation(), "Ufficio");
		assertEquals(agenda.getAppointmentAt(0).getPerson(), "Capo");
		assertEquals(agenda.getAppointmentAt(0).getDuration(), 60);

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
			System.out.println(app.getStrDate());
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
		} catch (ParseException e) {
			e.printStackTrace();
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
		boolean result = false;
		int indice = 2;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.addAppointmentAtIndex(GetCalendar("15/01/2023 11:20"), "Ufficio", "Capo", 60, indice);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		Appointment app = agenda.getAppointmentAt(indice);

		assertTrue(result);
		assertEquals(app.getDateTime(), GetCalendar("15/01/2023 11:20"));
		assertEquals(app.getLocation(), "Ufficio");
		assertEquals(app.getPerson(), "Capo");
		assertEquals(app.getDuration(), 60);

	}

	@Test
	void aggiungiAppuntamentoDatoInidiceErrore() {

		ArrayList<Appointment> appuntamenti = new ArrayList<Appointment>();
		boolean result = false;
		int indice = 2;

		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 14:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("15/01/2023 18:30"), "Studio", "Cliente", 35));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2021 15:20"), "Ufficio", "Capo", 60));
		appuntamenti.add(new Appointment(GetCalendar("10/01/2023 15:20"), "Ufficio", "Capo", 20));
		appuntamenti.add(new Appointment(GetCalendar("28/09/2022 18:30"), "Studio", "Cliente", 45));
		appuntamenti.add(new Appointment(GetCalendar("18/02/2020 15:20"), "Ufficio", "Capo", 120));

		Agenda agenda = new Agenda("Prova", appuntamenti);

		try {
			agenda.addAppointmentAtIndex(GetCalendar("15/01/2023 15:20"), "Ufficio", "Capo", 60, indice);
			result = true;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnavailabilityException e) {
			e.printStackTrace();
		}

		assertFalse(result);

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
