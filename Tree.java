public class Tree {

	private int xCorner;
	private int yCorner;
	private int canopyExtent;
	private double totSunlight;

	public Tree (int xCorner, int yCorner, int canopyExtent) {
		this.xCorner = xCorner;
		this.yCorner = yCorner;
		this.canopyExtent = canopyExtent;
	}

	public void setTotalSunlight(double sunlight) {
		this.totSunlight = sunlight;
	}

	public int getXCorner() { return xCorner; }
	public int getYCorner() { return yCorner; }
	public int getXExtent() { return xCorner + canopyExtent; }
	public int getYExtent() { return yCorner + canopyExtent; }
	public double getTotalSunlight() { return totSunlight; }
}