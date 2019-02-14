import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * simulate the solitaire game, there are two different options for users to choose
 * the user could choose how to input data and how to output data
 *
*/


public class BulgarianSolitaireSimulator {

    /**
     * two different options
     * @param singleStep option for user to choose how to output
     * @param userConfig option for user to choose if input data randomly or by oneself
     */
    private static boolean singleStep = false;
    private static boolean userConfig = false;

    private static int INITIAL = 0;
    private static int CURRENT = 1;


    public static void main(String[] args) {



      // decide options
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // create a new object of solitaire board
      SolitaireBoard solitaireBoard = new SolitaireBoard();
      Scanner input = new Scanner(System.in);

      // input data in different options, if true, it is decided by user,if not, data is random
      if(userConfig) {
          System.out.println("Number of total cards is 45");
          System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
          System.out.println("Please enter a space-separated list of positive integers followed by newline:");
          String numOfPiles = input.nextLine();
          while (!checkChar(numOfPiles)||!checkNum(numOfPiles,solitaireBoard)){
              numOfPiles = input.nextLine();
          }
          solitaireBoard  = new SolitaireBoard(convert(numOfPiles));
      }

       // output all steps in different options
       output(singleStep,solitaireBoard,input);
   }

    /**
     * check if all chars are digital or whitespace
     *
     * @param input     a string comes from user
     * @return      if all chars are digital or whitespace, it is true,if not, it is false
     *
     */
   private static boolean checkChar (String input){
      boolean result = true;
      char [] charArray ;
      charArray = input.toCharArray();

      for(int i =0 ; i < charArray.length; i++){
         if (Character.isDigit(charArray[i])||charArray[i]==' '){
            continue;
         }else{
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
            result = false;
            break;
         }
      }
      return result;
   }

    /**
     *
     * check if all numbers of arrayList meet requirement
     *      sum of all numbers should be total cards, each piles should have at least one card or at most total cards
     *
     * @param input             a list of number would be checked, call method to convert string to arrayList
     * @param solitaireBoard    the object of solitaire board
     * @return         if it meets all requirement, return true,if not, return false and output a sentence
     */

   private  static boolean checkNum(String input, SolitaireBoard solitaireBoard){
       ArrayList<Integer> numList = convert(input);
       boolean result = true;
       int sum = 0;

       for (int num:numList ) {
           if (num < 1||num > solitaireBoard.CARD_TOTAL){
               result = false;
               break;
           }
           sum = sum + num;
       }

       if (sum != solitaireBoard.CARD_TOTAL){
           result = false;
       }
        if (!result){
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
                    +solitaireBoard.CARD_TOTAL);
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
        }
       return result;
   }

    /**
     * choose the different options to output results step by step or at one time
     *
     * @param state decided by singleStep. If it is true, output data step by step, if not, output all data at one time
     * @param solitaireBoard the object of solitaire board
     * @param input         make sure continue to next step when we hit the return key
     */


   private static  void output (boolean state, SolitaireBoard solitaireBoard, Scanner input){
      if(state) {
         print(solitaireBoard, INITIAL);
         int n = 0;
         while (!input.nextLine().equals("")) {
            if(solitaireBoard.isDone()){
               break;
            }
            solitaireBoard.playRound();
            n++;
            System.out.print("[" + n + "] ");
            print(solitaireBoard, CURRENT);
         }
         System.out.println("Done!");
      }else{
            print(solitaireBoard, INITIAL);
            int n = 0;
            while (!solitaireBoard.isDone()) {

               solitaireBoard.playRound();
               n++;
               System.out.print("[" + n + "] ");
               print(solitaireBoard, CURRENT);
            }
            System.out.println("Done!");
      }
   }

    /**
     * convert the string to an arraylist, remove the whitespace in the string
     *
     * @param input the string would be removed whitespace and stored in the arraylist
     * @return the arraylist has stored the integral data from string
     */
   private  static  ArrayList <Integer> convert(String input){
       ArrayList<Integer> convertList = new ArrayList<>();
       char [] charArray = input.toCharArray();
       String numStr = "";
       for (int i = 0; i < charArray.length; i++) {
           if (charArray[i] != ' ') {
               numStr = numStr + charArray[i];
               if (i == charArray.length - 1) {
                   convertList.add(Integer.parseInt(numStr));
                   numStr = "";
                   continue;
               }
           }
           if (charArray[i] == ' ') {
               if (!numStr.isEmpty()) {
                   convertList.add(Integer.parseInt(numStr));
                   numStr = "";
               }
           }
       }
       return convertList;
   }

    /**
     * print different sentences in different states
     * @param solitaireBoard the object of solitaire board
     * @param state         different state, 1 represent initial state, 2 represent current state
     */
   private static void print(SolitaireBoard solitaireBoard, int state){
      if(state==INITIAL)
         System.out.println("Initial configuration: "+ solitaireBoard.configString());
      if(state==CURRENT)
         System.out.println("Current configuration: "+ solitaireBoard.configString());
   }
  
}
