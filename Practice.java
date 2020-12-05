public class Practice {

	public static void main(String[] args) {
		int index = 0;
		System.out.println("Recursive Version");

		for (int i = 0; i <= 10; i++) {
			System.out.println(recursion(index));
			index++;
		}
		System.out.println();

		int cap = 11;
		System.out.println("Iterative Version");
		for (int i = 1; i <= cap; i++) {

			long method = iterative(i);
			System.out.println(method);
		}
		System.out.println();

		long total = 0;
		long startTime = System.nanoTime();
		for (long i = 0; i < total; i++) {
			System.out.println(recursion(i));
		}

		long endTime = System.nanoTime();

		System.out.println("The recursion took: " + total + (startTime - endTime) + " nanoseconds");

		for (long i = 0; i < total; i++) {
			System.out.println(iterative(i));
		}

		long endTime02 = System.nanoTime();

		System.out.println("The iterative took: " + total + (startTime - endTime02) + " nanoseconds");
	}

	public static long recursion(long i) {

		if (i == 0)
			return 0;
		else if (i <= 2)
			return 1;

		long fibTerm = recursion(i - 1) + recursion(i - 2);
		return fibTerm;

	}

	public static long iterative(long j) {

		int index = 0;
		int next = 1;

		for (int i = 2; i <= j; i++) {
			int fib = index;
			index = index + next;
			next = fib;

		}
		return index;
	}

}
