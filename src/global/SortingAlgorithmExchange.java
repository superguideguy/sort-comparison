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
	 * By default, lo should be 0, and hi should be arr.length-1.
	 * 
	 * The algorithm preCheck(arr) is run before the quicksort begins. This affects performance.
	 * Runs on average in O(n log n) time. Runs at worst in O(n^2) time. Requires at most O(n) additional memory. Unstable.
	 * 
	 * @param arr
	 */
	static void quicksortMedianOfThree(int[] arr, int lo, int hi) {
		if (OtherAlgorithm.preCheck(arr)) return;
		int p = qMo3(arr,lo,hi);
		quicksortMedianOfThree(arr,lo,p);
		quicksortMedianOfThree(arr,p+1,hi);
	}
	private static int qMo3(int[] arr, int lo, int hi) {
		int mid = lo + ((hi-lo)/2);
		int pivot = OtherAlgorithm.median(arr[lo], arr[mid], arr[hi]);
		return partition(arr,lo,hi,pivot);
	}
	
	private static int partition(int[] arr, int lo, int hi, int pivot) {
		int i = lo - 1;
		int j = hi + 1;
		while (true) {
			do {
				i = i + 1;
			} while (arr[i] < pivot);
			
			do {
				j = j - 1;
			} while (arr[i] > pivot);
			
			if (i >= j) return j;
			
			ShufflingAlgorithm.exchange(arr, i, j);
		}
		
	}
	
}
