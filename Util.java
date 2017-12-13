import java.util.Random;

public class Util {

	/** Uses a a generator of pseudo-random numbers */

	private static final Random random = new Random();

	/**
	 * Returns a randomly generated permutation of the numbers 0 to
	 * <code>n-1</code>, where <code>n</code> is the size of the permutation. The
	 * permutation of size 0 is an empty permutation, which corresponds to an
	 * array of size 0.
	 * 
	 * @param n is the size of the permutation
	 * @return an array of size <code>n</code> containing the numbers 0 to
	 *         <code>n-1</code> in random order
	 */

	public static int[] getPermutation(int n) {

		// precondition: n >=0

		int permutation[];
		permutation = new int[n];

		boolean used[]; // memorizes the values that have already been used
		used = new boolean[n];

		int position = 0;
		while (position < n) { // stops when all the positions have been filled
			int index = random.nextInt(n);
			if (!used[index]) {
				permutation[position] = index;
				used[index] = true;
				position++; // incremented only if an unused value has been found 
			}
		}

		return permutation;
	}

	/**
	 * Runs a series of tests for the implementation of the method <code>getPermutation()</code>.
	 * 
	 * @param args the arguments of the program obtained from the command line
	 */

	public static void main(String[] args) {

		int permutation[], max = 10;

		StudentInfo.display();

		for (int size = 0; size <= max; size++) {

			boolean[] seen;
			seen = new boolean[size];

			System.out.println("Test, size=" + size);

			permutation = getPermutation(size);

			if (permutation == null) {
				System.err.println("The method getPermutation returned a null value");
				System.exit(1);
			}

			if (permutation.length != size) {
				System.err.println("The method getPermutation returned an array of size " + permutation.length);
				System.exit(1);
			}

			for (int i = 0; i < size; i++) {
				int value = permutation[i];
				if (value < 0 || value >= size) {
					System.err.println("Out of range value, " + value + ", at position " + i);
					System.exit(1);
				}
				if (seen[value]) {
					System.err.println(value + " was found twice, thus not a valid permutation");
					System.exit(1);
				}
				seen[value] = true;
			}

			for (int i = 0; i < size; i++) {
				if (!seen[i]) {
					System.err.println("Missing value " + i);
					System.exit(1);
				}
			}

			System.out.print("Success: {");

			for (int i = 0; i < size; i++) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(permutation[i]);
			}

			System.out.println("}");
		}

	}
}
