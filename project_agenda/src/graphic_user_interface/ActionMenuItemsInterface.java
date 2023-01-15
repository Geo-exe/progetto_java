package graphic_user_interface;

/**
 * La classe astratta interface viene usata per implementare la classe
 * enum(ActionMenuItemsEnum)
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */

public interface ActionMenuItemsInterface {

	public Class<?> getAssociatedClass();

	public String getTitle();

	public boolean AlwaysEnabled();
}
