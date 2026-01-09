import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, m;
	char[][] matrix;
	StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		n = 3 -> column: 5, 시작점: (0, 2)
		  *  
		 * * 
		*****
		n = 6 -> 5 + 1 + 5, (0, 5)
		n = 24 -> 5 * 8 + 1 * 7 -> 47, (0, 23)

		width = high * 2 - 1;

		1. 함수 정의
			void func(int high, int a, int b)
			(a, b)위치에서 high 높이의 삼각형 채우기
		2. Base Condition
			n = 3 -> 기본 모양 채우기
		3. 재귀식
			전체 4등분 해서 가운데 빼고 채우기 
		*/
		// 입력
		n = Integer.parseInt(br.readLine());
		m = 2 * n - 1;
		matrix = new char[n][m];

		// 처리
		func(n, 0, m / 2);

		// 출력
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == '*') {
					sb.append("*");
				} else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	void func(int high, int a, int b) {
		if (high == 3) {
			matrix[a][b] = '*';
			matrix[a+1][b-1] = '*';
			matrix[a+1][b+1] = '*';
			matrix[a+2][b-2] = '*';
			matrix[a+2][b-1] = '*';
			matrix[a+2][b] = '*';
			matrix[a+2][b+1] = '*';
			matrix[a+2][b+2] = '*';
			return;
		}
		high /= 2;
		int width = 2 * high - 1;
		func(high, a, b);
		func(high, a + high, b - width / 2 - 1);
		func(high, a + high, b + width / 2 + 1);
	}
}
