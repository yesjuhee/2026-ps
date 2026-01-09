import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	int[][] matrix;
	StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		1. 함수 정의
		2. Base Condition
		3. 재귀식
		*/
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(row.substring(j, j+1));
			}
		}

		func(n, 0, 0);

		System.out.println(sb);
	}

	void func(int size, int a, int b) {
		if (isAllSame(size, a, b)) {
			sb.append(matrix[a][b]);
			return;
		}
		sb.append("(");
		int nextSize = size / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				func(nextSize, a + i * nextSize, b + j * nextSize);
			}
		}
		sb.append(")");
	}

	boolean isAllSame(int size, int a, int b) {
		for (int i = a; i < a + size; i++) {
			for (int j = b; j < b + size; j++) {
				if (matrix[i][j] != matrix[a][b]) return false;
			}
		}
		return true;
	}
}
