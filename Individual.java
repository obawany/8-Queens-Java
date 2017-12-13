import java.util.Arrays;
import java.util.Random;

/**
 * An <code>Individual</code> (a chromosome) is a candidate solution for a given
 * problem. Its representation depends on the specific problem to be solved. Two
 * individuals can be combined (see method crossover) to produce a new
 * offspring. As with natural chromosomes, these artificial ones suffer
 * mutations. Each chromosome has a fitness value that indicates the quality of
 * this solution.
 * <p/>
 * 
 * A <code>Population</code> is a collection of chromosomes. At each iteration
 * (generation), the genetic algorithm selects chromosomes for reproduction. The
 * offsprings are inserted into the population, and the least fitted individuals
 * are eliminated. Thus, the size of the population is fixed.
 * <p/>
 * 
 * For this assignment, an <code>Individual</code> represents a solution to the
 * <code>n</code>-Queens problem. As introduced in the assignment description, a
 * candidate solution is represented by a permutation of size <code>n</code>,
 * such that attribute <code>i</code> represents the row for the queen found at
 * column <code>i</code>.
 * <p/>
 * 
 * Not all permutations are valid solutions to <code>n</code>-Queens problem. A
 * permutation is a valid solution if no two queens can attack each other. Two
 * queens are attacking each other if they are on the same row or column, which
 * is impossible given this representation, but also if they are found on the
 * same minor or major diagonal.
 * <p/>
 *
 * Herein, we define the fitness value of an individual as the number of pairs
 * of queens attacking each other.
 * <p/>
 * You must complete the implementation of the class <code>Individual</code>
 * following all the directives.
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 */


public class Individual implements Comparable<Individual> {

	private int size;
	private int[] permutation;
	private Random random = new Random();
	private Individual mutated;

	/**
	 * Creates an <code>Individual</code> having <code>size</code> attributes.
	 * This constructor is used by the class <code>Population</code>.
	 * 
	 * @param size the number of attributes of this <code>Individual</code>
	 */

	public Individual() {

	}

	public Individual(int size) {

		this.size = size;
		permutation = Util.getPermutation(size);

	}

	/**
	 * Creates an <code>Individual</code> using the provided permutation. The method
	 * must copy the values of the permutation into a new array. This constructor
	 * is primarily used for testing.
	 * 
	 * @param permutation used to initialize the attributes of this <code>Individual</code>
	 */

	public Individual(int[] permutation) { // 10

		this.permutation = new int[permutation.length];
		for(int i=0; i<permutation.length; i++) {
			this.permutation[i] = permutation[i];
		}
		size = permutation.length;

	}

	/**
	 * Returns the offspring resulting from the crossover of <code>this</code>
	 * <code>Individual</code> and <code>other</code>. The result must be a valid
	 * permutation!
	 * <p/>
	 * 
	 * In particular, the naive solution consisting of taking the first
	 * <code>position-1</code> attributes of this <code>Individual</code> and
	 * the last <code>size-position</code> attributes of <code>other</code> would
	 * not generate a valid permutation in most cases.
	 * <p/>
	 *  
	 * Instead, we are proposing that the first <code>position-1</code> attributes 
	 * of this <code>Individual</code> are copied to the offspring, then the
	 * missing values will be selected from <code>other</code>, whilst preserving
	 * their order of appearance in <code>other</code>.
	 * <p/>
	 * 
	 * This method is primarily used for testing.
	 * 
	 * @param other a reference to an <code>Individual</code>
	 * @param position the location of the crossover
	 * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
	 */

	public Individual crossover(Individual other, int position) {
		mutated = new Individual(this.permutation);
																			//same as the method recombine(), but here the position is
		int counter = position;												// already decided.
		for(int j=0; j<permutation.length; j++) {
			boolean okay = true;
			for(int i=0; i<position; i++) {
				if(other.permutation[j] == mutated.permutation[i]){
					okay = false;
				}
			}
			if(okay) {
				mutated.permutation[counter] = other.permutation[j];
				counter++;
			}
		}

		this.permutation = mutated.permutation;
		return this;

	}

	/**
	 * Returns the offspring resulting from the crossover of <code>this</code>
	 * <code>Individual</code> and <code>other</code>. The method randomly selects the
	 * position of the crossover. The result must be a valid permutation!
	 * <p/>
	 * 
	 * In particular, the naive solution consisting of taking the first
	 * <code>position-1</code> attributes of this <code>Individual</code> and the last
	 * <code>size-position</code> attributes of <code>other</code> would not generate a
	 * valid permutation in most cases.
	 * <p/>
	 * 
	 * Instead, we are proposing that the first <code>position-1</code> attributes
	 * of this <code>Individual</code> are copied to the offspring, then the missing
	 * values will be selected from <code>other</code>, whilst preserving their
	 * order of appearance in <code>other</code>.
	 * <p/>
	 * 
	 * This method is used by <code>Population</code>.
	 * 
	 * @param other a reference to an <code>Individual</code>
	 * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
	 */

	public Individual recombine(Individual other) {

		int position = random.nextInt(permutation.length);			// combines two individuals by taking the elements of the first array
																	// till a random position and then taking the elements of the other 
		mutated = new Individual(this.permutation);					// array which were not present in the first one
		int counter = position;
		for(int j=0; j<permutation.length; j++) {
			boolean okay = true;
			for(int i=0; i<position; i++) {
				if(other.permutation[j] == mutated.permutation[i]){
					okay = false;
				}
			}
			if(okay) {
				mutated.permutation[counter] = other.permutation[j];
				counter++;
			}
		}

		this.permutation = mutated.permutation;
		return this;

	}

	/**
	 * Returns the offspring resulting from applying a mutation
	 * to this <code>Individual</code>. In order to make sure that 
	 * the result is valid permutation, the method exchanges
	 * the value of two attributes, those found at positions
	 * <code>i</code> and <code>j</code>.
	 * 
	 * This method is primarily used for testing.
	 * 
	 * @param i the first attribute 
	 * @param j the second attribute
	 * @return the offspring resulting from exchanging attributes <code>i</code> and <code>j</code>
	 */

	public Individual mutate(int i, int j) { // 10
		mutated = new Individual(this.permutation);
		int temp;
		temp = mutated.permutation[i];
		mutated.permutation[i] = mutated.permutation[j];
		mutated.permutation[j] = temp;

		this.permutation = mutated.permutation;
		return this;
	}

	/**
	 * Returns the offspring resulting from applying a mutation
	 * to this <code>Individual</code>. In order to make sure that 
	 * the result is valid permutation, the method exchanges
	 * the value of two randomly selected attributes.
	 * <p/>
	 * 
	 * This method is called by <code>Population</code>.
	 * 
	 * @return the offspring resulting from exchanging two randomly selected attributes
	 */

	public Individual mutate() {

		int i, j;
		i = random.nextInt(size);
		j = random.nextInt(size);
		do {
			i = random.nextInt(size);
			j = random.nextInt(size);							//the method chooses two random positions and swaps them
																// so at to "mutate" the array and it makes sure that the postions
			mutated = new Individual(this.permutation);			// are not same.
			int temp;
			temp = mutated.permutation[i];
			mutated.permutation[i] = mutated.permutation[j];
			mutated.permutation[j] = temp;
		}while(i == j);
		return mutated;
	}

	/**
	 * Returns the fitness value of <code>this Individual</code>, which
	 * is defined as the number of pairs of queens attacking each
	 * other.
	 * 
	 * @return the fitness value of <code>this Individual</code>. 
	 */

	public int getFitness() {

		int fitness = 0;
		for(int i=0; i<=size-2; i++) {
			for(int j=i+1; j<=size-1; j++) {
				int x = permutation[i] - permutation[j];
				int y = i - j;
				if(Math.abs(x) == Math.abs(y)) {                //just checks for the contradictions on major and minor diagonals
					fitness++;                                  // as the vertical and horizontal conflicts have been taken care of
				}
			}
		}

		return fitness;

	}

	/**
	 * Returns a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> is
	 * less than, equal to, or greater than the fitness of the specified <code>Individual</code>. 
	 * 
	 * @param other <code>Individual</code> to be compared
	 * @return a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> 
	 *         is less than, equal to, or greater than the fitness of the specified <code>Individual</code>.
	 */

	public int compareTo(Individual other) {

		return this.getFitness() - other.getFitness();               //overwritten compareTo method of Comparable interface so as to use
		// Arrays.sort(array) method
	}

	/**
	 * Returns a string representation of this <code>Individual</code>.
	 * 
	 * @return a string representation of this <code>Individual</code>
	 */

	public String toString() {
		String s = "[";
		for (int i=0; i<permutation.length; i++) {     					//return the optimal solution array
			s = s + permutation[i] + ", ";
		}
		s = s + "]";

		return s;

	}

	/**
	 * Runs a series of tests.
	 * 
	 * @param args command line parameters of the program
	 */

	public static void main(String[] args) {




	}
}
