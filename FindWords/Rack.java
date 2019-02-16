import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
	// variables
	
	private String inputRack;
	private TreeMap<String,Integer> chCount;
	
	/**
	 * construct the object, and initialize the string
	 * @param str the new rack string
	 */
	public Rack(String str){
		inputRack =  str;
		initialStr();
	}

	/**
	 * initial the string to a tree map, calculate multiplicity of each letter excluding non-alphabet
	 */
	public void initialStr(){
		chCount = new TreeMap<String,Integer>();
		for (int i = 0; i< inputRack.length(); i++){
			String ch = String.valueOf(inputRack.charAt(i));
			if(!isAlph(ch)) continue;
			if(chCount.containsKey(ch)){
				chCount.put(ch, chCount.get(ch)+1);
			}else{
				chCount.put(ch, 1);	
			}
		}
	}

	/**
	 * get the final all sub sets of rack
	 * @return a list saving all sub sets of rack
	 */
	public ArrayList<String> getSets(){
		ArrayList<String> subSets = new ArrayList<String>();
		subSets = allSubsets(getUnique(), getMult(), 0);
		return subSets;
		
	}
	
	void print(){
		
		ArrayList<String> subSets = new ArrayList<String>();
		
		subSets = allSubsets(getUnique(), getMult(), 0);
		
		System.out.println(subSets);
		
	}
	/**
	 * check a string if it belong to [a-zA-Z]
	 * @param str a string to check
	 * @return true if its all letter belong to [a-zA-Z], otherwise false 
	 */
	private boolean isAlph(String str){
		return str.matches("[a-zA-Z]+");
	}
	
	/**
	 * get the all unique letter of a string in order
	 * @return a string with unique letter
	 */
	private String getUnique(){
		String str = "";
		for(Map.Entry<String,Integer> entry : chCount.entrySet()  ){
			str = str + entry.getKey();
		}
		return str;
	}
	
	/**
	 * get the multiplicity of each letter from unique in order
	 * @return a array of multiplicity
	 */
	private int[] getMult(){
		int [] mult = new int[chCount.size()];
		int count =0;
		for(Map.Entry<String,Integer> entry : chCount.entrySet()  ){
			mult[count] = entry.getValue();
			count++;
		}
		return mult;
	}
	
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
}
