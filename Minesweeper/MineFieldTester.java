public class MineFieldTester {

	
	   
   private static boolean[][] smallMineField = 
      {{false, false, false, false}, 
       {true, false, false, false}, 
       {false, true, true, false},
       {false, true, false, true}};

	
	
	
   public static void main(String[] args) {
			
		
		MineField  mineField  = new MineField(smallMineField);

		for (int i = 0; i < mineField.numRows(); i++){
			for (int j = 0; j < mineField.numCols(); j++){
			System.out.print(mineField.hasMine(i, j)+",");	
			}
			System.out.println();
			
		}
		System.out.println();
		
		for (int i = 0; i < 6; i++){
			for (int j = 0; j <6; j++){
				System.out.print(mineField.inRange(i, j)+",");		
			}
			System.out.println();
		}
		
		
		
		System.out.println(mineField.numMines()+ " " +mineField.numAdjacentMines(0,-1));
		
		System.out.println();
		int count = 0;
		MineField  mineField1  = new MineField(5,5,10);
		mineField1.populateMineField(0, 0);
		for (int i = 0; i < mineField1.numRows(); i++){
			for (int j = 0; j < mineField1.numCols(); j++){
			System.out.print(mineField1.hasMine(i, j)+",");	
			if(mineField1.hasMine(i,j)) count++;
			}
			System.out.println();
			
		}
		System.out.println(mineField1.numMines() +" " + count );
		
		
		
		
	}

}
