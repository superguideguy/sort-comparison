package global;

public class SortingAlgorithmExchange {

	
	/**
	 * Sorts the input array with a unidirectional bubble sort.
	 * Runs on average in O(n^2) time. Runs at worst in O(n^2) time. Requires at most O(1) additional memory. Stable.
	 * 
	 * @param arr - The array to be sorted
	 */
	static void bubbleSort(int[] arr) {
		while (!OtherAlgorithm.isSorted(arr))
			for (int i = 0; i < arr.length - 1; i++) if (arr[i] > arr[i+1])
				ShufflingAlgorithm.exchange(arr, i, i+1);
	}
	
	/**
	 * Sorts the input array with a bidirectional bubble (cocktail shaker) sort.
	 * Runs on average in O(n^2) time. Runs at worst in O(n^2) time. Requires at most O(1) additional memory. Stable.
	 * @param arr - The array to be sorted.
	 */
	static void cocktailSort(int[] arr) {
		while (!OtherAlgorithm.isSorted(arr)) {
			for (int i = 0; i <= arr.length - 2; i++) if (arr[i] > arr[i+1])
				ShufflingAlgorithm.exchange(arr, i, i+1);
			for (int i = arr.length - 2; i >= 0; i--) if (arr[i] > arr[i+1])
				ShufflingAlgorithm.exchange(arr, i, i+1);
		}
	}
	
	/**
	 * Sorts the input array with quicksort, selecting the pivot from the median of three method.
	 * The Hoare partition scheme is used.
	 * 
	 * The algorithm preCheck(arr) is run before the quicksort begins. This affects performance.
	 * Runs on average in O(n log n) time. Runs at worst in O(n^2) time. Requires at most O(n) additional memory. Unstable.
	 * 
	 * @param arr
	 */
	static void quicksortMedianOfThree(int[] arr) {
		if (OtherAlgorithm.preCheck(arr)) return;
		qMo3(arr,0,arr.length-1);
	}
	private static void qMo3(int[] arr, int lo, int hi) {
		
	}
	
}
