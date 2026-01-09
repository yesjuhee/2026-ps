import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	char[][] matrix;
	StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		1. 함수 정의
			void func(int size, int a, int b)
			(a, b)위치에서 sizeXsize의 별 채우기
		2. Base Condition
			size == 0
		3. 재귀식
			가운데 빼고 9등분 해서 다시 함수 출력
		*/
		// 입력
		n = Integer.parseInt(br.readLine());
		matrix = new char[n][n];
		
		func(n, 0, 0);

		// 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != '*') {
					sb.append(' ');
				} else {
					sb.append('*');	
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	void func(int size, int a, int b) {
		if (size == 1) {
			matrix[a][b] = '*';
			return;
		}
		size = size / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) continue;
				func(size, a + i * size, b + j * size);
			}
		}
	}
}
