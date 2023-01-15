package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import main.Main;
import sourcecode.Agenda;

/**
 * JFrame è una finestra di dialogo dichiarata nella libreria javax.swing. Essa
 * contiene al suo interno i vari componenti dell'interfaccia grafica.
 * ActionWindow estende JFrame implementando una classe astratta che crea una
 * finestra per un form composto da due JPanel. Il primo viene lasciato vuoto,
 * verrà poi implementato in loadFields quando verrà esteso nella classe. Il
 * secondo contiene i bottoni annulla e conferma, implementati in endButtons. Ad
 * ognuno viene dei due viene poi assegna una funzione, per il bottone annulla
 * si assegna cancelAction il cuo scopo è chiudere il JFrame, mentre per il
 * bottone confirm la sua funzione, chiamata confirmAction, deve essere
 * implementata nella sua classe.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */

public abstract class ActionWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JButton confirm;
	private JButton cancel;
	private JSplitPane panel7030;
	protected ArrayList<Agenda> agendas;
	protected JList<String> agendasList;

	/**
	 * Costruttore della classe. Inizializza i vari componenti.
	 * 
	 * @param String title
	 * @throws Exception
	 */
	public ActionWindow(String title) throws Exception {
		super(title);
		// creo il JFrame contenente i due pannelli del form
		if (!ActionsPanel.actionWindowIsOpen || title.equals("Modifica Appuntamento ")) {
			this.agendas = Main.agendas;
			this.agendasList = Dashboard.agendasList;
			setLayout(new BorderLayout());
			panel7030 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			panel7030.setResizeWeight(0.7);
			panel7030.setEnabled(false);
			panel7030.setDividerSize(0);

			// pannello dei campi del form
			panel7030.add(loadFields());
			// pannello dei bottoni
			panel7030.add(endButtons());
			add(panel7030, BorderLayout.CENTER);

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setPreferredSize(new Dimension(300, 200));
			pack();
			setAlwaysOnTop(true);
			setLocationRelativeTo(null);
			setVisible(true);
		} else {
			DialogMessage.error("Aprire una sola finestra Azione per volta!", "Impossibile eseguire l'azione");
			throw new Exception("Un'altra finestra azione è già in esecuzione");
		}
	}

	/**
	 * Funzione del tasto confirm. Deve essere implementato nella classe.
	 */
	public abstract void confirmAction();

	/**
	 * Funzione del tasto cancel. Chiude il JFrame.
	 */
	private void cancelAction() {
		setVisible(false);
		dispose();
	}

	/**
	 * Funzione di inizializzazione delle componenti da inserire nel JPanel per il
	 * form. Deve essere implementato nella classe.
	 * 
	 * @return JPanel
	 */
	protected abstract JPanel loadFields();

	/**
	 * Funzione di inizializzazione del JPanel contenente i bottoni cancel e
	 * confirm. I due bottoni vengono inizializzati e aggiungiunti al JPanel
	 * temporaneo che viene poi ritonato.
	 * 
	 * @return JPanel
	 */
	private JPanel endButtons() {
		this.confirm = new JButton("");
		// imposto il bottone confirm come tasto da premere quando preme invio
		getRootPane().setDefaultButton(confirm);
		this.cancel = new JButton("Annulla");

		// aggiungo i listener ai bottoni
		confirm.addActionListener(e -> {
			confirmAction();
		});
		cancel.addActionListener(e -> cancelAction());

		JPanel tempPanel = new JPanel();
		tempPanel.add(this.confirm);
		tempPanel.add(this.cancel);
		return tempPanel;
	}
}
