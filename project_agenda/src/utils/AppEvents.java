package utils;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AppEvents {
	public static ListSelectionListener selectionHandler() {
		return (new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selectedAgenda = tempList.getSelectedIndex();
					setAppointmentsPanel();
				}
			}
		});
	}
}
