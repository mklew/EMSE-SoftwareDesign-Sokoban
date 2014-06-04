package emse.softwaredesign.sokoban.model;

/**
 * This are all possible combinations of squares. Each View should know how to represent them. This indirection allows views to know nothing about real model.
 * <p/>
 * This is called <i>Fake Model</i>
 * 
 * @since 01/05/14
 */
public enum SquareType {

    /**
     * Player color piece
     */
    PLAYER{
    	public String toString(){
    		return "P";
    	}// end toString() override
    },
    PLAYER_ON_THE_SLOT {
        public String toString(){
            return "S";
        }// end of toString() override
    },
    /**
     * Box color piece
     */
    BOX{
    	public String toString(){
    		return "B";
    	}// end of toString() override
    },
    /**
     * BoxSlot color piece
     */
    BOX_SLOT {
    	public String toString(){
    		return "L";
    	}// end of toString() override
    },
    BOX_ON_THE_SLOT {
        public String toString(){
            return "C";
        }// end of toString() override
    },
    /**
     * GreenPoint color piece
     */
    GREEN_POINT {
        public String toString(){
            return "g";
        }// end of toString() override
    },
    /**
     * Dog color piece
     */
    DOG{
        public String toString(){
            return "d";
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
    }
}
