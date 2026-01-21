import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        한 계단 혹은 두 계단씩
        연속 3개 안됨
        마지막 계단 필수
        구: 얻을 수 있는 총 점수의 최대값
        dp[i] = max(dp[i - 1], dp[i - 2]) + v[i]
        n 1, 2는 고정, 3부터
        
        연속 3계단 안되는 거면 
        -> 3칸을 올라갈 수 있는 경우의 수: 1 + 2, 2 + 1 만 가능
        한 계단을 올라왔을 때 값 -> 두 개단 전
        dp[i, 1] = dp[i-1, 2] + v(i);
        dp[i, 2] = max(dp[i-2, 1], dp[i-2, 2]) + v(i);
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[] v; // 계단 값
        int[][] dp; // 올라간 값 계산

        // 입력
        n = Integer.parseInt(br.readLine());
        v = new int[n];
        for(int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        // 예외 처리 : n < 2
        if (n == 1) {
            System.out.println(v[0]);
            return;
        }
        dp = new int[n][2];

        // 초기 값
        dp[0][0] = v[0];
        dp[0][1] = v[0];
        dp[1][0] = v[0] + v[1];
        dp[1][1] = v[1];

        // dp
        for(int i = 2; i < n; i++) {
            dp[i][0] = dp[i-1][1] + v[i]; // 한 칸 이동
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + v[i]; // 두 칸 이동
        }
        System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
	}
}
