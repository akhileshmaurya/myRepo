package myproject;

public class SubarrayProduct {
	static int pointsBelongToTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int p1, int q1, int p2, int q2) {
		if (area(x1, y1, x2, y2, x3, y3) == 0)
			return 0;
		else if (isInside(x1, y1, x2, y2, x3, y3, p1, q1) && isInside(x1, y1, x2, y2, x3, y3, p2, q2)) {
			return 3;
		} else if (isInside(x1, y1, x2, y2, x3, y3, p1, q1)) {
			return 1;
		} else if (isInside(x1, y1, x2, y2, x3, y3, p2, q2)) {
			return 2;
		} else {
			return 4;
		}

	}

	static double area(int x1, int y1, int x2, int y2, int x3, int y3) {
		return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
	}

	/*
	 * A function to check whether point P(x, y) lies inside the triangle formed
	 * by A(x1, y1), B(x2, y2) and C(x3, y3)
	 */
	static boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y) {
		/* Calculate area of triangle ABC */
		double A = area(x1, y1, x2, y2, x3, y3);

		/* Calculate area of triangle PBC */
		double A1 = area(x, y, x2, y2, x3, y3);

		/* Calculate area of triangle PAC */
		double A2 = area(x1, y1, x, y, x3, y3);

		/* Calculate area of triangle PAB */
		double A3 = area(x1, y1, x2, y2, x, y);

		/* Check if sum of A1, A2 and A3 is same as A */
		return (A == A1 + A2 + A3);
	}

	/* Driver program to test above function */
	public static void main(String args[]) {

		System.out.println(pointsBelongToTriangle(3,1,7,1,5,5,1,1,4,3));
		System.out.println(pointsBelongToTriangle(3,1,7,1,5,5,1,1,4,3));

	}
}