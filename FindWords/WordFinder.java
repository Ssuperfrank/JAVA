import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


public class WordFinder {

	/**
	 * the main function to run, the subsets of rack are saved in the array list subSetsRack,
	 * the target words existed in dictionary and its value are saved in the Tree map subSetDic,
	 * then sort the map to new order, and saved it in list sortedSet.
	 * @param args the dictionary name saved in it
	 */
	
	public static void main(String[] args) {
		
		String fileName = "sowpods";
		ArrayList<String> subSetsRack ;
		TreeMap<String,Integer> subSetsDic;
		ArrayList<Entry<String, Integer>> sortedSet;
		ScoreTable score = new ScoreTable();
		
		Scanner input = new Scanner(System.in);
		String rackIn = input.next();
		System.out.println("Type . to quit.");
		
		while(true){
			System.out.print("Rack? ");
			if(rackIn.equals(".")){
				break;
			}
			Rack rack = new Rack(rackIn);
			subSetsRack = new ArrayList<String>();
			subSetsDic = new TreeMap<String,Integer>();
			sortedSet = new ArrayList<Entry<String, Integer>>();
			
			subSetsRack = rack.getSets();
			try {
				AnagramDictionary anagramDic = new AnagramDictionary(fileName);
				// compare sub sets of rack and words of dictionary, and save anagrams into map
				for(int i = 0; i<subSetsRack.size(); i++){
					if (subSetsRack.get(i).length()<2) continue;
						ArrayList<String> anaList = new ArrayList<String>();
						anaList = anagramDic.getAnagramsOf(subSetsRack.get(i));
						for (String anagrams : anaList){
							subSetsDic.put(anagrams,score.totalScore(anagrams));
						}			
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file "+ fileName +" does not exist!");
				break;
			}
			
			sortedSet = sortValue(subSetsDic);			//sort the set by values of entry
			print(rackIn,subSetsDic,sortedSet);			//print results			
			rackIn = input.next();
		}
	}
	
	/**
	 * print out the results according the sets 
	 * 
	 * @param rack the string to input
	 * @param dictionary  subSet of dictionary saving the anagrams words 
	 * @param list  a list conversed from the map in order
	 */
	private static void print( String rack, TreeMap<String, Integer> dictionary, ArrayList<Entry<String, Integer>> list){
		System.out.println("We can make "+ dictionary.size() +" words from " + "\""+rack+"\"");	
		if(dictionary.size() == 0) return; 
		System.out.println("All of the words with their scores (sorted by score):");
		for (Entry<String, Integer> element : list){
			System.out.println(element.getValue()+": "+element.getKey());
		}
	}
	
	/**
	 * sort the map by its value
	 * @param map  a map need to sort in new order
	 * @return a new list  in new order by map's value
	 */
	private static ArrayList<Entry<String, Integer>> sortValue(TreeMap<String,Integer> map){
		ArrayList <Map.Entry<String, Integer>> sortList = new ArrayList <Map.Entry<String, Integer>>();
		sortList.addAll(map.entrySet());
		// override the interface
		Comparator<Entry<String,Integer>> sortValue = new Comparator<Map.Entry<String, Integer>>(){
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo( o1.getValue() );
			}
		};
		Collections.sort(sortList,sortValue);	
		return sortList;
	}
}
