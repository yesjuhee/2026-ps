import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	int[][] matrix;
	int c1 = 0;
	int c2 = 0;
	int c3 = 0;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		3^7 = 1,000,000

		1. 함수 정의
			int func(int n, int a, int b)
			:(a,b)를 좌측상단 꼭지점으로 가지고, 행의 크기가 N인 정사각형 행렬에서
			종이의 개수를 구하는 함수
				matrix[a:a+n][b:b+n]
				N = 3^k
		2. Base Condition
			n == 1 -> 1
			내부의 모든 점이 같은 숫자 -> 1
		3. 재귀식
			func(n, a, b)
				그 다음 행의 크기: next = n / 3
			잘린 종이가 모두 같은 종류의 종이면 1
			아니면 각각의 합 구하기
		*/
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		func(n, 0, 0);

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
	}

	void func(int n, int a, int b) {
		// System.out.printf("func(%d %d %d)\n", n, a, b);
		if (n == 0) {
			return;
		}
		int v = matrix[a][b]; // 기준 값

		if (isAllSame(n, a, b, v)) {
			count(v);
			return;
		}

		int next = n / 3;
		func(next, a, b);
		func(next, a, b + next);
		func(next, a, b + next * 2);
		func(next, a + next, b);
		func(next, a + next, b + next);
		func(next, a + next, b + next * 2);
		func(next, a + next * 2, b);
		func(next, a + next * 2, b + next);
		func(next, a + next * 2, b + next * 2);
	}

	boolean isAllSame(int n, int a, int b, int v) {
		for (int i = a; i < a + n; i++) {
			for (int j = b; j < b + n; j++) {
				if (matrix[i][j] != v) return false;
			}
		}
		return true;
	}

	void count(int v) {
		if (v == -1) {
			c1++;
		} else if (v == 0) {
			c2++;
		} else {
			c3++;
		}
	}
}
