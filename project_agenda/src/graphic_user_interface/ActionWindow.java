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

public abstract class ActionWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JButton confirm;
	private JButton cancel;
	private JSplitPane panel7030;
	protected ArrayList<Agenda> agendas;
	protected JList<String> agendasList;

	public ActionWindow(String title) throws Exception {
		super(title);
		if (!ActionsPanel.actionWindowIsOpen || title.equals("Modifica Appuntamento ")) {
			this.agendas = Main.agendas;
			this.agendasList = Dashboard.agendasList;
			setLayout(new BorderLayout());
			panel7030 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			panel7030.setResizeWeight(0.7);
			panel7030.setEnabled(false);
			panel7030.setDividerSize(0);
			
			panel7030.add(loadFields());
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

	public abstract void confirmAction();

	private void cancelAction() {
		setVisible(false);
		dispose();
	}

	protected abstract JPanel loadFields();

	private JPanel endButtons() {
		this.confirm = new JButton("");
		getRootPane().setDefaultButton(confirm);
		this.cancel = new JButton("Annulla");

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
