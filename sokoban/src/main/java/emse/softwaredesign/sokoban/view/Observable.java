/**
 * 
 */
package emse.softwaredesign.sokoban.view;

import java.util.*;

/**
 * @author Alberth Montero <alberthm@gmail.com>
 *
 */
public class Observable implements Subject {
	// Instance Variables
	private ArrayList<Observer> observers;

	/**
	 * Default Constructor
	 */
	public Observable() {
		observers = new ArrayList<Observer>();
	}// end of Default constructor

	/**
	 * Method that add an observer to the ArrayList
	 */
	public void registerObserver(Observer o) {
		observers.add(o);
	}// end of registerObserver(o)

	/**
	 * Method that remove an observer from the ArrayList
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}// end of removeObserver(o)

	/**
	 * Method that update all the observers in the ArrayList
	 */
	public void notifyObservers() {
		for (Observer o : observers)
			o.update(this);
	}// end of notifyObservers()
}// end of Observable Class
