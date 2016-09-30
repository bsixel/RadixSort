public class RadixSort {

	/**
	 * 
	 * While it can technically sort any ASCII-handled character between 47 ('/') and 122 ('z'), it is best
	 * if you only feed it alphanumeric strings, because it uses the '/' character to deal with cases where the strings are not equal length.
	 * Will definitely give weird results if the strings to be sorted contain a '/'.
	 * When sorting, upper case letters are prioritized as they have a lower ASCII value.
	 * Numbers have higher priority than uppercase letters which are higher priority than lowercase letters.
	 * @param toSort The array of strings to sort.
	 * @return The inputed String array, sorted according to ASCII values.
	 */
	public static String[] sort(String[] toSort){

		System.out.println("Sorting " + printArray(toSort) + ":");
		System.out.format("Disregard the slashes, they're used as padding and unless we want %nterrible optimization just to get nicely formatted lists, they're staying for now.%n");
		
		long time = System.nanoTime();
		long printingTime = 0;
		int len = 0;
		for (int n = 0; n < toSort.length; n++) {
			if (toSort[n].length() > len) {
				len = toSort[n].length();
			}
		}
		for (int n = 0; n < toSort.length; n++) {
			while (toSort[n].length() < len) {
				toSort[n] = "/" + toSort[n];
			}
		}

		int iter = 0;
		for(int pos = len  - 1; pos >= 0; pos --){
			toSort = subSort(toSort, pos);
			iter ++;
			long t1 = System.nanoTime();
			System.out.format("Array after iteration #%1$d: %2$s%n", iter, RadixSort.printArray(toSort));
			long t2 = System.nanoTime();
			printingTime += (t2 - t1);
		}
		
		for (int n = 0; n < toSort.length; n ++) {
			while (toSort[n].startsWith("/")) {
				toSort[n] = toSort[n].substring(1);
			}
		}
		toSort = subSort(toSort, 0);

		long nano = System.nanoTime() - time - printingTime;
		long milli = (System.nanoTime() - time - printingTime) / 1000000;
		long sec = (System.nanoTime() - time - printingTime) / 1000000000;
		System.out.println("Time in nanoseconds: " + nano);
		System.out.println("Time in milliseconds: " + milli);
		System.out.println("Time in seconds: " + sec);
		return toSort;
	}
	
	private static String[] subSort(String[] toSort, int pos){
		String[] toReturn = new String[toSort.length];

		int[] digitCounts = new int[76];

		for(int n = 0; n < toSort.length; n ++){
			int d = toSort[n].charAt(pos) - 47;
			digitCounts[d] += 1;
		}

		for(int n = 1; n < digitCounts.length; n ++){
			digitCounts[n] += digitCounts[n - 1];
		}

		for(int n = toSort.length - 1; n >= 0; n --){
			int d = toSort[n].charAt(pos) - 47;
			toReturn[digitCounts[d] - 1] = toSort[n];
			digitCounts[d] --;
		}

		return toReturn;

	}
	
	/**
	 * Sort an integer array in ascending order.
	 * @param toSort The integer array we wish to sort.
	 * @return The numerically sorted integer array, in ascending order.
	 */
	public static int[] sort(int[] toSort){

		System.out.println("Sorting " + printArray(toSort) + ":");
		
		long time = System.nanoTime();
		long printingTime = 0;
		int len = 0;
		for (int n = 0; n < toSort.length; n  ++) {
			int l = (int) (Math.log10(toSort[n])) + 1;
			if (l > len) {
				len = l;
			}
		}

		int iter = 0;
		for(int pos = 0; pos <= len; pos ++){
			toSort = subSort(toSort, pos);
			iter ++;
			long t1 = System.nanoTime();
			System.out.format("Array after iteration #%1$d: %2$s%n", iter, RadixSort.printArray(toSort));
			long t2 = System.nanoTime();
			printingTime += (t2 - t1);
		}

		long nano = System.nanoTime() - time - printingTime;
		long milli = (System.nanoTime() - time - printingTime) / 1000000;
		long sec = (System.nanoTime() - time - printingTime) / 1000000000;
		System.out.println("Length of longest number: " + len);
		System.out.println("Time in nanoseconds: " + nano);
		System.out.println("Time in milliseconds: " + milli);
		System.out.println("Time in seconds: " + sec);
		return toSort;
	}

	private static int[] subSort(int[] toSort, int pos){
		int[] toReturn = new int[toSort.length];
        int[] digitCounts = new int[10];

        for(int n:toSort){
        	int d = (int) ((n / Math.pow(10, pos - 1)) % 10);
            digitCounts[d] += 1;
        }
        
        for(int i = 1; i < digitCounts.length; i ++){
            digitCounts[i] += digitCounts[i - 1];
        }
        
        for(int n = toSort.length - 1; n >= 0; n --){
        	int d = (int) ((toSort[n] / Math.pow(10, pos - 1)) % 10);
            toReturn[digitCounts[d] - 1] = toSort[n];
            digitCounts[d] --;
        }

        return toReturn;

	}

	/**
	 * There did not seem to be a nice method for printing arrays, so we made one. Returns a string representation of the array.
	 * @param toPrint The array you wish to print.
	 * @return A string with square brackets opening and closing the list of contained values, separated by commas and spaces. i.e. [1, 2, 3].
	 */
	public static String printArray(int[] toPrint) {
		String ret = "[";
		for (int n = 0; n < toPrint.length - 1; n ++) {
			ret += toPrint[n] + ", ";
		}
		ret += toPrint[toPrint.length - 1] + "]";
		
		return ret;
	}

	/**
	 * There did not seem to be a nice method for printing arrays, so we made one. Returns a string representation of the array.
	 * @param toPrint The array you wish to print.
	 * @return A string with square brackets opening and closing the list of contained values, separated by commas and spaces. i.e. [a, b, c].
	 */
	public static String printArray(String[] toPrint) {
		String ret = "[";
		for (int n = 0; n < toPrint.length - 1; n ++) {
			ret += toPrint[n] + ", ";
		}
		ret += toPrint[toPrint.length - 1] + "]";
		
		return ret;
	}

}
