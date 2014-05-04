/**
 * 
 */
package emse.softwaredesign.sokoban.view;

/**
 * @author Alberth Montero <alberthm@gmail.com>
 *
 */
public interface Subject {
	/**
	 * Method that register an object of Observer type
	 * 
	 * @param o the object to register
	 */
    public void registerObserver(Observer o);
    
    /**
     * Method that removes an object of Observer type
     *  
     * @param o the object to remove
     */
    public void removeObserver(Observer o);
    
    /**
     * Method that notify the Observers
     */
    public void notifyObservers();
}//end of Subject interface