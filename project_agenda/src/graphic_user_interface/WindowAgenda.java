package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class WindowAgenda extends JFrame {
	private static final long serialVersionUID = 1L;

	public WindowAgenda() {
		super("Dashboard");
		setLayout(new BorderLayout());

		AgendaList ciaone = new AgendaList();
		
		add(ciaone, BorderLayout.CENTER);

		setPreferredSize(new Dimension(400, 300));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}