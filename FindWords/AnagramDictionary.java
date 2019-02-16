import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
	 Set<String>  dictionary ;

	
	
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
	   dictionary  = new HashSet<String>();
	   File inFile = new File(fileName);
	   Scanner fileScanner =  new Scanner(inFile);
	   while(fileScanner.hasNext()){
		   dictionary.add(fileScanner.next());
	   }
	   
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
	   ArrayList<String> anagramsList = new ArrayList<String>();
	   for(String str : dictionary){
		   if(isAnagrams(s,str)){
			   anagramsList.add(str);
		   }
	   }
	   return anagramsList;
   }
   
   /**
   Compare two strings if they are anagrams
   @param s string to compare
   @param t string to compare
   @return true if they are anagrams,otherwise not
 */
   private boolean isAnagrams(String s, String t){
	   char[] sCh = s.toCharArray();
	   char[] tCh = t.toCharArray();
	   if(sCh.length!=tCh.length)return false;
	   Arrays.sort(sCh);
	   Arrays.sort(tCh);	   
	   return Arrays.equals(sCh, tCh);  
   }
   
   
   
   
}
