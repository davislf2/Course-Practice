/** A playing card hand value calculation designed for Cribbage.  
 *  It use Combination.java and CribbageRank.java
 */
import java.util.Arrays;

public class HandValue {

	public static CribbageRank Cr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		int[] values = {0, 1, 2};
		String[] testhand = { "AC", "2H", "3D", "4S" };
		String[] teststart = { "AS" };
//		String[] testall = { "AC", "2H", "3D", "4S", "AS" };
//		String[] testall = { "2C", "3H", "5D", "JS", "KS" };
//		String[] testall = { "3C", "2H", "2D", "3S", "2C" };
//		String[] testall = { "6C", "7H", "8D", "9S", "TC" };
		
//		String[] testall = { "7C", "QH", "2C", "JC", "9D" };
//		String[] testall = { "AS", "3H", "KH", "7H", "KS" };
//		String[] testall = { "AS", "3H", "KH", "7H", "2D" };
//		String[] testall = { "2S", "3H", "KH", "3S", "4H" };
//		String[] testall = { "6C", "7C", "8C", "9C", "8S" };
//		String[] testall = { "7H", "9S", "8C", "7C", "8H" };
//		String[] testall = { "5H", "5S", "5C", "JD", "5D" };
		
		String[] testall = args;
		
		int totalScore = 0;
//		System.out.println(Cr.JACK.abbrev());
//		System.out.println(Cr.JACK.faceValue());
//		System.out.println(Cr.JACK.nextHigher());
//		System.out.println(Cr.JACK.nextLower());
		
		
		int[] values = new int[testall.length];
		for (int i=0;i<testall.length;i++){
//			System.out.print(testall[i]+" "+testall[i].charAt(0)+" / ");
			values[i] = valueJudge(testall[i].charAt(0))[0];			
		}
		
		Integer[] what = Arrays.stream(values).boxed().toArray(Integer[]::new);
		int sum=0;
//		System.out.print("\nwhat: ");
		for (Integer li: what){
//			System.out.print(li+" ");
			sum+=li;
		}
//		System.out.println("sum: "+sum);
//		int sum = IntStream.of(what).sum();		// Doesn't work
		
		Integer[] sumList = new Integer[32];
		
		Integer[][] lines = Combinations.<Integer>combinations(what);
		int si = 0;
		for (Integer[] line: lines){
//			System.out.print(si+1+"th comb: ");
			sumList[si]=0;
			for (Integer str: line){
//				System.out.print(str+" ");
				sumList[si] += str;
			}
//			System.out.println();
			si++;
		}		
		for (Integer li: sumList){
//			System.out.print(li+" ");
		}
		int score = fifteens(sumList);
		score = nob(testall);
		score = pairs(testall);
		score = runs(testall);
//		score = flushes(testall);
		totalScore = totalScore + fifteens(sumList);
		totalScore = totalScore + nob(testall);
		totalScore = totalScore + pairs(testall);
		totalScore = totalScore + runs(testall);
		totalScore = totalScore + flushes(testall);
//		System.out.print("\nscore: "+score+" total score: "+totalScore);
		System.out.print(totalScore);
		System.out.println();
	}
	
	/** @return the score of 15s */
	public static int fifteens(Integer[] sumList){
		int score = 0;
		for (Integer li: sumList){
			if(li==15){
				score = score + 2;
			}
		}
		return score;
	}
	
	/** @return the score of pairs */
	public static int pairs(String[] testall){
		int score = 0;
		int[] table = new int[14];
		for(int i=0;i<5;i++){
			table[valueJudge(testall[i].charAt(0))[1]]++;
		}
		int k = 0;
//		System.out.println();
		for(int ti: table){
//			System.out.print(k+"':"+ti+" / ");
			k++;
			score = score + ti*(ti-1);
		}	
		return score;
	}

	/** @return the score of runs */
	public static int runs(String[] testall){
		int score = 0;
		int run = 0;
		int count = 1;
		int[] table = new int[15];
		for(int i=0;i<5;i++){
			table[valueJudge(testall[i].charAt(0))[1]]++;
		}
		for(int i=1;i<14;i++){
			if( table[i] != 0 ){
				count = count*table[i];
				if( table[i-1]*table[i]*table[i+1] != 0 ){			
					if( run>2 ){
						run = run + 1;
					}else{
						run = 3;
					}					
//					System.out.print("run:"+run+" count:"+count);
				}		
			}
		}
		score = count*run;
		return score;
	}
	
	/** @return the score of flushes */
	public static int flushes(String[] testall){
		int score = 0;
		int[] table = new int[4];
		for(int i=0;i<4;i++){
			table[suitJudge(testall[i].charAt(1))]++;			
		}
		int k = 0;
//		System.out.println();
		for(int ti: table){
//			System.out.print(k+"':"+ti+" / ");
			if(ti>3) {
				score = score + 4;
				if( suitJudge(testall[4].charAt(1)) == k ){
					score = score + 1;
				}
			}
			k++;
		}
		return score;
	}
	
	/** @return the score of one for his nob */
	public static int nob(String[] testall){
		int score = 0;
		for(int i=0;i<4;i++){
			// if one of the hand card is Jack and its suit is the same as the start card
			if( (testall[i].charAt(0) == 'J') && (testall[i].charAt(1) == (testall[4].charAt(1))) )
				score++;
		}
		return score;
	}

   public static int suitJudge(char c){
 		int value=0;
 		switch(c){
 		case 'C':
 			value = 0;
 			break;
		case 'D':
 			value = 1;
 			break;
		case 'H':
 			value = 2;
 			break;
		case 'S':
 			value = 3;
 			break;
		default:
 			value = -1;
 			break;
 		}
 		return value;
   }
    public static int[] valueJudge(char c){
 		int facevalue=0;
 		int realvalue=0;
 		switch(c){
 		case 'A':
 			facevalue = Cr.ACE.faceValue();
 			realvalue = Cr.ACE.realValue();
 			break;
 		case '2':
 			facevalue = Cr.TWO.faceValue();
 			realvalue = Cr.TWO.realValue();
 			break;	
 		case '3':
 			facevalue = Cr.THREE.faceValue();
 			realvalue = Cr.THREE.realValue();
 			break;
 		case '4':
 			facevalue = Cr.FOUR.faceValue();
 			realvalue = Cr.FOUR.realValue();
 			break;
 		case '5':
 			facevalue = Cr.FIVE.faceValue();
 			realvalue = Cr.FIVE.realValue();
 			break;	
 		case '6':
 			facevalue = Cr.SIX.faceValue();
 			realvalue = Cr.SIX.realValue();
 			break;  
 		case '7':
 			facevalue = Cr.SEVEN.faceValue();
 			realvalue = Cr.SEVEN.realValue();
 			break;
 		case '8':
 			facevalue = Cr.EIGHT.faceValue();
 			realvalue = Cr.EIGHT.realValue();
 			break;
 		case '9':
 			facevalue = Cr.NINE.faceValue();
 			realvalue = Cr.NINE.realValue();
 			break;
 		case 'T':
 			facevalue = Cr.TEN.faceValue();
 			realvalue = Cr.TEN.realValue();
 			break;
 		case 'J':
 			facevalue = Cr.JACK.faceValue();
 			realvalue = Cr.JACK.realValue();
 			break;
 		case 'Q':
 			facevalue = Cr.QUEEN.faceValue();
 			realvalue = Cr.QUEEN.realValue();
 			break;
 		case 'K':
 			facevalue = Cr.KING.faceValue();
 			realvalue = Cr.KING.realValue();
 			break;	
 		default:
 			facevalue = -1;
 			realvalue = -1;
 			break;
 		}  		
 		int[] value = {facevalue, realvalue};
 		return value;
     }
}
