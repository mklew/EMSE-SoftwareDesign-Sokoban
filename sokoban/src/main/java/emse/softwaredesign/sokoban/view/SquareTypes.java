package emse.softwaredesign.sokoban.view;

/**
 * This are all possible combinations of squares. Each View should know how to represent them. This indirection allows views to know nothing about real model.
 * <p/>
 * This is called <i>Fake Model</i>
 *
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @author Alberth Montero <alberthm@gmail.com>
 * 
 * @since 01/05/14
 */
public enum SquareTypes {
    
    /**
     * Box On Slot
     */
	PLAYER_ON_LOCATION{
		public String toString(){
			return "B";
		}// end toString() override
	},
    /**
     * Player color piece
     */
    PLAYER{
    	public String toString(){
    		return "p";
    	}// end toString() override
    }, 
    /**
     * Box color piece
     */
    BOX{
    	public String toString(){
    		return "b";
    	}// end of toString() override
    },
    /**
     * BoxSlot color piece
     */
    BOX_ON_LOCATION{
    	public String toString(){
    		return "l";
    	}// end of toString() override
    }, 
    /**
     * Floor color piece
     */
    FLOOR{
    	public String toString(){
    		return "f";
    	}// end of toString() override
    }, 
    /**
     * Wall color piece
     */
    WALL{
    	public String toString(){
    		return "W";
    	}// end of toString() override
    }, 
    /**
     * Empty
     */
    OUTSIDE_WALL {
    	public String toString(){
    		return "-";
    	}// end of toString() override
    };
}
