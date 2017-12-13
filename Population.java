import java.util.Arrays;
import java.util.Random;

/** 
 * A <code>Population</code> is a collection of individuals (each one
 * representing a candidate solution for the n-queens problem).  To
 * facilitate the implementation of the various methods, <b>the
 * individuals will always be kept in increasing value of fitness</b>.
 */

public class Population {

	private int size, dimension;
	private Individual[] population;
	int[] a = Util.getPermutation(size);
	private static final double MUTATION_RATE = 0.8;
	private Random random = new Random();
	/**
	 * A constructor of arity 2 to initialize the <b>Population</b>.
	 * 
	 * @param size is the number of individuals of this population
	 * @param dimension is the size of the board and also the number of queens
	 */

	public Population(int size, int dimension) {

		this.size = size;
		this.dimension = dimension;
		population = new Individual[size];
		for(int i=0; i<size; i++) {
			int[] a = Util.getPermutation(dimension);
			population[i] = new Individual(a);
		}
	}

	/**
	 * The method <code>evolve</code> selects parent individuals. An offspring
	 * is then created from the two parents, using the method
	 * <code>crossover</code>. With probability <code>MUTATION_RATE</code>, the
	 * offspring is <code>mutated</code>. Use 0.8 as the default
	 * <code>MUTATION_RATE</code> The resulting child is inserted into the
	 * population. As a result, the least fitted individual will be eliminated
	 * from the population. Remember that the <code>population</code> is kept in
	 * increasing order of fitness. For the selection of the parents, you can
	 * experiment with different scenarios. A possible scenario is to randomly
	 * select two parents. Another possible one would be to select the most fit,
	 * and a randomly selected one. Or else, select the two most fitted
	 * individuals.
	 */

	public void evolve() {
		
		Arrays.sort(population);			//Sorts the array of individuals
		
		Individual mutated = population[random.nextInt(size)].recombine(population[random.nextInt(size)]);        //takes two elements of
																												  //and performs recombine()
		double prob = random.nextDouble();																		  //on them
		if(prob <= MUTATION_RATE) {								//performs the method mutate() on the individual with the probability of 0.8
			mutated = mutated.mutate();
		}

		population[size-1] = mutated;				//adds the mutated individual at the end of the population eliminating the worst solution.
	}

	/**
	 * The instance method <code>public Individual getFittest()</code> returns the
	 * "best" individual of the population, i.e. the one that has the smallest
	 * fitness value.
	 * 
	 * @return returns the currently best solution
	 */

	public Individual getFittest() {		//sorts the population and returns the best individual in it
		Arrays.sort(population);
		return population[0];
	}

	/**
	 * Returns a <code>String</code> representation of this <code>Population</code>.
	 * 
	 * @return the String representation of this Population
	 */

	public String toString() {

	// REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
		return null;
	}
}
