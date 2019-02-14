import java.util.ArrayList;
import java.util.Random;

/**
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/
public class SolitaireBoard {
   
   public  final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public  final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assignment description for details.

   /**
      Representation invariant:

    @param piles[] an array to store the number of each pile and check if it is done
            piles[0]....piles[numPiles] should be at least one and at most total cards
    @param numPiles the number of valid piles
               0 < numPiles <=45
   */
   private  int [] piles = new int [CARD_TOTAL+1+NUM_FINAL_PILES];
   private  int numPiles = 0;


   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> pilesList) {

      for (int i = 0; i<pilesList.size(); i++ ){
         piles[i] = pilesList.get(i);
         numPiles++;
      }
   }
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
      Random random = new Random();
      int in = 0;
      int sum = 0;
      for (int i = 0; sum != CARD_TOTAL ;i++){
         numPiles ++;
         if (sum == CARD_TOTAL-1){
            piles[i] = 1;
            break;
         }
         in = random.nextInt(CARD_TOTAL-sum)+1;
         sum = sum + in ;
         piles[i] = in;
      }
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
       assert isValidSolitaireBoard();
       int temp;
       piles[CARD_TOTAL] = numPiles+1;
       for (int i =0 , point = 0; i <= CARD_TOTAL ; i++){
           if (piles[i]== 0){
               continue;
           }
           piles[i] = piles[i] - 1;
           temp = piles[point];
           piles[point] = piles[i];
           piles[i] = temp;
           if (piles[point] == 0){
               numPiles --;
               continue;
         }
            point ++;
       }
      numPiles ++;
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   public boolean isDone() {
      boolean result = true;
     for (int i = 0; i < piles.length;i++){
        if (piles[i]>NUM_FINAL_PILES){
           result =  false;
           continue;
        }
        piles[CARD_TOTAL+piles[i]] = piles[i];
        if (i>CARD_TOTAL){
           if (piles[i]!=(i-CARD_TOTAL)){
               result = false;
           }
           piles[i] = 0;
        }
     }
      return result;
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
      String str ="";
      for (int i = 0; i< numPiles; i++) {
         if (i== (numPiles-1)){
            str = str + piles[i];
            continue;
         }
         str = str + piles[i]+" ";
      }
      return str;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sum = 0;
      boolean result = true;
      for (int i = 0; i< numPiles; i++) {
         sum = piles[i]+sum;
         if (piles[i]<=0&&piles[i]>45)
         {
            result = false;
         }
      }
      if (sum !=CARD_TOTAL||numPiles>45||numPiles<=0){
         result =  false;
      }
      return result;
   }
}
