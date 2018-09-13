import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SequentialSunlightApp {

	public static void main(String[] args) throws Exception {

		String inFile = args[0], outFile = args[1];
		
		int x, y, numTrees;
		double[][] terrain;
		Tree[] trees;

		BufferedReader buffIn = new BufferedReader (new FileReader (inFile));
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

		double total, grandTotal = 0.0, average;
		long before = System.currentTimeMillis();

		for (Tree t : trees) {

			total = 0.0;
			for (int i = t.getXCorner(); i < t.getXExtent(); i++) {

				for (int j = t.getYCorner(); j < t.getYExtent(); j++) {

					try {

						total += terrain[i][j];
					}
					catch (Exception e) {}
				}
			}
			
			t.setTotalSunlight(total);
			grandTotal += total;
		}

		average = (float) (grandTotal/trees.length);

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