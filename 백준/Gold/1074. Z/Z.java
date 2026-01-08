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
		2^n * 2^n -> 0 ~ 2^(n-1)

		n, r, c
		3, 5, 1
		(r, c) 가 몇 사분면에 있는지 파악해야 함
		
		mod 4 하면 (0,0), (0,1), (1,0), (1,1) 중에 하나로 나옴!
		(5, 1) -> mod 4 -> (1, 1) -> n=2 일 때 위치
		(5, 1) -> div 4 -> (1, 0) -> 3사분면

		fun(3,5,1) -> fun(2, 1, 1) + 16 * 2

		1. 함수
			int func(int n, int r, int c)
			: 2^n * 2^n 차원에서 (r,c) 값
		2. Base Condition
			func(1, r, c) -> r * 2 + c
		3. 재귀식
			func(n, r, c) 
			= 2^(n-1) * 2^(n-1) * (2 * r/2^(n-1) + c/2^(n-1)) 
				+ func(n - 1, r % 2^(n-1), c % 2^(n-1)) 

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
		int num = 1 << (n-1);
		return num * num * (2 * (r/num) + c/num) + func(n-1, r%num, c%num); 
	}
}