package global;

import java.util.Arrays;

public class Runner {

	public static void main(String[] args) {
		//GUI Thread
		//Sort Thread
		//Escape/Break/Watchdog Thread
		
		int[] arr = {3,1,9,5,2,4,7,8,6};
		SortingAlgorithmExchange.quicksortMedianOfThree(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
