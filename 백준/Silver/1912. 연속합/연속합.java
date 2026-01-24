import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        연속합 최대값
        10 -4 3 1 5 6 -35 12 21 -1

        dp[i] : i로 끝나는 부분합 최대값
        dp[i] = max(dp[i-1] + v[i], v[i])
        구: dp 중 최대값
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        int n;
        int[] v;
        int[] dp;

        n = Integer.parseInt(br.readLine());
        v = new int[n + 1];
        dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // dp
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i-1] + v[i], v[i]);
        }

        // 최대값
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
	}
}
