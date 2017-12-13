import javax.swing.JOptionPane;

public class Queens {

	public static void simulate(int generations, int size, int dimension) {
		
		int counter = 1;
		Population pop = new Population(size, dimension);
		for(int i=0; i<generations-1; i++) {
			pop.evolve();
			if(pop.getFittest().getFitness() == 0){
				break;
			}
			counter++;
		}
		

		System.out.println("Number of generations is: " + counter);
		System.out.println();
		Individual ind = pop.getFittest();
		System.out.println("Fitness: "+pop.getFittest().getFitness());
		System.out.println(ind);

	}

	public static void main(String[] args) throws Exception{

		int generations, size, dimension;

		StudentInfo.display();

		if (args.length == 3) {
			generations = Integer.parseInt(args[0]);
			size = Integer.parseInt(args[1]);
			dimension = Integer.parseInt(args[2]);
		} else {
			generations = Integer.parseInt(JOptionPane.showInputDialog("Input the number of generations", "500"));
			size = Integer.parseInt(JOptionPane.showInputDialog("Input the size of the population", "100"));
			dimension = Integer.parseInt(JOptionPane.showInputDialog("Input the dimension of the board", "8"));
		}

		simulate(generations, size, dimension);
	}

}
