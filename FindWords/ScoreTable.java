public class ScoreTable {
	// variables 
	private int [] score = new int [26];
	
	public ScoreTable(){
		initialScore();
		
	}
	
	
	/**
	 * initialize the score table of lower case letter by the letter value
	 */
	private void initialScore(){
		score['a'-'a'] = 1;
		score['e'-'a'] = 1;
		score['i'-'a'] = 1;
		score['o'-'a'] = 1;
		score['u'-'a'] = 1;
		score['l'-'a'] = 1;
		score['n'-'a'] = 1;
		score['s'-'a'] = 1;
		score['t'-'a'] = 1;
		score['r'-'a'] = 1;
		
		score['d'-'a'] = 2;
		score['g'-'a'] = 2;
		
		score['b'-'a'] = 3;
		score['c'-'a'] = 3;
		score['m'-'a'] = 3;
		score['p'-'a'] = 3;
		
		score['f'-'a'] = 4;
		score['h'-'a'] = 4;
		score['v'-'a'] = 4;
		score['w'-'a'] = 4;
		score['y'-'a'] = 4;
		
		score['k'-'a'] = 5;
		
		score['j'-'a'] = 8;
		score['x'-'a'] = 8;
		
		score['q'-'a'] = 10;
		score['z'-'a'] = 10;

	}
	
	
	/**
	 * get the total scores of a string, whatever it is upper or lower case
	 * PRE elements of string belong to [a-zA-Z]
	 * @param str a string to calculate the total score
	 * @return the total score of a string
	 */
	public int totalScore(String str){
		str = str.toLowerCase();
		int sum = 0;
		for(int i = 0 ; i< str.length() ; i++){
			sum = sum + getScore(str.charAt(i));
		}
		return sum;
	}
	
	
	
	/**
	 * get the score of the selected char
	 * PRE s belong to [a-z]
	 * @param s  the char to get score
	 * @return the score of s
	 */
	private int getScore(char s){
		return score[s-'a'];
	}
	
	
	
	

}
