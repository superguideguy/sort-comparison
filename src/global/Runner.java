package global;


public class Runner {

	public static void main(String[] args) {
		//GUI Thread
		//Sort Thread
		//Escape/Break/Watchdog Thread
		
		//All of the following should return 2
		temp(1,2,3);
		temp(1,3,2);
		temp(2,1,3);
		temp(2,3,1);
		temp(3,1,2);
		temp(3,2,1);
			System.out.println();
		temp(2,2,3);
		temp(2,3,2);
		temp(3,2,2);
		temp(2,2,1);
		temp(2,1,2);
		temp(1,2,2);
			System.out.println();
		temp(2,2,2);
		
	}
	
	static void temp(int a, int b, int c) {
		System.out.print(OtherAlgorithm.median(a, b, c));
	}
	
}
