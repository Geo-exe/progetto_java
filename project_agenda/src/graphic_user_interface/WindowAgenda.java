package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class WindowAgenda extends JFrame {
	private static final long serialVersionUID = 1L;

	public WindowAgenda() {
		super("Dashboard");
		setLayout(new BorderLayout());

		// Crea il pannello per le schede
		JTabbedPane schedePane = new JTabbedPane();

		// Crea il pannello per la scheda "Vendite"
		JPanel venditePanel = new JPanel();
		venditePanel.setLayout(new FlowLayout());
		venditePanel.add(new JLabel("Grafico delle vendite"));
		schedePane.addTab("Vendite", venditePanel);

		// Crea il pannello per la scheda "Acquisti"
		JPanel acquistiPanel = new JPanel();
		acquistiPanel.setLayout(new FlowLayout());
		acquistiPanel.add(new JLabel("Grafico degli acquisti"));
		schedePane.addTab("Acquisti", acquistiPanel);

		// Crea il pannello per la scheda "Contatti"
		JPanel contattiPanel = new JPanel();
		contattiPanel.setLayout(new GridLayout(2, 2));
		contattiPanel.add(new JLabel("Numero di clienti:"));
		contattiPanel.add(new JLabel("123"));
		contattiPanel.add(new JLabel("Numero di fornitori:"));
		contattiPanel.add(new JLabel("456"));
		schedePane.addTab("Contatti", contattiPanel);

		add(schedePane, BorderLayout.CENTER);

		setPreferredSize(new Dimension(400, 300));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}