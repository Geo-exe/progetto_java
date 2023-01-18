package graphic_user_interface;

/**
 * La classe astratta interface viene usata per implementare la classe
 * enum(ActionMenuItemsEnum).
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */

public interface ActionMenuItemsInterface {
	/**
	 * Serve per ottenere la classe.
	 * 
	 * @return la classe associata
	 */
	public Class<?> getAssociatedClass();

	/**
	 * Serve per ottenere il titolo.
	 * 
	 * @return il titolo del tasto
	 */
	public String getTitle();

	/**
	 * Serve per ottenere lo stato del tasto.
	 * 
	 * @return true se il tasto deve essere attivato altrimenti false
	 */
	public boolean AlwaysEnabled();
}
