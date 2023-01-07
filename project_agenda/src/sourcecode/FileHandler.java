package sourcecode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileHandler implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static void exportToFile(String fileName, Object toExport){
        try {
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(toExport);
            out.close();
        } catch (IOException e) {
        	// TODO JOptionPane 
        	e.printStackTrace();
        }
    }
	
	public static Object importFromFile(String fileName, boolean isList) {
		Object rawImported = null;
		try {
        	ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        	rawImported = in.readObject();
        	in.close();
        } catch (IOException | ClassNotFoundException e) {
        	// TODO JOptionPane
        	e.printStackTrace();
        }
		if(isList) {
			 if(rawImported instanceof ArrayList<?>) {
				 ArrayList<?> temps = (ArrayList<?>) rawImported;
				 boolean isInstance = true;
				 int count = 0;
				 for(Object temp: temps) {
					 if(!(temps.get(count) instanceof Agenda)) {
						 isInstance = false;
						 break;
					 }
				 }
				 if(isInstance) {
					 @SuppressWarnings("unchecked")
					ArrayList<Agenda> tempAgendas = (ArrayList<Agenda>) rawImported;
					return tempAgendas;
				 }
			 }
		} else {
			if(rawImported instanceof Agenda) {
				return (Agenda) rawImported;
			}
		}
		return null;
    }

}
