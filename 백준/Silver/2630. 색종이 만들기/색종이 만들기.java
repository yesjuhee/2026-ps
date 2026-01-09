import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	int[][] matrix;
	int[] count = new int[2];

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		1. 함수 정의
			void func(int size, int a, int b)
			:(a,b)를 좌측상단 꼭지점으로 가지고, 행의 크기가 N인 정사각형 행렬에서
			종이의 개수를 구하는 함수
				matrix[a:a+n][b:b+n]
				N = 2^k
		2. Base Condition
			내부의 모든 점이 같은 숫자
		3. 재귀식
			func(size, a, b)
				그 다음 행의 크기: next = size / 2
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

		System.out.println(count[0]);
		System.out.println(count[1]);
	}

	void func(int size, int a, int b) {
		if (isAllSame(size, a, b)) {
			count[matrix[a][b]]++;
			return;
		}
		int nextSize = size / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				func(nextSize, a + i * nextSize, b + j * nextSize);
			}
		}
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
