package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;


public class WindowAdd extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel date;
	private JLabel time;
	private JLabel location;
	private JLabel person;
	private JLabel duration;
	
	private JFormattedTextField dateBox;
	private JFormattedTextField timeBox;
	private JTextField locationBox;
	private JTextField personBox;
	private JTextField durationBox;
	
	private JPanel panel;

	
	private JButton confirm;
	private JButton cancel;
	
	private ArrayList<Agenda> agendas;
	private JList<String> agendasList;
	
	DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat format2 = new SimpleDateFormat("HH:mm");
	DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public WindowAdd(ArrayList<Agenda> agendas,JList<String> agendasList) throws HeadlessException {
		super("Aggiungi un appuntamneto");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));
        pack();
        setLocationByPlatform(true);
        setVisible(false);
      
        panel=new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(6,2,5,5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        this.agendas=agendas;
        this.agendasList=agendasList;
        
        this.date =new JLabel("Data: ");
        this.time =new JLabel("Ora: ");
        this.duration=new JLabel("Durata: ");
        this.location=new JLabel("Luogo: ");
        this.person=new JLabel("Persona: ");
       
        this.dateBox =new JFormattedTextField(format1);
        this.timeBox =new JFormattedTextField(format2);
        this.durationBox=new JTextField();
        this.locationBox=new JTextField();
        this.personBox=new JTextField();
        
        this.confirm=new JButton("Aggiungi");
        this.cancel=new JButton("Annulla");
        
        
        dateBox .addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
              {
                JOptionPane.showMessageDialog(null, "Carattere non valido!");
                e.consume();
              }
            }
          });
        
        timeBox .addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) || (c == ':')))        
              {
                JOptionPane.showMessageDialog(null, "Carattere non valido!");
                e.consume();
              }
            }
          });
        
        durationBox .addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) ))        
              {
                JOptionPane.showMessageDialog(null, "Carattere non valido!");
                e.consume();
              }
            }
          });
        
        locationBox .addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||(c >= 'a') && (c <= 'z')||(c >= 'A') && (c <= 'Z')||(c == ' ')||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) ))        
              {
                JOptionPane.showMessageDialog(null, "Carattere non valido!");
                e.consume();
              }
            }
          });
        
        
        personBox .addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||(c >= 'a') && (c <= 'z')||(c >= 'A') && (c <= 'Z')||(c == ' ')||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) ))        
              {
                JOptionPane.showMessageDialog(null, "Carattere non valido!");
                e.consume();
              }
            }
          });
        
        confirm.addActionListener(e->{
			try {
				Add();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});	
        cancel.addActionListener(e->Cancel());	
        
        panel.add(date);
        panel.add(dateBox);
        
        panel.add(time);
        panel.add(timeBox);
        
        panel.add(duration);
        panel.add(durationBox);
        
        panel.add(location);
        panel.add(locationBox);
        
        panel.add(person);
        panel.add(personBox);

        panel.add(cancel);
        panel.add(confirm);
        
		
	}
	
	public void ResetWindowAdd() {
		
		this.dateBox.setText("");
        this.timeBox.setText("");
        this.durationBox.setText("");
        this.locationBox.setText("");
        this.personBox.setText("");
		
	}
	
	public void Add() throws ParseException {
		
		if(Compiled()){
		Calendar c1 = Calendar.getInstance();
		
		c1.setTime(format3.parse(this.dateBox.getText()+' '+this.timeBox.getText() ) );
		
		if(!agendasList.isSelectionEmpty()) {
        agendas.get(agendasList.getSelectedIndex()).addAppointment(c1, this.locationBox.getText(), this.personBox.getText(), Integer.parseInt(this.durationBox.getText()));
        
        int select=agendasList.getSelectedIndex();
        int a=agendasList.getLastVisibleIndex();
        int b=agendasList.getFirstVisibleIndex();
        
        agendasList.setSelectedIndex(a);
        agendasList.setSelectedIndex(b);
        agendasList.setSelectedIndex(select);
        setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Selezionare un'agenda!");
		}
		
		}else {
			JOptionPane.showMessageDialog(null, "Compilare tutto il form!");
		}
		
	}
	
	public void Cancel() {
		
		setVisible(false);
		
	}
	
	public boolean Compiled() {
		
		return !(this.dateBox.getText().equals("") || this.timeBox.getText().equals("") || this.durationBox.getText().equals("") || this.locationBox.getText().equals("") || this.personBox.getText().equals(""));
		
	}
}
