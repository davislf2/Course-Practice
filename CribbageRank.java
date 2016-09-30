/** A playing card rank type designed for cribbage.  It supports a single-
 *  character abbreviation for each rank, as well as providing the face
 *  value of a rank (ACE=1, KING, QUEEN, and JACK=10, other ranks are their
 *  face value), needed for counting 15s in a cribbage hand.  Also provides
 *  methods to get the next smaller and larger rank of a given rank.
 *
 * @author Peter Schachte schachte@unimelb.edu.au
 */
public enum CribbageRank {
        ACE('A'),
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8'),
        NINE('9'),
        TEN('T'),
        JACK('J'),
        QUEEN('Q'),
        KING('K');

        /** Single character abbreviation used to briefly print the rank. */
        private final char abbrev;
        
        /** @return the single-character abbreviation for this rank. */
        public char abbrev() {
            return abbrev;
        }

        /** @return the face value of the rank for counting 15's in cribbage
         *  (ACE=1, KING, QUEEN, and JACK=10, other ranks are their face value).
         */
        public int faceValue() {
            return Math.min(this.ordinal()+1, 10);
        }
        public int realValue() {
            return this.ordinal()+1;
        }

        /** @return the next higher rank */
        public CribbageRank nextHigher() {
            int value = this.ordinal() + 1;
            return value >= values().length ? null : values()[value];
        }

        /** @return the next lower rank */
        public CribbageRank nextLower() {
            int value = this.ordinal() - 1;
            return value < 0 ? null : values()[value];
        }
        
        /** Construct a rank.
         *  @param abbrev the single-character abbreviation for this rank
         */
        CribbageRank(char abbrev) {
            this.abbrev = abbrev;
        }
        
        /** @return The rank as a single-character string. */
        @Override
        public String toString() {
            return Character.toString(abbrev);
        }
        
        /** Judge the face value through char (Doesnt' work) */
//      public int valueJudge(char c){
//  		int value=0;
//  		switch(c){
//  		case 'A':
//  			value = ACE.faceValue();
//  			break;
//  		case '2':
//  			value = TWO.faceValue();
//  			break;	
//  		case '3':
//  			value = THREE.faceValue();
//  			break;
//  		case '4':
//  			value = FOUR.faceValue();
//  			break;
//  		case '5':
//  			value = FIVE.faceValue();
//  			break;	
//  		case '6':
//  			value = SIX.faceValue();
//  			break;  
//  		case '7':
//  			value = SEVEN.faceValue();
//  			break;
//  		case '8':
//  			value = EIGHT.faceValue();
//  			break;
//  		case '9':
//  			value = NINE.faceValue();
//  			break;
//  		case 'T':
//  			value = TEN.faceValue();
//  			break;
//  		case 'J':
//  			value = JACK.faceValue();
//  			break;
//  		case 'Q':
//  			value = QUEEN.faceValue();
//  			break;
//  		case 'K':
//  			value = KING.faceValue();
//  			break;	
//  		default:
//  			value = -1;
//  			break;
//  		}  		
//  		return value;
//      }
}
