package graphic_user_interface;

import java.util.Arrays;

import sourcecode.OrderMethodEnum;

public enum ActionButtonEnum implements ActionButtonInterface {
	ADDAGENDA{
		public Class<?> getAssociatedClass() {
			return AddAgendaWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Aggiungi <br>Agenda</p></html>"; 
		}

		public boolean AlwaysEnabled() {
			return true;
		}
		
	},
	REMOVEAGENDA{

		public Class<?> getAssociatedClass() {
			return DelAgendaWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Rimuovi <br>Agenda</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	ADDAPPOINTMENT{

		public Class<?> getAssociatedClass() {
			return AddAppointmentWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Aggiungi <br>Appuntamento</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	REMOVEAPPOINTMENT{

		public Class<?> getAssociatedClass() {
			return DelAppointmentWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Cancella <br>Appuntamento</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	EDITAPPOINTMENT{

		public Class<?> getAssociatedClass() {
			return SelectAppointmentToEditWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Modifica <br>Appuntamento</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	FINDAPPOINTMENT{

		public Class<?> getAssociatedClass() {
			return FindAppointmentWindow.class;
		}
		
		public String getTitle() {
			return "<html> <p style='text-align:center;'>Trova <br>Appuntamento</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	SORTAPPOINTMENT{

		public Class<?> getAssociatedClass() {
			return OrderByDateWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Ordina <br>Appuntamenti</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	},
	IMPORT{

		public Class<?> getAssociatedClass() {
			return ImportWindow.class;
		}
		
		public String getTitle() {
			return "<html><p style='text-align:center;'>Importa <br>Agenda</p></html>";
		}

		public boolean AlwaysEnabled() {
			return true;
		}
		
	},
	EXPORT{

		public Class<?> getAssociatedClass() {
			return ExportWindow.class;
		}

		public String getTitle() {
			return "<html><p style='text-align:center;'>Esporta Agenda</p></html>";
		}

		public boolean AlwaysEnabled() {
			return false;
		}
		
	};
	
	public static String[] getNames() {
        return Arrays.stream(values())
                     .map(Enum::name)
                     .toArray(String[]::new);
    }
}
