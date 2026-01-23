import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        finobacci(N)을 호출했을 때, 0과 1이 각각 몇번 출력되는지?
        n=3
        3 = 1 + 2
        2 = 1 + 0

        n=4
        4 = 3 + 2
        f(3)
        3 = 1 + 2
        2 = 1 + 0
        f(2)
        2 = 1 + 0

        dp[n][0] : n을 만들 때 0 출력 횟수
        dp[n][1] : n을 만들 때 1 출력 횟수

        dp[n][0] = dp[n-1][0] + dp[n-2][0]
        dp[n][1] = dp[n-1][0] + dp[n-2][1]
        n = 0,1
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[][] dp;
        int t;
        // 입력
        t  = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tmp = 0; tmp < t; tmp++) {
            n = Integer.parseInt(br.readLine());
            // 예외 처리
            if (n == 0) {
                sb.append("1 0\n");
                continue;
            }
            
            dp = new int[n+1][2];
            // 초기값
            dp[0][0] = 1;
            dp[0][1] = 0;
            dp[1][0] = 0;
            dp[1][1] = 1;
            // dp
            for(int i = 2; i <= n; i++) {
                dp[i][0] = dp[i-1][0] + dp[i-2][0];
                dp[i][1] = dp[i-1][1] + dp[i-2][1];
            }
            // 결과
            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }

        // 출력
        System.out.println(sb);
	}
}
