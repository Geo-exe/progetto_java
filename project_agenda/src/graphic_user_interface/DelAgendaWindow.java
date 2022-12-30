package graphic_user_interface;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

import sourcecode.Agenda;

public class DelAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;

	public DelAgendaWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList,
			boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void confirmAction() {
		// TODO Auto-generated method stub

	}

	@Override
	protected JPanel loadFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
