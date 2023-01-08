package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.UnavailabilityException;

public class AddAppointmentWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;

	protected JFormattedTextField dateBox;
	protected JFormattedTextField timeBox;
	protected JTextField locationBox;
	protected JTextField personBox;
	protected JTextField durationBox;

	public AddAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Aggiungi");
	}

	public void confirmAction() {
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
				c1.setTime(format3.parse(this.dateBox.getText() + ' ' + this.timeBox.getText()));

			if (!agendasList.isSelectionEmpty()) {
				try {
					agendas.get(agendasList.getSelectedIndex()).addAppointment(c1, this.locationBox.getText(),
							this.personBox.getText(), Integer.parseInt(this.durationBox.getText()));
					DialogMessage.information("Successo", "Appuntamento aggiunto!");
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				} catch (UnavailabilityException e) {
					DialogMessage.error("Impossibile", "Già impegnato! Impossibile creare l'appuntamento.");
					e.printStackTrace();
				}
				
				int select = agendasList.getSelectedIndex();
				agendasList.clearSelection();
				agendasList.setSelectedIndex(select);
				
				setVisible(false);
				dispose();
				
			} else {
				DialogMessage.error("Impossibile", "Selezionare un'agenda!");
			}
		} catch (IllegalArgumentException e) {
			DialogMessage.error("Errore di inserimento", "Non è possibile lasciare un valore vuoto");
			e.printStackTrace();
		}  catch (ParseException e) {
			DialogMessage.error("Errore di inserimento", "Data e ore inserite non sono valide");
			e.printStackTrace();
		}
	}

	public JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		
		DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat format2 = new SimpleDateFormat("HH:mm");
		tempPanel.setLayout(new GridLayout(5,2,5,5));
		tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		this.dateBox = new JFormattedTextField(format1);
		this.timeBox = new JFormattedTextField(format2);
		this.durationBox = new JTextField();
		this.locationBox = new JTextField();
		this.personBox = new JTextField();

		dateBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)
						|| (c == KeyEvent.VK_SLASH))) {
					errorDialog();
					e.consume();
				}
			}
		});

		timeBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)
						|| (c == ':'))) {
					errorDialog();
					e.consume();
				}
			}
		});

		durationBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
					errorDialog();
					e.consume();
				}
			}
		});

		locationBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == ' ')
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
					errorDialog();
					e.consume();
				}
			}
		});

		personBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == ' ')
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
					errorDialog();
					e.consume();
				}
			}
		});

		
		tempPanel.add(new JLabel("Data: "));
		tempPanel.add(dateBox);
		
		tempPanel.add(new JLabel("Ora: "));
		tempPanel.add(timeBox);
		
		tempPanel.add(new JLabel("Durata: "));
		tempPanel.add(durationBox);
		
		tempPanel.add(new JLabel("Luogo: "));
		tempPanel.add(locationBox);
		
		tempPanel.add(new JLabel("Persona: "));
		tempPanel.add(personBox);

		return tempPanel;
	}
	
	private static void errorDialog() {
		DialogMessage.error("Errore di inserimento", "Carattere non valido!");
	}

}
