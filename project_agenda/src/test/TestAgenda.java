package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import sourcecode.Agenda;
import sourcecode.Appointment;
import sourcecode.UnavailabilityException;

class TestAgenda {
	
	@Test
	void testConstructor() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		assertEquals(nameTest, testObject.getName());
		assertEquals(0, testObject.size());
	}
	
	@Test
	void testSize() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		assertEquals(0, testObject.size());
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
		} catch (ParseException e) {}
		try {
			testObject.addAppointment(c1, nameTest, nameTest, 50);
		} catch (UnavailabilityException e) {}
		assertEquals(1, testObject.size());
	}
	
	@Test
	void testAddAppointment() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
		} catch (ParseException e) {}
		assertDoesNotThrow(() -> testObject.addAppointment(c1, nameTest, nameTest, 50));
		assertThrows(UnavailabilityException.class, () -> testObject.addAppointment(c1, nameTest, nameTest, 50));
	}
	
	@Test
	void testGetAppointments() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
			c2.setTime(format3.parse("13/12/2022 12:22"));
		} catch (ParseException e) {}
		try {
			testObject.addAppointment(c1, nameTest, nameTest, 50);
			testObject.addAppointment(c2, nameTest, nameTest, 50);
		} catch (UnavailabilityException e) {}
		assertEquals(testObject.getAppointments().getClass(), ArrayList.class);
		assertEquals(testObject.getAppointments().size(), 2);
		for(Object obj: testObject.getAppointments()) {
			assertInstanceOf(Appointment.class, obj);
		}
	}
	
	@Test
	void testRemoveAt() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
			c2.setTime(format3.parse("13/12/2022 12:22"));
		} catch (ParseException e) {}
		try {
			testObject.addAppointment(c1, nameTest, nameTest, 50);
			testObject.addAppointment(c2, nameTest, nameTest, 50);
		} catch (UnavailabilityException e) {}
		Appointment app2 = testObject.getAppointmentAt(1);
		testObject.removeAt(0);
		assertEquals(1, testObject.getAppointments().size());
		assertEquals(app2, testObject.getAppointmentAt(0));
	}
	
	@Test
	void testRemoveAll() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
			c2.setTime(format3.parse("13/12/2022 12:22"));
		} catch (ParseException e) {}
		try {
			testObject.addAppointment(c1, nameTest, nameTest, 50);
			testObject.addAppointment(c2, nameTest, nameTest, 50);
		} catch (UnavailabilityException e) {}
		testObject.removeAll();
		assertEquals(0, testObject.getAppointments().size());
	}
	
	@Test
	void testModifyAppointment() {
		String nameTest = "Test";
		Agenda testObject = new Agenda(nameTest);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			c1.setTime(format3.parse("12/12/2022 12:22"));
			c2.setTime(format3.parse("13/12/2022 12:22"));
			c3.setTime(format3.parse("16/12/2022 12:22"));
		} catch (ParseException e) {}
		try {
			testObject.addAppointment(c1, nameTest, nameTest, 50);
			testObject.addAppointment(c2, nameTest, nameTest, 50);
		} catch (UnavailabilityException e) {}
		// da finire
	}

}
