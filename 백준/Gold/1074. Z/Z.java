import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, r, c;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		1. 함수
			int func(int n, int r, int c)
			: 2^n * 2^n 차원에서 (r,c) 값
		2. Base Condition
			func(0, r, c) -> 0
		3. 재귀식
			half <- 2^(n-1) : 1/4 조각 한 변의 길이
			(r, c)가 1번 사각형 -> return func(n - 1, r, c)
			(r, c)가 2번 사각형 -> return half*half + func(n-1, r, c - half);
			(r, c)가 3번 사각형 -> return 2*half*half + func(n-1, r - half, c);
			(r, c)가 4번 사각형 -> return 3*half*half + func(n-1, r - half, c - half);

		*/
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		System.out.println(func(n, r, c));
	}

	int func(int n, int r, int c) {
		if (n == 0) {
			return 0;
		}
		int half = 1 << (n-1);
		
		if (r < half && c < half) return func(n-1, r, c);
		if (r < half && c >= half) return half*half + func(n-1, r, c - half);
		if (r >= half && c < half) return 2*half*half + func(n-1, r - half, c);
		return 3*half*half + func(n-1, r - half, c - half);
	}
}