package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import sourcecode.Agenda;

public abstract class ActionWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton confirm;
	private JButton cancel;
	private JSplitPane panel7030;
	protected ArrayList<Agenda> agendas;
	protected JList<String> agendasList;

	public ActionWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList, boolean actionWindowIsOpen) throws Exception {
		super(title);
		if (!actionWindowIsOpen) {
			this.agendas = agendas;
			this.agendasList = agendasList;
			
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
			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Aprire una sola finestra Azione per volta!");
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
		this.confirm = new JButton("Aggiungi");
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
