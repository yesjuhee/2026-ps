import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n;
	StringBuilder sb = new StringBuilder();
	int count = 0;

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		/*
		n-1 개의 원판을 기둥 1에서 기둥 2로 옮긴다.
		n번 원판을 기등 1에서 기둥 3으로 옮긴다.
		n-1 개의 원판을 기둥 2에서 기둥 3으로 옮긴다.
		-> 원판이 n-1개일 때 옮길 수 있으면 원판이 n개일 때도 옮길 수 있다.

		원판이 1개일 때 원판을 내가 원하는 곳으로 옮길 수 있다.
		원판이 k개일 때 옮길 수 있으면 원판이 k+1개일 때도 옮길 수 있다.

		1. 함수의 정의
			void func(int a, int b, int n) 
			: 원판 n개를 기둥 a에서 기둥 b로 옮기는 방법을 출력하는 함수
		2. base condition
			n == 1
		3. 재귀 식
			func(1, 3, n) -> func(1, 2, n-1) + 1 3 + func(2, 3, n-1)
			func(2, 3, n) -> func(2, 1, n-1) + 2 3 + func(1, 3, n-1)
		*/

		n = Integer.parseInt(br.readLine());
		count = func(1, 3, n);
		System.out.println(count);
		System.out.println(sb);
	}

	int func(int a, int b, int n) {
		if (n == 1) {
			sb.append(a).append(" ").append(b).append("\n");
			return 1;
		}
		int count = func(a, 6 - a - b, n - 1);
		sb.append(a).append(" ").append(b).append("\n");
		func(6 - a - b, b, n - 1);
		return 2 * count + 1;
	}
}