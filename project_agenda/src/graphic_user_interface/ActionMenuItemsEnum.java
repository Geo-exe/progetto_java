package graphic_user_interface;

import java.util.Arrays;

/**
 * La classe speciale enum, nella quale si trovano la classe, il titolo e la
 * flag di attivazione di ogni singolo menuItems presente nel menu in
 * ActionPanel.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */

public enum ActionMenuItemsEnum implements ActionMenuItemsInterface {
	/**
	 * Contiene un metodo che ritorna la classe AddAgendaWindow, uno che ritorna il
	 * titolo "Aggiungi Agenda" e un altro che ritorna true abilitando il tasto.
	 */
	ADDAGENDA {
		public Class<?> getAssociatedClass() {
			return AddAgendaWindow.class;
		}

		public String getTitle() {
			return "Aggiungi Agenda";
		}

		public boolean AlwaysEnabled() {
			return true;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe DelAgendaWindow, uno che ritorna il
	 * titolo "Rimuovi Agenda" e un altro che ritorna false disabilitando il tasto.
	 */
	REMOVEAGENDA {

		public Class<?> getAssociatedClass() {
			return DelAgendaWindow.class;
		}

		public String getTitle() {
			return "Rimuovi Agenda";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe AddAppointmentWindow, uno che
	 * ritorna il titolo "Aggiungi Appuntamento" e un altro che ritorna false
	 * disabilitando il tasto.
	 */
	ADDAPPOINTMENT {

		public Class<?> getAssociatedClass() {
			return AddAppointmentWindow.class;
		}

		public String getTitle() {
			return "Aggiungi Appuntamento";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe DelAppointmentWindow, uno che
	 * ritorna il titolo "Cancella Appuntamento" e un altro che ritorna false
	 * disabilitando il tasto.
	 */
	REMOVEAPPOINTMENT {

		public Class<?> getAssociatedClass() {
			return DelAppointmentWindow.class;
		}

		public String getTitle() {
			return "Cancella Appuntamento";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe SelectAppointmentToEditWindow, uno
	 * che ritorna il titolo "Modifica Appuntamento" e un altro che ritorna false
	 * disabilitando il tasto.
	 */
	EDITAPPOINTMENT {

		public Class<?> getAssociatedClass() {
			return SelectAppointmentToEditWindow.class;
		}

		public String getTitle() {
			return "Modifica Appuntamento";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe FindAppointmentWindow, uno che
	 * ritorna il titolo "Trova Appuntamento" e un altro che ritorna false
	 * disabilitando il tasto.
	 */
	FINDAPPOINTMENT {

		public Class<?> getAssociatedClass() {
			return FindAppointmentWindow.class;
		}

		public String getTitle() {
			return "Trova Appuntamento";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe OrderByDateWindow, uno che ritorna
	 * il titolo "Ordina Appuntamenti" e un altro che ritorna false disabilitando il
	 * tasto.
	 */
	SORTAPPOINTMENT {

		public Class<?> getAssociatedClass() {
			return OrderByDateWindow.class;
		}

		public String getTitle() {
			return "Ordina Appuntamenti";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe ImportWindow, uno che ritorna il
	 * titolo "Importa File Agenda" e un altro che ritorna true abilitando il tasto.
	 */
	IMPORT {

		public Class<?> getAssociatedClass() {
			return ImportWindow.class;
		}

		public String getTitle() {
			return "Importa File Agenda";
		}

		public boolean AlwaysEnabled() {
			return true;
		}

	},
	/**
	 * Contiene un metodo che ritorna la classe ExportWindow, uno che ritorna il
	 * titolo "Esporta File Agenda" e un altro che ritorna false disabilitando il
	 * tasto.
	 */
	EXPORT {

		public Class<?> getAssociatedClass() {
			return ExportWindow.class;
		}

		public String getTitle() {
			return "Esporta File Agenda";
		}

		public boolean AlwaysEnabled() {
			return false;
		}

	};

	/**
	 * Ritorna i titoli.
	 * @return array di stringhe dei nomi
	 */
	public static String[] getNames() {
		return Arrays.stream(values()).map(Enum::name).toArray(String[]::new);
	}
}
