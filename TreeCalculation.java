import java.util.concurrent.RecursiveTask;

class TreeCalculation extends RecursiveTask<Double> {

 		int lo; int hi; Tree[] res; double[][] terrain;
 		final int SEQUENTIAL_CUTOFF = 500;
 		double grandTotal;

		TreeCalculation (int lo, int hi, Tree[] res, double[][] terrain) {

			this.lo = lo; this.hi = hi; this.res = res; this.terrain = terrain;
			grandTotal = 0.0;
		}

		protected Double compute () {

			if (hi - lo < SEQUENTIAL_CUTOFF) {

				double total;
				for (Tree t : res) {

					total = 0.0;
					for (int i = t.getXCorner(); i < t.getXExtent(); i++) {

						for (int j = t.getYCorner(); j < t.getYExtent(); j++) {

							try {

								total += terrain[i][j];
							} catch (Exception e) {}
						}
					}
					grandTotal += total;
					t.setTotalSunlight(total);
				}
				return grandTotal;
			}
			else {

				int mid = (hi+lo)/2;
				TreeCalculation left = new TreeCalculation (lo, mid, res, terrain);
				TreeCalculation right = new TreeCalculation (mid, hi, res, terrain);

				left.fork();
				double rightTot = right.compute();
				double leftTot = left.join();
				return rightTot + leftTot;
			}
		}
	}