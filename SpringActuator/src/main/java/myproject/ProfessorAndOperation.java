package myproject;

import java.util.Scanner;

public class ProfessorAndOperation {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[][] = new int[2][n];
		for (int i = 0; i < n; i++) {
			arr[0][i] = sc.nextInt();
		}
		int q = sc.nextInt();
		int l, r, temp;
		for (int i = 0; i < q; i++) {
			l = sc.nextInt() - 1;
			r = sc.nextInt() - 1;
			for (int j = l; j <= r; j++) {
				arr[1][j] = arr[1][j] + 1;
			}
		}
		for (int i = 0; i < n / 2; i++) {
			if ((arr[1][i] + arr[1][n - i - 1]) % 2 != 0) {
				// System.out.println("changing "+arr[0][i]+" ::
				// "+arr[0][n-i-1]);
				temp = arr[0][i];
				arr[0][i] = arr[0][n - i - 1];
				arr[0][n - i - 1] = temp;
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.print(arr[0][i] + " ");
		}
		sc.close();
	}

}
