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

	public static String[] getNames() {
		return Arrays.stream(values()).map(Enum::name).toArray(String[]::new);
	}
}
