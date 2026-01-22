import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        1. 테이블 정의하기
        D[i] : 2 * i 크기의 직사각형을 채우는 방법의 수
        2. 점화식 찾기
        D[i] = D[i-1] + D[i-2]
        3. 초기값 구하기
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());

        // 예외 처리
        if(n == 1) {
            System.out.println(1);
            return;
        }
        
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10_007;
        }
        System.out.println(dp[n-1]);
	}
}
