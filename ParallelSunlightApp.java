import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.ForkJoinPool;

public class ParallelSunlightApp {

	static double[][] terrain;
	static final ForkJoinPool fjPool = new ForkJoinPool();

	public static void main(String[] args) throws Exception {

		String inFile = args[0], outFile = args[1];
		
		int x, y, numTrees;
		Tree[] trees;

		BufferedReader buffIn = new BufferedReader(new FileReader(args[0]));
		BufferedWriter buffOut = new BufferedWriter (new FileWriter (outFile));
		String[] line;

		line = buffIn.readLine().split(" ");
		x = Integer.parseInt(line[0]);
		y = Integer.parseInt(line[1]);
		terrain = new double[x][y];

		line = buffIn.readLine().split(" ");
		for (int i = 0; i < x; i++) {

			for (int j = 0; j < y; j++) {

				terrain[i][j] = Double.parseDouble(line[i*y + j]);
			}
		}

		numTrees = Integer.parseInt(buffIn.readLine());
		trees = new Tree[numTrees];
		for (int i = 0; i < numTrees; i++) {

			line = buffIn.readLine().split(" ");
			Tree t = new Tree (Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
			trees[i] = t;
		}

		buffIn.close();
		buffIn = null; line = null; inFile = null; outFile = null;
		System.gc();

		long before = System.currentTimeMillis();

		double grandTotal = fjPool.invoke(new TreeCalculation (0, trees.length, trees, terrain));
		double average = (float) (grandTotal/trees.length);

		long after = System.currentTimeMillis();
		System.out.println(after - before);

		buffOut.write(average + "\n");
		buffOut.write(trees.length + "\n");
		
		for (Tree t : trees) {

			buffOut.write(t.getTotalSunlight() + "\n");
		}

		buffOut.close();
	}
}